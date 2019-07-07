package ca.softwarespace.riot.dataaggregator.RiotModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Author: Steve Mbiele
 * Date: 6/9/2019
 */
@Entity
@Table(name = "ParticipantIdentity")
public class ParticipantIdentity {

    @Id
    @Column(name = "id")
    @JsonIgnore
    @GeneratedValue(generator = "participantIdentity_generator")
    @SequenceGenerator(
            name = "participantIdentity_generator",
            sequenceName = "participantIdentity_sequence",
            initialValue = 1000
    )
    private long id;

    @OneToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Summoner player;

    @OneToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Match match;

    @Column(name = "participant_id")
    private int participanId;


    public ParticipantIdentity() {
    }

    public ParticipantIdentity(Summoner player, Match match, int participanId) {
        this.player = player;
        this.match = match;
        this.participanId = participanId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Summoner getPlayer() {
        return player;
    }

    public void setPlayer(Summoner player) {
        this.player = player;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public int getParticipanId() {
        return participanId;
    }

    public void setParticipanId(int participanId) {
        this.participanId = participanId;
    }
}
