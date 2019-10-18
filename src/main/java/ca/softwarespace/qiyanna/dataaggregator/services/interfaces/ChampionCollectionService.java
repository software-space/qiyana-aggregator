package ca.softwarespace.qiyanna.dataaggregator.services.interfaces;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

public interface ChampionCollectionService {

  @EventListener(ApplicationReadyEvent.class)
  void init();

}
