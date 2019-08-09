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
  private double kills;
  private double deaths;
  private double assists;
  private double cs;
  private double gold;
  private double csPerMin;
}
