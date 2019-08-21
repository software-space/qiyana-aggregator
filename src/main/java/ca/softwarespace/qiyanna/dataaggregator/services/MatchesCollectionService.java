package ca.softwarespace.qiyanna.dataaggregator.services;

import ca.softwarespace.qiyanna.dataaggregator.models.MatchDto;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;


/**
 * Author: Steve Mbiele Date: 5/15/2019
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class MatchesCollectionService {

  public MatchHistory filterMatchHistory(Summoner summoner) {
    ArrayList<Queue> queues = new ArrayList<>();
    queues.add(Queue.OVERCHARGE);
    queues.add(Queue.SIEGE);
    queues.add(Queue.NEXUS_SIEGE);
    queues.add(Queue.ARURF_5X5);
    queues.add(Queue.ARURF);
    queues.add(Queue.ARAM_5x5);
    queues.add(Queue.ARAM);
    queues.add(Queue.ARSR_5x5);
    queues.add(Queue.ASCENSION_5x5);
    queues.add(Queue.ASSASSINATE_5x5);
    queues.add(Queue.BILGEWATER_5x5);
    queues.add(Queue.DARKSTAR_3x3);
    queues.add(Queue.FIRSTBLOOD_1x1);
    queues.add(Queue.FIRSTBLOOD_2x2);
    queues.add(Queue.NORMAL_3x3);
    queues.add(Queue.NORMAL_5x5_DRAFT);
    queues.add(Queue.NORMAL_3x3);
    queues.add(Queue.NORMAL_5x5_BLIND);
    queues.add(Queue.NORMAL_3X3_BLIND_PICK);
    queues.add(Queue.ONEFORALL_5x5);
    queues.add(Queue.SR_6x6);
    queues.add(Queue.GROUP_FINDER_5x5);
    queues.add(Queue.KING_PORO_5x5);
    queues.add(Queue.ONEFORALL_MIRRORMODE_5x5);
    queues.add(Queue.RANKED_PREMADE_3x3);
    queues.add(Queue.RANKED_PREMADE_5x5);
    queues.add(Queue.RANKED_SOLO_5x5);
    queues.add(Queue.RANKED_TEAM_3x3);
    queues.add(Queue.RANKED_TEAM_5x5);
    queues.add(Queue.RANKED_FLEX_SR);
    queues.add(Queue.RANKED_FLEX_TT);
    queues.add(Queue.TEAM_BUILDER_DRAFT_RANKED_5x5);
    queues.add(Queue.TEAM_BUILDER_DRAFT_UNRANKED_5x5);
    queues.add(Queue.TEAM_BUILDER_RANKED_SOLO);
    queues.add(Queue.COUNTER_PICK);
    queues.add(Queue.DEFINITELY_NOT_DOMINION_5x5);
    queues.add(Queue.TB_BLIND_SUMMONERS_RIFT_5x5);
    queues.add(Queue.CUSTOM);
    queues.add(Queue.HEXAKILL);
    return Orianna.matchHistoryForSummoner(summoner).withSeasons(Season.getLatest())
        .withQueues(queues).get();
  }

  @Async
  public void oriannaTest(String summonerName) {
    Summoner summoner = Orianna.summonerNamed(summonerName).get();
    Region region = summoner.getRegion();
    aggregate(summoner, region);
  }

  @Async
  public void oriannaTest(String summonerName, String regionName) {
    Summoner summoner = Orianna.summonerNamed(summonerName).withRegion(Region.valueOf(regionName))
        .get();
    Region region = summoner.getRegion();
    aggregate(summoner, region);
  }

  @Async
  public CompletableFuture<Set<MatchDto>> getMatchHistoryBySummoner(Summoner summoner) {
    MatchHistory matches = filterMatchHistory(summoner);
    Set<MatchDto> matchDtos = new HashSet<>();

    for (Match match : matches) {
      Match pulledMatch = Match.withId(match.getId()).get();
      MatchDto matchDto = MatchDto.builder()
          .id(pulledMatch.getId())
          .build();
      matchDtos.add(matchDto);
    }

    return CompletableFuture.completedFuture(matchDtos);
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

