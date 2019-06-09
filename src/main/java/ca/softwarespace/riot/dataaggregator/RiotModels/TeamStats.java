package ca.softwarespace.riot.dataaggregator.RiotModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

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
}
