package ca.softwarespace.qiyanna.dataaggregator.util;

import com.merakianalytics.orianna.types.common.Queue;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Steve Mbiele Date: 5/15/2019
 */
public class Constants {

  public static final long SECOND_TO_MILLI = 1000;
  public static final String MATCH_DOCUMENT_QUEUE_KEY = "queueId";
  public static final String MATCH_PLATFORM_ID = "platformId";
  public static final String MATCH_DOCUMENT_PARTICIPANT_CHAMPION_KEY = "participants.championId";
  public static final String MATCH_DOCUMENT_PARTICIPANT_LANE = "participants.timeline.lane";
  public static final String MATCH_DOCUMENT_PARTICIPANT_ROLE = "participants.timeline.role";
  public static final String MATCH_DOCUMENT_PARTICIPANT_TIER = "participants.highestAchievedSeasonTier";
  public static final String MATCH_DOCUMENT_PARTICIPANT_STAT_WIN = "participants.stats.win";
  public static final String RESULT_KEY = "result";

  private Constants() {
  }

  public static List<Queue> getQueuesList() {
    List<Queue> queues = new ArrayList<>();
    queues.add(Queue.ARAM);
    queues.add(Queue.NORMAL);
    queues.add(Queue.RANKED_SOLO);
    queues.add(Queue.RANKED_FLEX);
    queues.add(Queue.RANKED_THREES);
    queues.add(Queue.RANKED_TFT);
    queues.add(Queue.TFT);
    queues.add(Queue.THREES);
    return queues;
  }
}
