package ca.softwarespace.qiyanna.dataaggregator.controllers;

import ca.softwarespace.qiyanna.dataaggregator.services.interfaces.StatsCalculatorService;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatsControllerTests {

    @Mock
    private StatsCalculatorService statsCalculatorService;

    @InjectMocks
    private StatsController statsController;

    private Platform platform;
    private Queue queueType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        platform = Platform.EUROPE_WEST;
        queueType = Queue.RANKED_SOLO;
    }

    @Test
    public void getWinRateForChampionWithIdShouldReturnCalculatedWinrateForThatChampion() {

        when(statsCalculatorService.getWinrateForChampionWithIdAndPlatformAndQueueType(1,platform,queueType)).thenReturn(1.3);
        ResponseEntity<Double> receivedWinrate = statsController.getWinRateForChampion(1, platform, queueType);
        Assert.assertNotNull(receivedWinrate);
        Assert.assertEquals(new Double(1.3), receivedWinrate.getBody());
    }

    @Test
    public void getAmountOfMatchesPlayedByChampionIdShouldReturnTheAmountOfGamesAChampionHasPlayed() {
        int selectedChampionId = 1;
        when(statsCalculatorService.getMatchesPlayedByChampionIdAndPlatformAndQueueType(selectedChampionId,platform,queueType)).thenReturn(9L);

        ResponseEntity<Long> receivedAmountOfGamesPlayed = statsController.getAmountOfGamesPlayedByChampionId(selectedChampionId, platform, queueType);
        Assert.assertNotNull(receivedAmountOfGamesPlayed);
        Assert.assertNotNull(receivedAmountOfGamesPlayed.getBody());
        Assert.assertEquals(9L, receivedAmountOfGamesPlayed.getBody(),0);
    }

    @Test
    public void getChampionPickRateByRegionAndQueueTypeShouldReturnThePickRateOfAChampion() {
        int selectedChampion = 1;

        when(statsCalculatorService.getChampionPickRateByRegionAndQueueType(selectedChampion,platform,queueType)).thenReturn(1.2);

        ResponseEntity<Double> receivedPickRate = statsController.getChampionPickRateByRegionAndQueueType(selectedChampion,platform,queueType);

        Assert.assertNotNull(receivedPickRate);
        Assert.assertNotNull(receivedPickRate.getBody());
        Assert.assertEquals(1.2,receivedPickRate.getBody(),0);

    }


}
