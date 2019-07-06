package ca.softwarespace.qiyanna.dataaggregator.Repositories;

import ca.softwarespace.qiyanna.dataaggregator.RiotModels.Summoner;
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
