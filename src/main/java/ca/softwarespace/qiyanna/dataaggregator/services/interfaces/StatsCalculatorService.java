package ca.softwarespace.qiyanna.dataaggregator.services.interfaces;

import ca.softwarespace.qiyanna.dataaggregator.events.DataCollectionEvent;
import org.springframework.context.ApplicationListener;

public interface StatsCalculatorService extends ApplicationListener<DataCollectionEvent> {

  void calculateChampionWins(int championId, int queueId, int roleId, int laneId,
      int tierId, int regionId);
}
