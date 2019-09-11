package ca.softwarespace.qiyanna.dataaggregator.util;

import ca.softwarespace.qiyanna.dataaggregator.models.dao.AggregatedChampionDto;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.ChampionDto;
import java.util.function.Consumer;

public class AggregatedChampionConsumer implements Consumer<ChampionDto> {

  private AggregatedChampionDto aggregatedChampionDto = AggregatedChampionDto.builder().build();
  private int count = 0;

  @Override
  public void accept(ChampionDto championDto) {
    aggregatedChampionDto.setName(championDto.getName());
    aggregatedChampionDto.setAccountId(championDto.getAccountId());
    aggregatedChampionDto
        .setAverageKills(aggregatedChampionDto.getAverageKills() + championDto.getKills());
    aggregatedChampionDto
        .setAverageDeaths(aggregatedChampionDto.getAverageDeaths() + championDto.getDeaths());
    aggregatedChampionDto
        .setAverageAssists(aggregatedChampionDto.getAverageAssists() + championDto.getAssists());
    aggregatedChampionDto.setAverageCs(aggregatedChampionDto.getAverageCs() + championDto.getCs());
    aggregatedChampionDto
        .setAverageGold(aggregatedChampionDto.getAverageGold() + championDto.getGold());
    aggregatedChampionDto
        .setAverageCsPerMin(aggregatedChampionDto.getAverageCsPerMin() + championDto.getCsPerMin());
    aggregatedChampionDto
        .setWins(aggregatedChampionDto.getWins() + (championDto.isWinner() ? 1 : 0));
    aggregatedChampionDto
        .setLosses(aggregatedChampionDto.getLosses() + (championDto.isWinner() ? 0 : 1));
    count++;
  }

  public void combine(AggregatedChampionConsumer other) {
    aggregatedChampionDto.setAverageKills(
        aggregatedChampionDto.getAverageKills() + other.aggregatedChampionDto.getAverageKills());
    aggregatedChampionDto.setAverageDeaths(
        aggregatedChampionDto.getAverageDeaths() + other.aggregatedChampionDto.getAverageDeaths());
    aggregatedChampionDto.setAverageAssists(
        aggregatedChampionDto.getAverageAssists() + other.aggregatedChampionDto
            .getAverageAssists());
    aggregatedChampionDto.setAverageCs(
        aggregatedChampionDto.getAverageCs() + other.aggregatedChampionDto.getAverageAssists());
    aggregatedChampionDto.setAverageGold(
        aggregatedChampionDto.getAverageGold() + other.aggregatedChampionDto.getAverageGold());
    aggregatedChampionDto.setAverageCsPerMin(
        aggregatedChampionDto.getAverageGold() + other.aggregatedChampionDto.getAverageCsPerMin());
    aggregatedChampionDto
        .setWins(aggregatedChampionDto.getWins() + other.aggregatedChampionDto.getWins());
    aggregatedChampionDto
        .setLosses(aggregatedChampionDto.getLosses() + other.aggregatedChampionDto.getLosses());
    count += other.count;
  }

  public AggregatedChampionDto getAggregatedChampionDto() {
    aggregatedChampionDto.setAverageKills(aggregatedChampionDto.getAverageKills() / count);
    aggregatedChampionDto.setAverageDeaths(aggregatedChampionDto.getAverageDeaths() / count);
    aggregatedChampionDto.setAverageAssists(aggregatedChampionDto.getAverageAssists() / count);
    aggregatedChampionDto.setAverageCs(aggregatedChampionDto.getAverageCs() / count);
    aggregatedChampionDto.setAverageGold(aggregatedChampionDto.getAverageGold() / count);
    aggregatedChampionDto.setAverageCsPerMin(aggregatedChampionDto.getAverageCsPerMin() / count);
    aggregatedChampionDto.setPlayed(count);
    double winrate =
        (double) aggregatedChampionDto.getWins() / ((double) aggregatedChampionDto.getWins()
            + (double) aggregatedChampionDto.getLosses());
    aggregatedChampionDto.setWinRate(winrate);
    return aggregatedChampionDto;
  }
}
