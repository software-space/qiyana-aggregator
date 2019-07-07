package ca.softwarespace.qiyanna.dataaggregator.controllers;

import ca.softwarespace.qiyanna.dataaggregator.services.MatchListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private MatchListService matchListService;

    public GreetingController(MatchListService matchListService) {
        this.matchListService = matchListService;
    }

    @GetMapping("/")
    public String greetings() {
        matchListService.fetchMatchList("fastboyz");
        matchListService.fetchMatchList("maytaro");
        matchListService.fetchMatchList("yofranck99");
        return "Hello from";
    }
}
