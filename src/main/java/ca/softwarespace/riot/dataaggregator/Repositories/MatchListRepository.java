package ca.softwarespace.riot.dataaggregator.Repositories;

import ca.softwarespace.riot.dataaggregator.RiotModels.MatchList;
import ca.softwarespace.riot.dataaggregator.RiotModels.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Steve Mbiele
 * Date: 5/15/2019
 */
public interface MatchListRepository extends JpaRepository<MatchList, Long> {
    Boolean existsBySummoner(Summoner summoner);
    MatchList findBySummoner(Summoner summoner);
}
