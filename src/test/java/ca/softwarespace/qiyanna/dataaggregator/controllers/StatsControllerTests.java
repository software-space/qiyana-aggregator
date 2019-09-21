package ca.softwarespace.qiyanna.dataaggregator.controllers;

import ca.softwarespace.qiyanna.dataaggregator.services.interfaces.StatsCalculatorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatsControllerTests {

    @Mock
    private StatsCalculatorService statsCalculatorService;

    @InjectMocks
    private StatsController statsController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getWinRateForChampionWithIdShouldReturnCalculatedWinrateForThatChampion() {


    }


}
