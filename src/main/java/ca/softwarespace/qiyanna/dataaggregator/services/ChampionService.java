package ca.softwarespace.qiyanna.dataaggregator.services;

import ca.softwarespace.qiyanna.dataaggregator.models.AggregatedChampionDto;
import ca.softwarespace.qiyanna.dataaggregator.models.ChampionDto;
import ca.softwarespace.qiyanna.dataaggregator.util.RegionUtil;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.match.ParticipantStats;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.joda.time.Duration;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ChampionService {

  public CompletableFuture<List<AggregatedChampionDto>> getAllChampionStatsBySummonerName(String summonerName, String regionName) {
    Region region = RegionUtil.getRegionByTag(regionName);
    Summoner summoner = Orianna.summonerNamed(summonerName)
        .withRegion(region)
        .get();
    // TODO include queue in request when orianna is updated to have updated queue ids
    MatchHistory matches = MatchHistory.forSummoner(summoner)
        .withSeasons(Season.getLatest())
        .withQueues(Queue.TEAM_BUILDER_RANKED_SOLO) // TODO update with actual ranked solo as soon as orianna gets updated
        .get();
    Map<String, List<ChampionDto>> championsGroupedByName = groupChampionsByName(summoner, matches);
    List<AggregatedChampionDto> champions = aggregateStats(championsGroupedByName);
    return CompletableFuture.completedFuture(champions);
  }

  private Map<String, List<ChampionDto>> groupChampionsByName(Summoner summoner, MatchHistory matches) {
    List<ChampionDto> champions = new ArrayList<>();
    for (Match match : matches) {
      Duration duration = match.getDuration();
      Optional<ChampionDto> champion = match.getParticipants().stream()
          .filter(participant -> participant.getSummoner().getAccountId().equals(summoner.getAccountId()))
          .map(participant -> this.buildChampionDto(participant, duration))
          .filter(Objects::nonNull)
          .findFirst();
      if (champion.isPresent()) {
        champions.add(champion.get());
      }
    }
    return champions.stream()
        .collect(Collectors.groupingBy(ChampionDto::getName));
  }

  public List<AggregatedChampionDto> aggregateStats(Map<String, List<ChampionDto>> championsGroupedByName) {
    List<AggregatedChampionDto> champions = new ArrayList<>();

    for (Map.Entry<String, List<ChampionDto>> entry : championsGroupedByName.entrySet()) {
      AggregatedChampionDto aggregatedChampionDto = AggregatedChampionDto.builder()
          .name(entry.getKey())
          .build();
      final int championCountByName = entry.getValue().size();
      int wins = 0;
      int losses = 0;
      for (ChampionDto champion : entry.getValue()) {
        aggregatedChampionDto.setKills(aggregatedChampionDto.getKills() + champion.getKills());
        aggregatedChampionDto.setDeaths(aggregatedChampionDto.getDeaths() + champion.getDeaths());
        aggregatedChampionDto.setAssists(aggregatedChampionDto.getAssists() + champion.getAssists());
        aggregatedChampionDto.setCs(aggregatedChampionDto.getCs() + champion.getCs());
        aggregatedChampionDto.setGold(aggregatedChampionDto.getGold() + champion.getGold());
        aggregatedChampionDto.setCsPerMin(aggregatedChampionDto.getCsPerMin() + champion.getCsPerMin());
        if (champion.isWinner()) {
          wins++;
        } else {
          losses++;
        }
      }
      aggregatedChampionDto.setKills(aggregatedChampionDto.getKills() / championCountByName);
      aggregatedChampionDto.setDeaths(aggregatedChampionDto.getDeaths() / championCountByName);
      aggregatedChampionDto.setAssists(aggregatedChampionDto.getAssists() / championCountByName);
      aggregatedChampionDto.setCs(aggregatedChampionDto.getCs() / championCountByName);
      aggregatedChampionDto.setGold(aggregatedChampionDto.getGold() / championCountByName);
      aggregatedChampionDto.setCsPerMin(aggregatedChampionDto.getCsPerMin() / championCountByName);
      aggregatedChampionDto.setPlayed(championCountByName);
      aggregatedChampionDto.setWins(wins);
      aggregatedChampionDto.setLosses(losses);
      double winrate = (double) wins / ((double) wins + (double) losses);
      aggregatedChampionDto.setWinrate(winrate);
      champions.add(aggregatedChampionDto);
    }
    return champions.stream()
        .sorted(Comparator.comparing(AggregatedChampionDto::getPlayed).reversed())
        .collect(Collectors.toList());
  }

  private ChampionDto buildChampionDto(Participant participant, Duration matchDuration) {
    ParticipantStats stats = participant.getStats();
    if (stats == null) { // TODO check why stats are sometimes null
      return null;
    }

    int cs = stats.getCreepScore() + stats.getNeutralMinionsKilled();
    double matchDurationInMinutes = matchDuration.getStandardMinutes();
    double csPerMin = cs / matchDurationInMinutes;

    return ChampionDto.builder()
        .name(participant.getChampion().getName())
        .kills(stats.getKills())
        .deaths(stats.getDeaths())
        .assists(stats.getAssists())
        .cs(cs)
        .isWinner(participant.getTeam().isWinner())
        .gold(stats.getGoldEarned())
        .csPerMin(csPerMin)
        .build();
  }
}