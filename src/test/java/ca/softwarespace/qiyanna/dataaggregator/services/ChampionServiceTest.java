package ca.softwarespace.qiyanna.dataaggregator.services;

import ca.softwarespace.qiyanna.dataaggregator.models.ChampionDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChampionService.class)
public class ChampionServiceTest {

  @Autowired
  private ChampionService championService;

  private static Map<String, List<ChampionDto>> championsGroupedByName = new HashMap<>();

  @BeforeClass
  public static void setup() {
    List<ChampionDto> viChampions = new ArrayList<>();
    List<ChampionDto> annieChampions = new ArrayList<>();
    List<ChampionDto> olafChampions = new ArrayList<>();

    viChampions.add(ChampionDto.builder()
        .name("Vi")
        .kills(7)
        .deaths(3)
        .assists(8)
        .build());

    viChampions.add(ChampionDto.builder()
        .name("Vi")
        .kills(3)
        .deaths(2)
        .assists(12)
        .build());

    viChampions.add(ChampionDto.builder()
        .name("Vi")
        .kills(12)
        .deaths(4)
        .assists(3)
        .build());

    annieChampions.add(ChampionDto.builder()
        .name("Annie")
        .kills(4)
        .deaths(0)
        .assists(0)
        .build());

    annieChampions.add(ChampionDto.builder()
        .name("Annie")
        .kills(11)
        .deaths(6)
        .assists(8)
        .build());

    olafChampions.add(ChampionDto.builder()
        .name("Olaf")
        .kills(12)
        .deaths(7)
        .assists(11)
        .build());

    championsGroupedByName.put("Vi", viChampions);
    championsGroupedByName.put("Annie", annieChampions);
    championsGroupedByName.put("Olaf", olafChampions);
  }

  @Test
  public void shouldCalculateAverageStatsCorrectly() {
    List<ChampionDto> champions = championService.calculateAverageStats(championsGroupedByName);

    ChampionDto averageVi = champions.stream()
        .filter(champion -> champion.getName().equals("Vi")).findFirst().get();

    ChampionDto averageAnnie = champions.stream()
        .filter(champion -> champion.getName().equals("Annie")).findFirst().get();

    ChampionDto averageOlaf = champions.stream()
        .filter(champion -> champion.getName().equals("Olaf")).findFirst().get();

    double averageViKills = ((7 + 3 + 12) / (double) 3);
    double averageAnnieKills = ((4 + 11) / (double) 2);
    double averageOlafKills = ((12) / (double) 1);

    double averageViDeaths = ((3 + 2 + 4) / (double) 3);
    double averageAnnieDeaths = ((0 + 6) / (double) 2);
    double averageOlafDeaths = ((7) / (double) 1);

    double averageViAssists = ((8 + 12 + 3) / (double) 3);
    double averageAnnieAssists = ((0 + 8) / (double) 2);
    double averageOlafAssists = ((11) / (double) 1);

    assertEquals(averageViKills, averageVi.getKills(), 0.0f);
    assertEquals(averageAnnieKills, averageAnnie.getKills(), 0.0f);
    assertEquals(averageOlafKills, averageOlaf.getKills(), 0.0f);

    assertEquals(averageViDeaths, averageVi.getDeaths(), 0.0f);
    assertEquals(averageAnnieDeaths, averageAnnie.getDeaths(), 0.0f);
    assertEquals(averageOlafDeaths, averageOlaf.getDeaths(), 0.0f);

    assertEquals(averageViAssists, averageVi.getAssists(), 0.0f);
    assertEquals(averageAnnieAssists, averageAnnie.getAssists(), 0.0f);
    assertEquals(averageOlafAssists, averageOlaf.getAssists(), 0.0f);
  }
}
