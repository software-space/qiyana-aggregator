package ca.softwarespace.qiyanna.dataaggregator.models.builders;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.MatchDto;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.Participant;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.ParticipantIdentity;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.Team;

import java.util.Date;
import java.util.List;

public class MatchDtoBuilder {

    private MatchDto match;

    public MatchDtoBuilder() {
            match = new MatchDto();
    }

    public MatchDtoBuilder withGameCreation(Long gameCreation) {
        match.setGameCreation(gameCreation);
        return this;
    }

    public MatchDtoBuilder withGameDuration(Long gameDuration) {
        match.setGameDuration(gameDuration);
        return this;
    }

    public MatchDtoBuilder withGameId(Long gameId) {
        match.setGameId(gameId);
        return this;
    }

    public MatchDtoBuilder withGameMode(String gameMode) {
        match.setGameMode(gameMode);
        return this;
    }

    public MatchDtoBuilder withGameType(String gameType) {
        match.setGameType(gameType);
        return this;
    }

    public MatchDtoBuilder withGameVersion(String gameVersion) {
        match.setGameVersion(gameVersion);
        return this;
    }

    public MatchDtoBuilder withMapId(int mapId) {
        match.setMapId(mapId);
        return this;
    }

    public MatchDtoBuilder withParticipantIdentities(List<ParticipantIdentity> participantIdentities) {
        match.setParticipantIdentities(participantIdentities);
        return this;
    }

    public MatchDtoBuilder withParticipants(List<Participant> participants) {
        match.setParticipants(participants);
        return this;
    }

    public MatchDtoBuilder withPlatformId(String platformId) {
        match.setPlatformId(platformId);
        return this;
    }

    public MatchDtoBuilder withQueueId(int queueId) {
        match.setQueueId(queueId);
        return this;
    }

    public MatchDtoBuilder withSeasonId(int seasonId) {
        match.setSeasonId(seasonId);
        return this;
    }

    public MatchDtoBuilder withTeams(List<Team> teams) {
        match.setTeams(teams);
        return this;
    }

    public MatchDtoBuilder with_updated(Date updated) {
        match.set_updated(updated);
        return this;
    }

    public MatchDtoBuilder with_included_data_hash(long included_data_hash) {
        match.set_included_data_hash(included_data_hash);
        return this;
    }

    public MatchDto build() {
        return this.match;
    }



}
