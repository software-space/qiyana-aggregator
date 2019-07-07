package ca.softwarespace.riot.dataaggregator.RiotModels;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Author: Steve Mbiele
 * Date: 6/9/2019
 */
@Entity
@Table(name = "TeamStats")
public class TeamStats {

    @Id
    @Column(name = "id")
    @JsonIgnore
    @GeneratedValue(generator = "teamStats_generator")
    @SequenceGenerator(
            name = "teamStats_generator",
            sequenceName = "teamStats_sequence",
            initialValue = 1000
    )
    private long id;

    @Column(name = "first_dragon")
    private boolean firstDragon;

    @Column(name = "first_inhibitor")
    private boolean firstInhibitor;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private List<Ban> bans;

    @Column(name = "baron_kills")
    private int baronKills;

    @Column(name = "first_rift_herald")
    private boolean firstRiftHerald;

    @Column(name = "first_baron")
    private boolean firstBaron;

    @Column(name = "rift_herald_kills")
    private int riftHeraldKills;

    @Column(name = "first_blod")
    private boolean firstBlood;

    @Column(name = "team_id")
    private int teamId;

    @Column(name = "first_tower")
    private boolean firstTower;

    @Column(name = "vilemaw_kills")
    private int vilemawKills;

    @Column(name = "inhib_kills")
    private int inhibitorKills;

    @Column(name = "tower_kills")
    private int towerKills;

    @Column(name = "dominion_victory_score")
    private int dominionVictoryScore;

    @Column(name = "win")
    private String win;

    @Column(name = "dragon_kills")
    private int dragonKills;

    public TeamStats() {
    }

    public TeamStats(boolean firstDragon, boolean firstInhibitor, List<Ban> bans, int baronKills,
                     boolean firstRiftHerald, boolean firstBaron, int riftHeraldKills, boolean firstBlood, int teamId,
                     boolean firstTower, int vilemawKills, int inhibitorKills, int towerKills, int dominionVictoryScore,
                     String win, int dragonKills) {
        this.firstDragon = firstDragon;
        this.firstInhibitor = firstInhibitor;
        this.bans = bans;
        this.baronKills = baronKills;
        this.firstRiftHerald = firstRiftHerald;
        this.firstBaron = firstBaron;
        this.riftHeraldKills = riftHeraldKills;
        this.firstBlood = firstBlood;
        this.teamId = teamId;
        this.firstTower = firstTower;
        this.vilemawKills = vilemawKills;
        this.inhibitorKills = inhibitorKills;
        this.towerKills = towerKills;
        this.dominionVictoryScore = dominionVictoryScore;
        this.win = win;
        this.dragonKills = dragonKills;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isFirstDragon() {
        return firstDragon;
    }

    public void setFirstDragon(boolean firstDragon) {
        this.firstDragon = firstDragon;
    }

    public boolean isFirstInhibitor() {
        return firstInhibitor;
    }

    public void setFirstInhibitor(boolean firstInhibitor) {
        this.firstInhibitor = firstInhibitor;
    }

    public List<Ban> getBans() {
        return bans;
    }

    public void setBans(List<Ban> bans) {
        this.bans = bans;
    }

    public int getBaronKills() {
        return baronKills;
    }

    public void setBaronKills(int baronKills) {
        this.baronKills = baronKills;
    }

    public boolean isFirstRiftHerald() {
        return firstRiftHerald;
    }

    public void setFirstRiftHerald(boolean firstRiftHerald) {
        this.firstRiftHerald = firstRiftHerald;
    }

    public boolean isFirstBaron() {
        return firstBaron;
    }

    public void setFirstBaron(boolean firstBaron) {
        this.firstBaron = firstBaron;
    }

    public int getRiftHeraldKills() {
        return riftHeraldKills;
    }

    public void setRiftHeraldKills(int riftHeraldKills) {
        this.riftHeraldKills = riftHeraldKills;
    }

    public boolean isFirstBlood() {
        return firstBlood;
    }

    public void setFirstBlood(boolean firstBlood) {
        this.firstBlood = firstBlood;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public boolean isFirstTower() {
        return firstTower;
    }

    public void setFirstTower(boolean firstTower) {
        this.firstTower = firstTower;
    }

    public int getVilemawKills() {
        return vilemawKills;
    }

    public void setVilemawKills(int vilemawKills) {
        this.vilemawKills = vilemawKills;
    }

    public int getInhibitorKills() {
        return inhibitorKills;
    }

    public void setInhibitorKills(int inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    public int getTowerKills() {
        return towerKills;
    }

    public void setTowerKills(int towerKills) {
        this.towerKills = towerKills;
    }

    public int getDominionVictoryScore() {
        return dominionVictoryScore;
    }

    public void setDominionVictoryScore(int dominionVictoryScore) {
        this.dominionVictoryScore = dominionVictoryScore;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public int getDragonKills() {
        return dragonKills;
    }

    public void setDragonKills(int dragonKills) {
        this.dragonKills = dragonKills;
    }
}
