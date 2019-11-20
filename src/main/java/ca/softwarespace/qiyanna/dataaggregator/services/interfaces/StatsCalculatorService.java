package ca.softwarespace.qiyanna.dataaggregator.services.interfaces;

import ca.softwarespace.qiyanna.dataaggregator.events.DataCollectionEvent;
import org.springframework.context.ApplicationListener;

public interface StatsCalculatorService extends ApplicationListener<DataCollectionEvent> {

  double calculateChampionWins(int championId, int queueId, int roleId, int laneId,
      int tierId, String regionName);

  double calculateChampionLosses(int championId, int queueId, int roleId, int laneId,
      int tierId, String regionName);
}
