package ca.softwarespace.qiyanna.dataaggregator.util;

import com.merakianalytics.orianna.types.common.Queue;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Steve Mbiele Date: 5/15/2019
 */
public class Constants {

  public static final String MATCH_LIST_BEGIN_INDEX_PARAMETER = "beginIndex";
  public static final String MATCHES_NODE_IN_MATCH_LIST = "matches";
  public static final int SOLO_QUEUE_RANKED_ID = 420;
  private Constants() {
  }

  public static List<Queue> getQueuesList() {
    ArrayList<Queue> queues = new ArrayList<>();
    queues.add(Queue.OVERCHARGE);
    queues.add(Queue.NEXUS_SIEGE);
    queues.add(Queue.ARURF_5X5);
    queues.add(Queue.URF_5X5);
    queues.add(Queue.ARAM);
    queues.add(Queue.ARSR_5X5);
    queues.add(Queue.ASSASSINATE_5X5);
    queues.add(Queue.BILGEWATER_5X5);
    queues.add(Queue.DARKSTAR_3X3);
    queues.add(Queue.NORMAL_3X3_BLIND_PICK);
    queues.add(Queue.NORMAL_5V5_BLIND_PICK);
    queues.add(Queue.ONEFORALL_MIRRORMODE_5X5);
    queues.add(Queue.ONEFORALL_RAPID_5X5);
    queues.add(Queue.KINGPORO);
    queues.add(Queue.RANKED_SOLO_5X5);
    queues.add(Queue.RANKED_FLEX_SR);
    queues.add(Queue.RANKED_FLEX_TT);
    queues.add(Queue.RANKED_TFT);
    queues.add(Queue.NORMAL_TFT);
    queues.add(Queue.TEAM_BUILDER_DRAFT_UNRANKED_5X5);
    queues.add(Queue.COUNTER_PICK);
    queues.add(Queue.DEFINITELY_NOT_DOMINION_5X5);
    queues.add(Queue.CUSTOM);
    queues.add(Queue.HEXAKILL);
    return queues;
  }
}
