package ca.softwarespace.qiyanna.dataaggregator.models.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AggregatedChampionDao {

  @Id
  private String id;
  private String accountId;
  private String name;

  private int played;
  private int wins;
  private int losses;
  private double winRate;

  private double averageKills;
  private double averageDeaths;
  private double averageAssists;
  private double averageCs;
  private double averageGold;
  private double averageCsPerMin;

}
