package ca.softwarespace.qiyanna.dataaggregator.services;

import ca.softwarespace.qiyanna.dataaggregator.events.DataCollectionEvent;
import ca.softwarespace.qiyanna.dataaggregator.events.UpdateMatchesEvent;
import ca.softwarespace.qiyanna.dataaggregator.models.CommunityPatch;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.AggregatorInfoDto;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.DefaultSummonerDto;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.LeagueEntryDto;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.RankDto;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.SummonerDto;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.TierDto;
import ca.softwarespace.qiyanna.dataaggregator.models.exceptions.RecordNotFoundException;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLAggregatorInfoRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLDefaultSummonerRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLLeagueEntryRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLRankRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLSummonerRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLTierRepository;
import ca.softwarespace.qiyanna.dataaggregator.services.interfaces.MatchesCollectionService;
import ca.softwarespace.qiyanna.dataaggregator.util.Constants;
import ca.softwarespace.qiyanna.dataaggregator.util.RegionEnum;
import ca.softwarespace.qiyanna.dataaggregator.util.RestClient;
import ca.softwarespace.qiyanna.dataaggregator.util.SeasonsEnum;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.league.LeagueEntry;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * Author: Steve Mbiele Date: 5/15/2019
 */
@Service
@Slf4j
public class MatchesCollectionServiceImpl implements MatchesCollectionService {

  private final SQLSummonerRepository sqlSummonerRepository;
  private final SQLAggregatorInfoRepository sqlAggregatorInfoRepository;
  private final SQLDefaultSummonerRepository sqlDefaultSummonerRepository;
  private final SQLLeagueEntryRepository sqlLeagueEntryRepository;
  private final SQLTierRepository sqlTierRepository;
  private final SQLRankRepository sqlRankRepository;

  @Value("${community.patches.url}")
  private String patchUrl;

  private ObjectMapper objectMapper;
  private ApplicationEventPublisher eventPublisher;

  @Autowired
  public MatchesCollectionServiceImpl(
      SQLSummonerRepository sqlSummonerRepository,
      SQLAggregatorInfoRepository sqlAggregatorInfoRepository,
      SQLDefaultSummonerRepository sqlDefaultSummonerRepository,
      SQLLeagueEntryRepository sqlLeagueEntryRepository,
      SQLTierRepository sqlTierRepository,
      SQLRankRepository sqlRankRepository,
      ApplicationEventPublisher eventPublisher) {
    this.sqlSummonerRepository = sqlSummonerRepository;
    this.sqlAggregatorInfoRepository = sqlAggregatorInfoRepository;
    this.sqlDefaultSummonerRepository = sqlDefaultSummonerRepository;
    this.sqlLeagueEntryRepository = sqlLeagueEntryRepository;
    this.sqlTierRepository = sqlTierRepository;
    this.sqlRankRepository = sqlRankRepository;
    this.objectMapper = new ObjectMapper();
    this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    this.eventPublisher = eventPublisher;
  }

