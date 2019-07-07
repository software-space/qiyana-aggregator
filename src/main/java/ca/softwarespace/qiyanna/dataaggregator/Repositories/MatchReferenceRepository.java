package ca.softwarespace.qiyanna.dataaggregator.Repositories;

import ca.softwarespace.qiyanna.dataaggregator.RiotModels.MatchList;
import ca.softwarespace.qiyanna.dataaggregator.RiotModels.MatchReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: Steve Mbiele
 * Date: 5/15/2019
 */
public interface MatchReferenceRepository extends JpaRepository <MatchReference, Long> {
    List<MatchReference> findAllByMatchLists(MatchList matchList);
    MatchReference findByGameIdAndAndMatchLists(long gameId, MatchList matchList);
}
