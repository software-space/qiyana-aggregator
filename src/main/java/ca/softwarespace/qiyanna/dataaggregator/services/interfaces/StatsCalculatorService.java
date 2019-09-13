package ca.softwarespace.qiyanna.dataaggregator.services.interfaces;

import ca.softwarespace.qiyanna.dataaggregator.events.DataCollectionEvent;
import org.springframework.context.ApplicationListener;

public interface StatsCalculatorService extends ApplicationListener<DataCollectionEvent> {

}
