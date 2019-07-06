package ca.softwarespace.qiyanna.dataaggregator.RiotModels;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Author: Steve Mbiele
 * Date: 6/9/2019
 */
@Entity
@Table(name = "participant")
public class Participant {

    @Id
    @Column(name = "id")
    @JsonIgnore
    @GeneratedValue(generator = "participant_generator")
    @SequenceGenerator(
            name = "participant_generator",
            sequenceName = "participant_sequence",
            initialValue = 1000
    )
    private int id;

    @OneToOne
    @JoinColumn
    private ParticipantStats stats;

    @Column(name = "participant_id")
    private int participanId;

    @OneToOne
    @JoinColumn
    private ParticipantTimeline timeline;

    @Column(name = "team_id")
    private int teamId;

    @Column
    private int spell2Id;

    @Column
    private String highestAchievedSeasonTier;

    @Column
    private int spell1Id;

    @Column
    private int championId;

    public Participant() {
    }

    public Participant(ParticipantStats stats, int participanId, ParticipantTimeline timeline, int teamId, int spell2Id,
                       String highestAchievedSeasonTier, int spell1Id, int championId) {
        this.stats = stats;
        this.participanId = participanId;
        this.timeline = timeline;
        this.teamId = teamId;
        this.spell2Id = spell2Id;
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
        this.spell1Id = spell1Id;
        this.championId = championId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParticipantStats getStats() {
        return stats;
    }

    public void setStats(ParticipantStats stats) {
        this.stats = stats;
    }

    public int getParticipanId() {
        return participanId;
    }

    public void setParticipanId(int participanId) {
        this.participanId = participanId;
    }

    public ParticipantTimeline getTimeline() {
        return timeline;
    }

    public void setTimeline(ParticipantTimeline timeline) {
        this.timeline = timeline;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getSpell2Id() {
        return spell2Id;
    }

    public void setSpell2Id(int spell2Id) {
        this.spell2Id = spell2Id;
    }

    public String getHighestAchievedSeasonTier() {
        return highestAchievedSeasonTier;
    }

    public void setHighestAchievedSeasonTier(String highestAchievedSeasonTier) {
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
    }

    public int getSpell1Id() {
        return spell1Id;
    }

    public void setSpell1Id(int spell1Id) {
        this.spell1Id = spell1Id;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }
}
