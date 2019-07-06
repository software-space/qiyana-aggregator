package ca.softwarespace.qiyanna.dataaggregator.RiotModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Author: Steve Mbiele
 * Date: 5/15/2019
 */

@Entity
@Table(name = "MatchList")
public class MatchList {

    @Id
    @Column(name = "id")
    @JsonIgnore
    @GeneratedValue(generator = "matchlist_generator")
    @SequenceGenerator(
            name = "matchlist_generator",
            sequenceName = "matchlist_sequence",
            initialValue = 1000
    )
    private long id;

    @Column(name = "total_games")
    private int totalGames;

    @Column(name = "start_index")
    private int startIndex;

    @Column(name = "end_index")
    private int endIndex;

    @OneToOne
    @JoinColumn(name = "summoner_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Summoner summoner;

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public Summoner getSummoner() {
        return summoner;
    }

    public void setSummoner(Summoner summoner) {
        this.summoner = summoner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchList matchList = (MatchList) o;
        return id == matchList.id &&
                totalGames == matchList.totalGames &&
                startIndex == matchList.startIndex &&
                endIndex == matchList.endIndex &&
                summoner.getId().equals(matchList.getSummoner().getId());
    }
}
