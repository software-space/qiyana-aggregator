package ca.softwarespace.qiyanna.dataaggregator.services;

import ca.softwarespace.qiyanna.dataaggregator.events.ChampionCollectionEvent;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.ChampionDto;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLChampionRepository;
import ca.softwarespace.qiyanna.dataaggregator.services.interfaces.ChampionCollectionService;
import com.merakianalytics.orianna.Orianna;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChampionCollectionServiceImpl implements ChampionCollectionService {

  private ApplicationEventPublisher eventPublisher;
  private final SQLChampionRepository championRepository;

  @Autowired
  public ChampionCollectionServiceImpl(
      ApplicationEventPublisher eventPublisher,
      SQLChampionRepository championRepository) {
    this.eventPublisher = eventPublisher;
    this.championRepository = championRepository;
  }

  @Override
  public void init() {
    List<ChampionDto> champions = championRepository.findAll();
    if (champions == null || champions.isEmpty() || champions.size() < Orianna.getChampions()
        .size()) {
      Orianna.getChampions().stream().forEach(champion -> {
        ChampionDto dto = ChampionDto.builder().id(champion.getId()).name(champion.getName())
            .build();
        championRepository.add(dto);
      });
    }
    ChampionCollectionEvent collectionEvent = new ChampionCollectionEvent(this,
        "Done Collection champions");
    eventPublisher.publishEvent(collectionEvent);
  }
}
