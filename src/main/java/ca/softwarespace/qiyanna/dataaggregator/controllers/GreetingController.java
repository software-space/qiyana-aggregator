package ca.softwarespace.qiyanna.dataaggregator.controllers;

import ca.softwarespace.qiyanna.dataaggregator.services.MatchesCollectionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/summoner")
public class GreetingController {

    private MatchesCollectionService matchesCollectionService;

    public GreetingController(MatchesCollectionService matchesCollectionService) {
        this.matchesCollectionService = matchesCollectionService;
    }

    @PostMapping("/{summonerName}")
    public String greetings(@PathVariable String summonerName, @RequestParam(required = false) String regionName) {

       StringBuilder sb = new StringBuilder();
       sb.append("Starting aggregation for ");
       sb.append(summonerName);
       if(regionName != null){
           matchesCollectionService.oriannaTest(summonerName, regionName);
           sb.append(" with region: ").append(regionName);
       } else {
           matchesCollectionService.oriannaTest(summonerName);
           sb.append(" with region: NA");
       }
        return  sb.toString();
    }
}
