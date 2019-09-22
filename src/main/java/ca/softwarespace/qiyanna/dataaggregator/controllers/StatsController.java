package ca.softwarespace.qiyanna.dataaggregator.controllers;

import ca.softwarespace.qiyanna.dataaggregator.services.interfaces.StatsCalculatorService;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private StatsCalculatorService calculatorService;

    @Autowired
    public StatsController(StatsCalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }


    @RequestMapping("/winrate/{championId}/{platform}/{queueType}")
    public ResponseEntity<Double> getWinRateForChampion(@PathVariable("championId") int championId,
                                                        @PathVariable("platform") Platform platform,
                                                        @PathVariable("queueType") Queue queueType) {
        double winrateForChampionWithIdAndPlatformAndQueueType =
                calculatorService.getWinrateForChampionWithIdAndPlatformAndQueueType(championId,
                                                                                     platform,
                                                                                     queueType);
        return ResponseEntity.ok(winrateForChampionWithIdAndPlatformAndQueueType);
    }

    @RequestMapping("/played/{championId}/{platform}/{queueType}")
    public ResponseEntity<Long> getAmountOfGamesPlayedByChampionId(@PathVariable("championId") int championId,
                                                                   @PathVariable("platform") Platform platform,
                                                                   @PathVariable("queueType") Queue queueType) {
        long amountOfGamesPlayedByChampion = calculatorService.getMatchesPlayedByChampionIdAndPlatformAndQueueType(championId, platform,queueType);
        return ResponseEntity.ok(amountOfGamesPlayedByChampion);
    }

    @RequestMapping("/pickrate/{championId}/{platform}/{queueType}")
    public ResponseEntity<Double> getChampionPickRateByRegionAndQueueType(@PathVariable("championId") int championId,
                                                                        @PathVariable("platform") Platform platform,
                                                                        @PathVariable("queueType") Queue queueType) {
        double championPickRate = calculatorService.getChampionPickRateByRegionAndQueueType(championId,platform,queueType);
        return ResponseEntity.ok(championPickRate);
    }
}
