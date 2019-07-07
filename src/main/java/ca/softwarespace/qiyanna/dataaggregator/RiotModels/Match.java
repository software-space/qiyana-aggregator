package ca.softwarespace.qiyanna.dataaggregator.RiotModels;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Steve Mbiele
 * Date: 6/9/2019
 */
@Entity
@Table(name = "Match")
public class Match {

    @Id
    @Column(name = "game_id")
    private int gameId;

    @Column(name = "season_id")
    private int seasonId;

    @Column(name = "queue_id")
    private int queueId;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ParticipantIdentity> participantIdentities;

    @Column(name = "game_version")
    private String gameVersion;

    @Column(name = "platform_id")
    private String platformId;

    @Column(name = "game_mode")
    private String gameMode;

    @Column(name = "map_id")
    private int mapId;

    @Column(name = "game_type")
    private String gameType;

    @ManyToMany()
    private List<TeamStats> teams;

    @ManyToMany()
    private List<Participant> participants;

    @Column(name = "game_duration")
    private long gameDuration;

    @Column(name = "game_creation")
    private long gameCreation;

    public Match() {
    }

    public Match(int seasonId, int queueId, int gameId, List<ParticipantIdentity> participantIdentities,
                 String gameVersion, String platformId, String gameMode, int mapId, String gameType, List<TeamStats> teamStats,
                 List<Participant> participants, long gameDuration, long gameCreation) {
        this.seasonId = seasonId;
        this.queueId = queueId;
        this.gameId = gameId;
        this.participantIdentities = participantIdentities;
        this.gameVersion = gameVersion;
        this.platformId = platformId;
        this.gameMode = gameMode;
        this.mapId = mapId;
        this.gameType = gameType;
        this.teams = teamStats;
        this.participants = participants;
        this.gameDuration = gameDuration;
        this.gameCreation = gameCreation;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public List<ParticipantIdentity> getParticipantIdentities() {
        return participantIdentities;
    }

    public void setParticipantIdentities(List<ParticipantIdentity> participantIdentities) {
        this.participantIdentities = participantIdentities;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public List<TeamStats> getTeamStats() {
        return teams;
    }

    public void setTeamStats(List<TeamStats> teamStats) {
        this.teams = teamStats;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public long getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(long gameDuration) {
        this.gameDuration = gameDuration;
    }

    public long getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(long gameCreation) {
        this.gameCreation = gameCreation;
    }

    public void addParticipantIdentity(ParticipantIdentity participantIdentity) {
        if (participantIdentities == null)
            participantIdentities = new ArrayList<>();

        participantIdentities.add(participantIdentity);
    }

    public void addTeam(TeamStats teamStats) {
        if (this.teams == null)
            this.teams = new ArrayList<>();
        this.teams.add(teamStats);
    }

    public void addParticipant(Participant participant) {
        if (participants == null)
            participants = new ArrayList<>();
        participants.add(participant);
    }
}
