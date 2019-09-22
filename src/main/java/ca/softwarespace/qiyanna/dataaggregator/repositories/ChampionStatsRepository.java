package ca.softwarespace.qiyanna.dataaggregator.repositories;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.MatchDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionStatsRepository extends MongoRepository<MatchDto, String> {
    List<MatchDto> findByParticipants_ChampionIdAndPlatformIdAndQueueId(int ChampionId, String platformTag, int queueId);
    long countAllByParticipants_ChampionIdAndPlatformIdAndQueueId(int championId, String platformTag, int queueId);
    long countAllByTeams_Bans_ChampionIdAndPlatformIdAndQueueId(int championId, String platformTag, int queuId);
}
