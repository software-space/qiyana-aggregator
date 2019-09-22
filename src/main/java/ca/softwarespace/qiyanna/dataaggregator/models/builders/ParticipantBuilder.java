package ca.softwarespace.qiyanna.dataaggregator.models.builders;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.Participant;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.Stats;

public class ParticipantBuilder {

    private Participant participant;

    public ParticipantBuilder() {
        this.participant = new Participant();
    }

    public ParticipantBuilder withChampionId(int championId) {
        this.participant.setChampionId(championId);
        return this;
    }

    public ParticipantBuilder withStats(Stats stats) {
        this.participant.setStats(stats);
        return this;
    }

    public Participant build(){
        return this.participant;
    }
}
