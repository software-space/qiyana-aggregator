package ca.softwarespace.qiyanna.dataaggregator;

import com.merakianalytics.orianna.Orianna;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableAsync
@Slf4j
public class DataAggregatorApplication {

  @Value("${orianna.config.file}")
  private String oriannaConfig;

  public static void main(String[] args) {
    SpringApplication.run(DataAggregatorApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void onApplicationReady() {
    log.info(oriannaConfig);
    Orianna.loadConfiguration(oriannaConfig);
  }
}
