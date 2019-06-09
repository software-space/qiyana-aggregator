package ca.softwarespace.riot.dataaggregator.RiotModels;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Steve Mbiele
 * Date: 6/9/2019
 */
@Entity
@Table(name = "Ban")
public class Ban {

    private int id;

    private int pickTurn;

    private int championId;

    @JsonIgnore
    private TeamStats teamStats;
}
