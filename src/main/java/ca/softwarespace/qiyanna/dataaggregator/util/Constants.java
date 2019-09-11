package ca.softwarespace.qiyanna.dataaggregator.util;

import com.merakianalytics.orianna.types.common.Queue;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Steve Mbiele Date: 5/15/2019
 */
public class Constants {

  public static final int SOLO_QUEUE_RANKED_ID = 420;
  public static final long SECOND_TO_MILLI= 1000;
  private Constants() {
  }

  public static List<Queue> getQueuesList() {
    ArrayList<Queue> queues = new ArrayList<>();
    queues.add(Queue.ARAM);
    queues.add(Queue.BILGEWATER_ARAM_5X5);
    queues.add(Queue.NORMAL_3X3_BLIND_PICK);
    queues.add(Queue.NORMAL_5V5_BLIND_PICK);
    queues.add(Queue.RANKED_SOLO_5X5);
    queues.add(Queue.RANKED_FLEX_SR);
    queues.add(Queue.RANKED_FLEX_TT);
    queues.add(Queue.RANKED_TFT);
    queues.add(Queue.NORMAL_TFT);
    queues.add(Queue.TEAM_BUILDER_DRAFT_UNRANKED_5X5);
    return queues;
  }
}
