package ca.softwarespace.qiyanna.dataaggregator.Repositories;

import ca.softwarespace.qiyanna.dataaggregator.RiotModels.MatchList;
import ca.softwarespace.qiyanna.dataaggregator.RiotModels.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Steve Mbiele
 * Date: 5/15/2019
 */
public interface MatchListRepository extends JpaRepository<MatchList, Long> {
    Boolean existsBySummoner(Summoner summoner);
    MatchList findBySummoner(Summoner summoner);
}