  public void init() {
    try {
      AggregatorInfoDto infoDto = sqlAggregatorInfoRepository.findById(1);
      List<DefaultSummonerDto> defaultSummoners = sqlDefaultSummonerRepository.findAll();
      if (infoDto.getCount() == 0) {
        defaultSummoners.forEach(df -> prepareDataCollection(df.getName(), df.getRegionName(),
            SeasonsEnum.SEASON_2018.getSeasonId()));
      }
      infoDto.setCount(infoDto.getCount() + 1);
      sqlAggregatorInfoRepository.update(infoDto);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  @Async
  public void prepareDataCollection(String summonerName, String regionName, Integer startSeasonId) {
    if (startSeasonId == null) {
      startSeasonId = Season.getLatest().getId();
    }
    long seasonStartTime = getPatchStartTime(regionName, startSeasonId) * Constants.SECOND_TO_MILLI;
    Region region = RegionEnum.getRegionByTag(regionName);
    Summoner summoner = Orianna.summonerNamed(summonerName).withRegion(region).get();
    collectMatches(summoner, region, seasonStartTime);
    DataCollectionEvent collectionEvent = new DataCollectionEvent(this, "this is a test");
    eventPublisher.publishEvent(collectionEvent);
  }

  @Async
  public void prepareDataCollection(String accountId, String regionName) {
    Region region = RegionEnum.getRegionByTag(regionName);
    long seasonStartTime =
        getPatchStartTime(region.getTag(), Season.getLatest().getId()) * Constants.SECOND_TO_MILLI;
    Summoner summoner = Orianna.summonerWithAccountId(accountId).withRegion(region).get();
    collectMatches(summoner, region, seasonStartTime);
    DataCollectionEvent collectionEvent = new DataCollectionEvent(this, "this is a test");
    eventPublisher.publishEvent(collectionEvent);
  }

  @Async
  public void prepareUpdate(String summonerName, String regionName) {
    Region region = RegionEnum.getRegionByTag(regionName);
    Summoner summoner = Orianna.summonerNamed(summonerName).withRegion(region).get();
    updateMatchesForSummoner(summoner, region);
  }


  private long getPatchStartTime(String regionName, Integer startSeasonId) {
    try {
      RestClient restClient = new RestClient();
      String data = restClient.get(patchUrl);
      JsonNode json = objectMapper.readTree(data);
      JsonNode jsonPatches = json.get("patches");
      JsonNode jsonShifts = json.get("shifts");
      List<CommunityPatch> patches = objectMapper
          .readValue(jsonPatches.toString(), new TypeReference<List<CommunityPatch>>() {
          });
      HashMap<String, Integer> shifts = objectMapper
          .readValue(jsonShifts.toString(), new TypeReference<HashMap<String, Integer>>() {
          });
      Optional<CommunityPatch> patch = patches.stream().filter(p -> p.getSeason() == startSeasonId)
          .findFirst();
      Optional<String> shiftKey = shifts.keySet().stream()
          .filter(k -> k.toUpperCase().contains(regionName.toUpperCase())).findFirst();

      if (patch.isPresent() && shiftKey.isPresent()) {
        return patch.get().getStart() + shifts.get(shiftKey.get());
      }
    } catch (Exception e) {
      log.error(e.getLocalizedMessage(), e);
    }
    return 0L;
  }

  private void updateMatchesForSummoner(Summoner summoner, Region region) {
    HashSet<String> summonersToPull = new HashSet<>();
    HashSet<Long> unPulledMatchIds = new HashSet<>();

    DateTime startUpdateTime = createOrUpdateSummonerRecord(
        sqlSummonerRepository.convertSummonerOriannaToDto(summoner));
    long seasonStartTime =
        getPatchStartTime(region.getTag(), Season.getLatest().getId()) * Constants.SECOND_TO_MILLI;
    MatchHistory matches = filterMatchHistory(summoner, millsToDateTime(seasonStartTime),
        startUpdateTime);
    for (Match match : matches) {
      unPulledMatchIds.add(match.getId());
    }
    while (!unPulledMatchIds.isEmpty()) {
      long newMatchId = unPulledMatchIds.iterator().next();
      Match newMatch = Match.withId(newMatchId).withRegion(region).get();
      for (Participant p : newMatch.getParticipants()) {
        if (!summoner.getAccountId().equals(p.getSummoner().getAccountId())) {
          summonersToPull.add(p.getSummoner().getId());
        }
      }
      unPulledMatchIds.remove(newMatchId);
    }
    UpdateMatchesEvent updateMatchesEvent = new UpdateMatchesEvent(this, summonersToPull,
        region.getTag());
    eventPublisher.publishEvent(updateMatchesEvent);
  }

  private void collectMatches(Summoner summoner, Region region, long seasonStartTime) {
    HashSet<String> unPulledSummonerIds = new HashSet<>();
    unPulledSummonerIds.add(summoner.getId());

    HashSet<String> pulledSummonerIds = new HashSet<>();
    HashSet<Long> unPulledMatchIds = new HashSet<>();
    HashSet<Long> pulledMatchIds = new HashSet<>();

    while (!unPulledSummonerIds.isEmpty()) {

      final String newSummonerId = unPulledSummonerIds.iterator().next();

      final Summoner newSummoner = Summoner.withId(newSummonerId).withRegion(region).get();
      final MatchHistory matches;
      DateTime startUpdateTime = createOrUpdateSummonerRecord(
          sqlSummonerRepository.convertSummonerOriannaToDto(newSummoner));
      matches = filterMatchHistory(newSummoner, millsToDateTime(seasonStartTime),
          startUpdateTime);
      createOrUpdateLeagueEntry(newSummoner);

      for (final Match match : matches) {
        if (!pulledMatchIds.contains(match.getId())) {
          unPulledMatchIds.add(match.getId());
        }
      }
      unPulledSummonerIds.remove(newSummonerId);
      pulledSummonerIds.add(newSummonerId);

      while (!unPulledMatchIds.isEmpty()) {
        long newMatchId = unPulledMatchIds.iterator().next();
        Match newMatch = Match.withId(newMatchId).withRegion(region).get();
        if (newMatch != null && newMatch.getParticipants() != null) {
          try {
            for (Participant p : newMatch.getParticipants()) {
              if (!pulledSummonerIds.contains(p.getSummoner().getId())) {
                unPulledSummonerIds.add(p.getSummoner().getId());
              }
            }
          } catch (Exception e) {
            log.error(e.getMessage(), e);
          }
        }
        unPulledMatchIds.remove(newMatchId);
        pulledMatchIds.add(newMatchId);
      }
    }
  }

  private void createOrUpdateLeagueEntry(Summoner newSummoner) {
    LeagueEntryDto entryDto = sqlLeagueEntryRepository.findByAccountId(newSummoner.getAccountId());
    LeagueEntry leagueEntry = newSummoner.getLeaguePosition(Queue.RANKED_SOLO);

    try {
      if (leagueEntry != null) {
        RankDto rankDto = sqlRankRepository.findByRankName(leagueEntry.getDivision().name());
        TierDto tierDto = sqlTierRepository
            .findByTierName(leagueEntry.getLeague().getTier().name());
        if (entryDto == null) {
          entryDto = sqlLeagueEntryRepository
              .convertLeagueEntryOriannaToDto(leagueEntry, newSummoner.getAccountId(),
                  tierDto.getTierId(), rankDto.getRankId());
          sqlLeagueEntryRepository.add(entryDto);
        } else {
          LeagueEntryDto newEntryDto = sqlLeagueEntryRepository
              .convertLeagueEntryOriannaToDto(leagueEntry, newSummoner.getAccountId(),
                  tierDto.getTierId(), rankDto.getRankId());
          newEntryDto.setLeagueEntryId(entryDto.getLeagueEntryId());
          sqlLeagueEntryRepository.update(newEntryDto);
        }
      }
    } catch (RecordNotFoundException e) {
      log.error(e.getMessage(), e);
    }

  }

  private DateTime createOrUpdateSummonerRecord(SummonerDto dto) {
    SummonerDto fetched = sqlSummonerRepository.findByAccountId(dto.getAccountId());
    if (fetched == null) {
      sqlSummonerRepository.add(dto);
      return null;
    } else {
      long lastUpdate = fetched.getRevisionDate();
      sqlSummonerRepository.update(dto);
      return millsToDateTime(lastUpdate);
    }
  }

  private MatchHistory filterMatchHistory(Summoner summoner, DateTime seasonStartTime,
      DateTime startTime) {
    if (startTime != null) {
      return Orianna.matchHistoryForSummoner(summoner)
          .withQueues(Constants.getQueuesList()).withStartTime(startTime).get();
    } else {
      return Orianna.matchHistoryForSummoner(summoner).withStartTime(seasonStartTime)
          .withQueues(Constants.getQueuesList()).get();
    }
  }

  private DateTime millsToDateTime(long millis) {
    return new DateTime(millis);
  }

  @Override
  public void onApplicationEvent(UpdateMatchesEvent updateMatchesEvent) {
    HashSet<String> accountIds = updateMatchesEvent.getSummonersToCollect();
    String regionName = updateMatchesEvent.getRegionName();
    accountIds.forEach(s -> prepareDataCollection(s, regionName));
  }
}
