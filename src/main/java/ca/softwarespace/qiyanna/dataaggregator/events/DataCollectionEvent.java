package ca.softwarespace.qiyanna.dataaggregator.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

@EqualsAndHashCode(callSuper = true)
@Data
public class DataCollectionEvent extends ApplicationEvent {

  private String message;

  public DataCollectionEvent(Object source, String message) {
    super(source);
    this.message = message;
  }
}
