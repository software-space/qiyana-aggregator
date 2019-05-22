package ca.softwarespace.riot.dataaggregator.Repositories;

import ca.softwarespace.riot.dataaggregator.RiotModels.MatchList;
import ca.softwarespace.riot.dataaggregator.RiotModels.MatchReference;
import ca.softwarespace.riot.dataaggregator.RiotModels.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: Steve Mbiele
 * Date: 5/15/2019
 */
public interface MatchListRepository extends JpaRepository<MatchList, Long> {
    Boolean existsBySummoner(Summoner summoner);
    MatchList findBySummoner(Summoner summoner);
//    Boolean existsByMatchReferencesContaining(MatchReference reference);
//    List<MatchList> findAllByMatches(MatchReference reference);
//    @Query("Select game_id from MatchListMatchReference where game_id = ?1  and matchList_id = ?2")
//    List<Long> findMatchListMacthRefByGameIdAndMatchLisId(long gameId, long matchListId);
}
