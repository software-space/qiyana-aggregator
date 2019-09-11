package ca.softwarespace.qiyanna.dataaggregator.services.interfaces;

import ca.softwarespace.qiyanna.dataaggregator.events.UpdateMatchesEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

public interface MatchesCollectionService extends ApplicationListener<UpdateMatchesEvent> {

  @EventListener(ApplicationReadyEvent.class)
  void init();

  void prepareDataCollection(String summonerName, String regionName, Integer startSeasonId);

  void prepareDataCollection(String accountId, String region);

  void prepareUpdate(String summonerName, String regionName);
}
