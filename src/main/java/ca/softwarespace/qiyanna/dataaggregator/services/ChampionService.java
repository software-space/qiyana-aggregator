package ca.softwarespace.qiyanna.dataaggregator.services;

import ca.softwarespace.qiyanna.dataaggregator.models.ChampionDto;
import ca.softwarespace.qiyanna.dataaggregator.util.RegionUtil;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.match.ParticipantStats;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ChampionService {

  public CompletableFuture<List<ChampionDto>> getAllChampionStatsBySummonerName(
      String summonerName,
      String regionName) {
    Region region = RegionUtil.getRegionByTag(regionName);
    Summoner summoner = Orianna.summonerNamed(summonerName).withRegion(region).get();

    MatchHistory matches = MatchHistory.forSummoner(summoner).withSeasons(Season.getLatest())
        .get();

    Map<String, List<ChampionDto>> championsGroupedByName = groupChampionsByName(summoner, matches);

    List<ChampionDto> champions = calculateAverageStats(championsGroupedByName);

    return CompletableFuture.completedFuture(champions);
  }

  private Map<String, List<ChampionDto>> groupChampionsByName(Summoner summoner,
      MatchHistory matches) {
    return matches.stream()
        .flatMap(match -> match.getParticipants().stream())
        .filter(
            participant -> participant.getSummoner().getAccountId().equals(summoner.getAccountId()))
        .map(this::buildChampionDto)
        .filter(Objects::nonNull)
        .collect(Collectors.groupingBy(ChampionDto::getName));
  }

  public List<ChampionDto> calculateAverageStats(Map<String, List<ChampionDto>> championsGroupedByName) {
    List<ChampionDto> champions = new ArrayList<>();

    for (Map.Entry<String, List<ChampionDto>> entry : championsGroupedByName.entrySet()) {
      ChampionDto sumChampion = ChampionDto.builder()
          .name(entry.getKey())
          .build();
      final int championCountByName = entry.getValue().size();
      for (ChampionDto champion : entry.getValue()) {
        sumChampion.setKills(sumChampion.getKills() + champion.getKills());
        sumChampion.setDeaths(sumChampion.getDeaths() + champion.getDeaths());
        sumChampion.setAssists(sumChampion.getAssists() + champion.getAssists());
      }
      sumChampion.setKills(sumChampion.getKills() / championCountByName);
      sumChampion.setDeaths(sumChampion.getDeaths() / championCountByName);
      sumChampion.setAssists(sumChampion.getAssists() / championCountByName);
      champions.add(sumChampion);
    }

    return champions;
  }

  private ChampionDto buildChampionDto(Participant participant) {
    ParticipantStats stats = participant.getStats();
    if (stats == null) { // TODO check why stats are sometimes null
      return null;
    }

    return ChampionDto.builder()
        .name(participant.getChampion().getName())
        .kills(stats.getKills())
        .deaths(stats.getDeaths())
        .assists(stats.getAssists())
        .build();
  }

}
