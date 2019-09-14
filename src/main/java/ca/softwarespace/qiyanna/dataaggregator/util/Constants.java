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
