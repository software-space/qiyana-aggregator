package ca.softwarespace.qiyanna.dataaggregator.RiotModels;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Author: Steve Mbiele
 * Date: 6/9/2019
 */
@Entity
@Table(name = "Ban")
public class Ban {

    @Id
    @Column(name = "id")
    @JsonIgnore
    @GeneratedValue(generator = "ban_generator")
    @SequenceGenerator(
            name = "ban_generator",
            sequenceName = "ban_sequence",
            initialValue = 1000
    )
    private int id;

    @Column(name = "pick_turn")
    private int pickTurn;

    @Column(name = "champion_id")
    private int championId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @JsonIgnore
    private TeamStats teamStats;

    public Ban() {
    }

    public Ban(int pickTurn, int championId, TeamStats teamStats) {
        this.pickTurn = pickTurn;
        this.championId = championId;
        this.teamStats = teamStats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPickTurn() {
        return pickTurn;
    }

    public void setPickTurn(int pickTurn) {
        this.pickTurn = pickTurn;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public TeamStats getTeamStats() {
        return teamStats;
    }

    public void setTeamStats(TeamStats teamStats) {
        this.teamStats = teamStats;
    }
}
