package ca.softwarespace.qiyanna.dataaggregator.services;

import ca.softwarespace.qiyanna.dataaggregator.events.DataCollectionEvent;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.MatchDto;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.Participant;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.Region;
import ca.softwarespace.qiyanna.dataaggregator.repositories.ChampionStatsRepository;
import ca.softwarespace.qiyanna.dataaggregator.services.interfaces.StatsCalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StatsCalculatorServiceImpl implements StatsCalculatorService {

  private ChampionStatsRepository championStatsRepository;
  private ObjectMapper mapper;

  @Autowired
  public StatsCalculatorServiceImpl(ChampionStatsRepository championStatsRepository, ObjectMapper mapper) {
    this.championStatsRepository = championStatsRepository;
    this.mapper = mapper;
    this.mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
  }


  @Override
  public void onApplicationEvent(DataCollectionEvent dataCollectionEvent) {
    log.info(dataCollectionEvent.getMessage());
  }

  public boolean getWinOrLose(MatchDto match, int championId) {
    Optional<Participant> selectedParticipantWithProvidedChampion = match.getParticipants().stream().filter(u -> u.getChampionId() == championId).findFirst();
    return selectedParticipantWithProvidedChampion.map(participant -> participant.getStats().isWin()).orElse(false);
  }

  public double calculatePercentage(long wins, long gamesPlayed) {
    return (double) wins / gamesPlayed * 100;
  }

  @Override
  public double getWinrateForChampionWithIdAndPlatformAndQueueType(int championId, Platform platform, Queue queueType) {
    List<MatchDto> matches = championStatsRepository.findByParticipants_ChampionIdAndPlatformIdAndQueueId(championId, platform.getTag(),queueType.getId());
    long amountOfWins = 0;
    for(MatchDto match : matches) {
      if(getWinOrLose(match,championId)) {
        amountOfWins++;
      }
    }
    return calculatePercentage(amountOfWins, matches.size());
  }
}
