package ca.softwarespace.qiyanna.dataaggregator.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChampionStatsDto {

  private int championStatsId;
  private int championId;
  private int laneId;
  private int queueId;
  private int tierId;
  private int patchId;
  private int regionId;
  private double winRate;
  private double banRate;
  private double pickRate;
  private int roleId;
}
