package ca.softwarespace.qiyanna.dataaggregator.services;

import ca.softwarespace.qiyanna.dataaggregator.events.DataCollectionEvent;
import ca.softwarespace.qiyanna.dataaggregator.services.interfaces.StatsCalculatorService;
import ca.softwarespace.qiyanna.dataaggregator.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StatsCalculatorServiceImpl implements StatsCalculatorService {

  @Override
  public void onApplicationEvent(DataCollectionEvent dataCollectionEvent) {
    log.info(dataCollectionEvent.getMessage());
  }
}
