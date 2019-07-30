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
//  private int played;
//  private int wins;
//  private int losses;
//  private double winrate;
//  private double averageCs;
  private double kills;
  private double deaths;
  private double assists;
//  private double averageGold;

}
