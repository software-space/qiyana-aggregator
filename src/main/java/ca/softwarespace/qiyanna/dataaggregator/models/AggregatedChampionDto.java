package ca.softwarespace.qiyanna.dataaggregator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AggregatedChampionDto {

  private String accountId;
  private String name;

  private int played;
  private int wins;
  private int losses;
  private double winrate;

  private double averageKills;
  private double averageDeaths;
  private double averageAssists;
  private double averageCs;
  private double averageGold;
  private double averageCsPerMin;


  // TODO refactor. no logic in models
  public static AggregatedChampionDao from(AggregatedChampionDto aggregatedChampionDto) {
    return AggregatedChampionDao.builder()
        .accountId(aggregatedChampionDto.getAccountId())
        .name(aggregatedChampionDto.getName())
        .played(aggregatedChampionDto.getPlayed())
        .wins(aggregatedChampionDto.getWins())
        .losses(aggregatedChampionDto.getLosses())
        .winrate(aggregatedChampionDto.getWinrate())
        .averageKills(aggregatedChampionDto.getAverageKills())
        .averageDeaths(aggregatedChampionDto.getAverageDeaths())
        .averageAssists(aggregatedChampionDto.getAverageAssists())
        .averageCs(aggregatedChampionDto.getAverageCs())
        .averageGold(aggregatedChampionDto.getAverageGold())
        .averageCsPerMin(aggregatedChampionDto.getAverageCsPerMin())
        .build();
  }
}
