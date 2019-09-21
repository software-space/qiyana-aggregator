package ca.softwarespace.qiyanna.dataaggregator.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MatchDto {
  private ObjectId _id;
  private long gameCreation;
  private long gameDuration;
  private long gameId;
  private String gameMode;
  private String gameType;
  private String gameVersion;
  private int mapId;
  private List<ParticipantIdentity> participantIdentities;
  private List<Participant> participants;
  private String platformId;
  private int queueId;
  private int seasonId;
  private List<Team> teams;
  private Date _updated;
  private long _included_data_hash;
}
