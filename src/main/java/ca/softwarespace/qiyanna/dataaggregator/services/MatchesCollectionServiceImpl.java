package ca.softwarespace.qiyanna.dataaggregator.services;

import ca.softwarespace.qiyanna.dataaggregator.events.DataCollectionEvent;
import ca.softwarespace.qiyanna.dataaggregator.events.UpdateMatchesEvent;
import ca.softwarespace.qiyanna.dataaggregator.models.CommunityPatch;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.AggregatorInfo;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.DefaultSummonerName;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.LeagueEntry;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.Rank;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.Tier;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.AggregatorInfoRecord;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.DefaultSummonerNameRecord;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.LeagueEntryRecord;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.RankRecord;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.SummonerRecord;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.TierRecord;
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
import org.jooq.DSLContext;
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

  private final DSLContext dsl;
  @Value("${community.patches.url}")
  private String patchUrl;
  private LeagueEntry leagueEntryTable = LeagueEntry.LEAGUE_ENTRY;

  private ObjectMapper objectMapper;
  private ApplicationEventPublisher eventPublisher;

  @Autowired
  public MatchesCollectionServiceImpl(DSLContext dsl, ApplicationEventPublisher eventPublisher) {
    this.dsl = dsl;
    this.objectMapper = new ObjectMapper();
    this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    this.eventPublisher = eventPublisher;
  }

  public void init() {
    AggregatorInfoRecord aggregatorInfo = this.dsl.selectFrom(AggregatorInfo.AGGREGATOR_INFO)
        .fetchOne();
    List<DefaultSummonerNameRecord> defaultSummoners = this.dsl
        .selectFrom(DefaultSummonerName.DEFAULT_SUMMONER_NAME).fetch();
    if (aggregatorInfo.getCount() == 0) {
      defaultSummoners.forEach(df -> prepareDataCollection(df.getName(), df.getRegionname(),
          SeasonsEnum.SEASON_2018.getSeasonId()));
      dsl.update(AggregatorInfo.AGGREGATOR_INFO).set(aggregatorInfo).execute();
    }
    aggregatorInfo.setCount(aggregatorInfo.getCount() + 1);
    aggregatorInfo.update();
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

    DateTime startUpdateTime = createOrUpdateSummonerRecord(summoner);
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
      DateTime startUpdateTime = createOrUpdateSummonerRecord(newSummoner);
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
        if (newMatch != null) {
          for (Participant p : newMatch.getParticipants()) {
            if (!pulledSummonerIds.contains(p.getSummoner().getId())) {
              unPulledSummonerIds.add(p.getSummoner().getId());
            }
          }
        }
        unPulledMatchIds.remove(newMatchId);
        pulledMatchIds.add(newMatchId);
      }
    }
  }

  private void createOrUpdateLeagueEntry(Summoner newSummoner) {
    LeagueEntryRecord record = dsl.selectFrom(
        LeagueEntry.LEAGUE_ENTRY)
        .where(
            LeagueEntry.LEAGUE_ENTRY.summoner().ACCOUNTID.eq(newSummoner.getAccountId()))
        .fetchAny();

    com.merakianalytics.orianna.types.core.league.LeagueEntry leaguePosition = newSummoner
        .getLeaguePosition(Queue.RANKED_SOLO);
    if (leaguePosition != null) {
      RankRecord rankRecord = dsl.selectFrom(Rank.RANK)
          .where(Rank.RANK.NAME.like(leaguePosition.getDivision().name())).fetchAny();
      TierRecord tierRecord = dsl.selectFrom(Tier.TIER)
          .where(Tier.TIER.SHORTNAME.like(leaguePosition.getLeague().getTier().name()))
          .fetchAny();

      if (record == null) {
        record = new LeagueEntryRecord();
        record = fillLeagueEntry(newSummoner, record, leaguePosition, rankRecord, tierRecord);
        dsl.insertInto(leagueEntryTable, leagueEntryTable.QUEUEID, leagueEntryTable.RANKID,
            leagueEntryTable.SUMMONERID, leagueEntryTable.TIERID, leagueEntryTable.FRESHBLOOD,
            leagueEntryTable.HOTSTREAK, leagueEntryTable.INACTIVE,
            leagueEntryTable.LEAGUEID, leagueEntryTable.LEAGUEPOINTS, leagueEntryTable.LOSSES,
            leagueEntryTable.VETERAN, leagueEntryTable.WINS)
            .values(record.getQueueid(), record.getRankid(), record.getSummonerid(),
                record.getTierid(), record.getFreshblood(), record.getHotstreak(),
                record.getInactive(),
                record.getLeagueid(), record.getLeaguepoints(), record.getLosses(),
                record.getVeteran(), record.getWins()).execute();
      } else {
        record = fillLeagueEntry(newSummoner, record, leaguePosition, rankRecord, tierRecord);
        record.update();
      }
    }
  }

  private LeagueEntryRecord fillLeagueEntry(Summoner newSummoner, LeagueEntryRecord record,
      com.merakianalytics.orianna.types.core.league.LeagueEntry leaguePosition,
      RankRecord rankRecord, TierRecord tierRecord) {
    record.setFreshblood(leaguePosition.isFreshBlood());
    record.setHotstreak(leaguePosition.isOnHotStreak());
    record.setInactive(leaguePosition.isInactive());
    record.setVeteran(leaguePosition.isVeteran());
    record.setLeagueid(leaguePosition.getLeague().getId());
    record.setLeaguepoints(leaguePosition.getLeaguePoints());
    record.setLosses(leaguePosition.getLosses());
    record.setWins(leaguePosition.getWins());
    record.setSummonerid(newSummoner.getAccountId());
    record.setQueueid(Constants.SOLO_QUEUE_RANKED_ID);
    record.setRankid(rankRecord.getRankid());
    record.setTierid(tierRecord.getTierid());
    return record;
  }

  private DateTime createOrUpdateSummonerRecord(Summoner newSummoner) {
    SummonerRecord record = dsl.selectFrom(
        ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.Summoner.SUMMONER)
        .where(
            ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.Summoner.SUMMONER.ACCOUNTID
                .eq(newSummoner.getAccountId()))
        .fetchAny();

    if (record == null) {
      record = new SummonerRecord();
      record = fillSummonerRecord(newSummoner, record);
      dsl.insertInto(
          ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.Summoner.SUMMONER,
          ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.Summoner.SUMMONER
              .fields())
          .values(record.intoArray()).execute();
      return null;
    } else {
      long lastUpdate = record.getRevisiondate();
      record = fillSummonerRecord(newSummoner, record);
      record.update();
      return millsToDateTime(lastUpdate);
    }
  }

  private SummonerRecord fillSummonerRecord(Summoner newSummoner, SummonerRecord record) {
    record.setAccountid(newSummoner.getAccountId());
    record.setSummonerid(newSummoner.getId());
    record.setName(newSummoner.getName());
    record.setPuuid(newSummoner.getPuuid());
    record.setProfileiconid(newSummoner.getProfileIcon().getId());
    record.setSummonerlevel((long) newSummoner.getLevel());
    record.setRevisiondate(newSummoner.getUpdated().getMillis());
    return record;
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

