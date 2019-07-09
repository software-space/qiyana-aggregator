package ca.softwarespace.qiyanna.dataaggregator.controllers;

import ca.softwarespace.qiyanna.dataaggregator.services.MatchListService;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private MatchListService matchListService;

    public GreetingController(MatchListService matchListService) {
        this.matchListService = matchListService;
    }

    @GetMapping("/{summonerName}")
    public String greetings(@PathVariable String summonerName) {
//        matchListService.fetchMatchList("fastboyz");
//        matchListService.fetchMatchList("maytaro");
//        matchListService.fetchMatchList("yofranck99");
        Summoner summoner = matchListService.oriannaTest(summonerName);
        return  summoner.toJSON();
    }
}
