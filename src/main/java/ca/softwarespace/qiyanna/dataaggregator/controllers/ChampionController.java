package ca.softwarespace.qiyanna.dataaggregator.controllers;

import ca.softwarespace.qiyanna.dataaggregator.models.ChampionDto;
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
  public List<ChampionDto> getChampionStatsBySummonerName(
      @ApiParam(example = "Marcarrian")
      @PathVariable String summonerName,
      @ApiParam(example = "EUW")
      @RequestParam String regionName) throws Exception {
    return championService.getAllChampionStatsBySummonerName(summonerName, regionName).get();
  }



}
