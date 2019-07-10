package ca.softwarespace.qiyanna.dataaggregator.services;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashSet;


/**
 * Author: Steve Mbiele
 * Date: 5/15/2019
 */

@Service
public class MatchesCollectionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchesCollectionService.class);


    private MatchHistory filterMatchHistory(Summoner summoner) {
        return Orianna.matchHistoryForSummoner(summoner).withSeasons(Season.getLatest()).get();
    }

    @Async
    public void oriannaTest(String summonerName) {
        Summoner summoner =  Orianna.summonerNamed(summonerName).get();
        Region region = summoner.getRegion();
        aggregate(summoner, region);
    }

    @Async
    public void oriannaTest(String summonerName, String regionName){
        Summoner summoner =  Orianna.summonerNamed(summonerName).withRegion(Region.valueOf(regionName)).get();
        Region region = summoner.getRegion();
        aggregate(summoner, region);
    }

    private void aggregate(Summoner summoner, Region region) {
        HashSet<String> unpulledSummonerIds = new HashSet<>();
        unpulledSummonerIds.add(summoner.getId());

        HashSet<String> pulledSummonerIds = new HashSet<>();
        HashSet<Long> unpulledMatchIds = new HashSet<>();
        HashSet<Long> pulledMatchIds = new HashSet<>();

        while (!unpulledSummonerIds.isEmpty()) {
            // Get a new summoner from our list of unpulled summoners and pull their match history
            final String newSummonerId = unpulledSummonerIds.iterator().next();
            final Summoner newSummoner = Summoner.withId(newSummonerId).withRegion(region).get();
            final MatchHistory matches = filterMatchHistory(newSummoner);
            for (final Match match : matches) {
                if (!pulledMatchIds.contains(match.getId())) {
                    unpulledMatchIds.add(match.getId());
                }
            }
            unpulledSummonerIds.remove(newSummonerId);
            pulledSummonerIds.add(newSummonerId);

            while (!unpulledMatchIds.isEmpty()) {
                // Get a random match from our list of matches
                final long newMatchId = unpulledMatchIds.iterator().next();
                final Match newMatch = Match.withId(newMatchId).withRegion(region).get();
                for (final Participant p : newMatch.getParticipants()) {
                    if (!pulledSummonerIds.contains(p.getSummoner().getId())) {
                        unpulledSummonerIds.add(p.getSummoner().getId());
                    }
                }
                // The above lines will trigger the match to load its data by iterating over all the participants.
                // If you have a database in your datapipeline, the match will automatically be stored in it.
                unpulledMatchIds.remove(newMatchId);
                pulledMatchIds.add(newMatchId);
            }
        }
    }
}

