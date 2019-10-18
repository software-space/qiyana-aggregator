package ca.softwarespace.qiyanna.dataaggregator.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChampionCollectionEvent extends ApplicationEvent {

  private String message;

  public ChampionCollectionEvent(Object source, String message) {
    super(source);
    this.message = message;
  }
}
