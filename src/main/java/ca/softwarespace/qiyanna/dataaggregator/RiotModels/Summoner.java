package ca.softwarespace.qiyanna.dataaggregator.RiotModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: Steve Mbiele
 * Date: 5/15/2019
 */

@Entity
@Table(name = "Summoner")
public class Summoner {

    @Id
    private String id;

    @Column(name = "profile_icon_id")
    private int profileIconId;

    @Column(name = "name")
    private String name;

    @Column(name = "puuid")
    private String puuid;

    @Column(name = "summoner_level")
    private long summonerLevel;

    @Column(name = "revision_date")
    private long revisionDate;

    @Column(name = "account_id")
    private String accountId;

    public Summoner(String id, int profileIconId, String name, String puuid, long summonerLevel, long revisionDate,
                    String accountId) {
        this.id = id;
        this.profileIconId = profileIconId;
        this.name = name;
        this.puuid = puuid;
        this.summonerLevel = summonerLevel;
        this.revisionDate = revisionDate;
        this.accountId = accountId;
    }

    public Summoner() {
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
