package ca.softwarespace.qiyanna.dataaggregator.services;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.SummonerDto;
import ca.softwarespace.qiyanna.dataaggregator.util.RegionEnum;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Deprecated
public class SummonerService {

  public SummonerDto getSummonerByName(String name, String regionName) {
    Region region = RegionEnum.getRegionByTag(regionName);
    Summoner summoner = Orianna.summonerNamed(name).withRegion(region).get();

    int wins = summoner.getLeaguePosition(Queue.RANKED_SOLO_5X5).getWins();
    int losses = summoner.getLeaguePosition(Queue.RANKED_SOLO_5X5).getLosses();
    double winRate = (double) wins / ((double) wins + (double) losses);

    return SummonerDto.builder()
        .accountId(summoner.getAccountId())
        .name(summoner.getName())
        .level(summoner.getLevel())
        .tier(summoner.getLeaguePosition(Queue.RANKED_SOLO_5X5).getTier())
        .division(summoner.getLeaguePosition(Queue.RANKED_SOLO_5X5).getDivision())
        .leaguePoints(summoner.getLeaguePosition(Queue.RANKED_SOLO_5X5).getLeaguePoints())
        .wins(wins)
        .losses(losses)
        .winRate(winRate)
        .build();
  }
}
