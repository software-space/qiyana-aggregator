package ca.softwarespace.riot.dataaggregator.RiotModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: Steve Mbiele
 * Date: 5/15/2019
 */

@Entity
@Table(name = "match_reference")
public class MatchReference {

    @Id
    @Column(name = "game_id")
    private long gameId;

    @Column(name = "lane")
    private String lane;

    @Column(name = "platform_id")
    private String platformId;

    @Column
    private String role;

    @Column
    private int champion;

    @Column
    private int queue;

    @Column
    private long timestamp;

    @Column
    private int season;


    public MatchReference(long gameId, String lane, String platformId, String role, int champion, int queue,
                          long timestamp, int season) {
        this.gameId = gameId;
        this.lane = lane;
        this.platformId = platformId;
        this.role = role;
        this.champion = champion;
        this.queue = queue;
        this.timestamp = timestamp;
        this.season = season;
    }

    public MatchReference() {
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public int getChampion() {
        return champion;
    }

    public void setChampion(int champion) {
        this.champion = champion;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }
}
