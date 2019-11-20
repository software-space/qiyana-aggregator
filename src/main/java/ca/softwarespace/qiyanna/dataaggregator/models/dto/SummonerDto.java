package ca.softwarespace.qiyanna.dataaggregator.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SummonerDto {

  private String accountId;
  private String name;
  private String puuid;
  private String summonerId;
  private int profileIconId;
  private long summonerLevel;
  private long revisionDate;
}
