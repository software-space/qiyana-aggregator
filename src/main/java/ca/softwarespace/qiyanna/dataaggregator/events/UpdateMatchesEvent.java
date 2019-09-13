package ca.softwarespace.qiyanna.dataaggregator.events;

import java.util.HashSet;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;


@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateMatchesEvent extends ApplicationEvent {
  private HashSet<String> summonersToCollect;
  private String regionName;

  public UpdateMatchesEvent(Object source, HashSet<String>summonersToCollect, String regionName){
    super(source);
    this.summonersToCollect = summonersToCollect;
    this.regionName = regionName;
  }

}
