package ca.softwarespace.qiyanna.dataaggregator.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeagueEntryDto {

  private String accountId;
  private String leagueId;
  private long leagueEntryId;
  private int queueId;
  private int rankId;
  private int tierId;
  private int leaguePoints;
  private int wins;
  private int losses;
  private boolean hotStreak;
  private boolean veteran;
  private boolean inactive;
  private boolean freshBlood;
}
