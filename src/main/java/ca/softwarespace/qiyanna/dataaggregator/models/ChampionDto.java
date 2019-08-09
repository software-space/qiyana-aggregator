package ca.softwarespace.qiyanna.dataaggregator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChampionDto {

  private String name;
  private int cs;
  private int kills;
  private int deaths;
  private int assists;
  private int neutralMinionsKilled;
  private boolean isWinner;
  private int gold;

}
