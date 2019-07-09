package ca.softwarespace.qiyanna.dataaggregator;

import com.merakianalytics.orianna.Orianna;
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
public class DataAggregatorApplication {

    @Value("${orianna.config.file}")
    private String oriannaConfig;

    public static void main(String[] args) {
        SpringApplication.run(DataAggregatorApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        System.out.println(oriannaConfig);
        Orianna.loadConfiguration(oriannaConfig);
    }
}
