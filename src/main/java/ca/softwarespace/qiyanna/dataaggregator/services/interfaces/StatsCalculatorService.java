package ca.softwarespace.qiyanna.dataaggregator.services.interfaces;

import ca.softwarespace.qiyanna.dataaggregator.events.DataCollectionEvent;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import org.springframework.context.ApplicationListener;

public interface StatsCalculatorService extends ApplicationListener<DataCollectionEvent> {

    double getWinrateForChampionWithIdAndPlatformAndQueueType(int championId, Platform platform, Queue queueType);
    long getMatchesPlayedByChampionIdAndPlatformAndQueueType(int selectedChampionId, Platform platform, Queue queueType);

    double getChampionPickRateByRegionAndQueueType(int selectedChampion, Platform platform, Queue queueType);
}
