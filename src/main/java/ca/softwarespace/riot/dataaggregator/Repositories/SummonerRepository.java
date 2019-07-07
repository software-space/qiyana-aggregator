package ca.softwarespace.riot.dataaggregator.Repositories;

import ca.softwarespace.riot.dataaggregator.RiotModels.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Steve Mbiele
 * Date: 5/15/2019
 */
@Repository
public interface SummonerRepository  extends JpaRepository<Summoner, String> {
    Summoner findByName(String SummonersName);

}
