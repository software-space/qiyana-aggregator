package ca.softwarespace.qiyanna.dataaggregator.controllers;

import ca.softwarespace.qiyanna.dataaggregator.models.dao.AggregatedChampionDto;
import ca.softwarespace.qiyanna.dataaggregator.services.ChampionService;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/champion")
public class ChampionController {

  private final ChampionService championService;

  @GetMapping("/{summonerName}")
  public List<AggregatedChampionDto> aggregateChampionStatsBySummoner(
      @ApiParam(example = "Marcarrian", required = true)
      @PathVariable String summonerName,
      @ApiParam(example = "EUW", required = true)
      @RequestParam String regionName,
      @ApiParam(example = "Olaf")
      @RequestParam(required = false) String championName) {
    return championService.aggregateChampionStatsBySummoner(summonerName, championName, regionName);
  }


}
