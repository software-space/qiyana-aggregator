package ca.softwarespace.qiyanna.dataaggregator.services;

import ca.softwarespace.qiyanna.dataaggregator.models.ChampionDto;
import ca.softwarespace.qiyanna.dataaggregator.models.SummonerDto;
import ca.softwarespace.qiyanna.dataaggregator.util.RegionUtil;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.match.ParticipantStats;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class SummonerService {

  private final MatchesCollectionService matchesCollectionService;

  public SummonerDto getSummonerByName(String name, String regionName) {
    Region region = RegionUtil.getRegionByTag(regionName);
    Summoner summoner = Orianna.summonerNamed(name).withRegion(region).get();

    int wins = summoner.getLeaguePosition(Queue.RANKED_SOLO_5x5).getWins();
    int losses = summoner.getLeaguePosition(Queue.RANKED_SOLO_5x5).getLosses();
    double winrate = (double) wins / ((double) wins + (double) losses);

    return SummonerDto.builder()
        .name(summoner.getName())
        .level(summoner.getLevel())
        .tier(summoner.getLeaguePosition(Queue.RANKED_SOLO_5x5).getTier())
        .division(summoner.getLeaguePosition(Queue.RANKED_SOLO_5x5).getDivision())
        .leaguePoints(summoner.getLeaguePosition(Queue.RANKED_SOLO_5x5).getLeaguePoints())
        .wins(wins)
        .losses(losses)
        .winrate(winrate)
        .build();
  }
}
