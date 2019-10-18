package ca.softwarespace.qiyanna.dataaggregator.events;

import com.merakianalytics.orianna.types.common.Region;
import java.util.HashMap;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;


@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateMatchesEvent extends ApplicationEvent {

  private HashMap<String, Region> summonersToCollect;

  public UpdateMatchesEvent(Object source, HashMap<String, Region> summonersToCollect) {
    super(source);
    this.summonersToCollect = summonersToCollect;
  }

}
