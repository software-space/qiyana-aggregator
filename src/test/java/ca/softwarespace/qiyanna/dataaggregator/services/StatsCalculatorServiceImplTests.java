package ca.softwarespace.qiyanna.dataaggregator.services;

import ca.softwarespace.qiyanna.dataaggregator.models.builders.MatchDtoBuilder;
import ca.softwarespace.qiyanna.dataaggregator.models.builders.ParticipantBuilder;
import ca.softwarespace.qiyanna.dataaggregator.models.builders.StatsBuilder;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.MatchDto;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.Participant;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.Stats;
import ca.softwarespace.qiyanna.dataaggregator.repositories.ChampionStatsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatsCalculatorServiceImplTests {

    @Mock
    private ChampionStatsRepository championStatsRepository;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private StatsCalculatorServiceImpl statsCalculatorService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getWinOrLoseShouldReturnTrueOnWin() {

        int selectedChampion = 1;

        Stats stats = new StatsBuilder().withWin(true).build();
        Participant participant =
                new ParticipantBuilder()
                        .withChampionId(selectedChampion).withStats(stats).build();

        List<Participant> participants = new ArrayList<>();
        participants.add(participant);

        MatchDto match = new MatchDtoBuilder().withParticipants(participants).build();

        boolean isWin = statsCalculatorService.getWinOrLose(match,selectedChampion);

        Assert.assertTrue(isWin);
    }

    @Test
    public void getWinOrLoseShouldReturnFalseOnLoss() {
        int selectedChampion = 1;

        Stats stats = new StatsBuilder().withWin(false).build();
        Participant participant =
                new ParticipantBuilder()
                        .withChampionId(selectedChampion).withStats(stats).build();

        List<Participant> participants = new ArrayList<>();
        participants.add(participant);

        MatchDto match = new MatchDtoBuilder().withParticipants(participants).build();

        boolean isWin = statsCalculatorService.getWinOrLose(match,selectedChampion);

        Assert.assertFalse(isWin);
    }

    @Test
    public void calculatePercentageShouldReturn100PercentIfAllGamesAreWon() {
        int wins = 100;
        int gamesPlayed = 100;

        double winrate = statsCalculatorService.calculatePercentage(wins,gamesPlayed);

        Assert.assertEquals(100.00,winrate,0);
    }

    @Test
    public void getWinRateForChampionWithIdAndPlatformAndQueueTypeShouldReturnTheWinRateOfAChampion() {

        Platform platform = Platform.EUROPE_WEST;
        Queue queueType = Queue.RANKED_SOLO;

        Stats winStat = new StatsBuilder().withWin(true).build();
        Stats lossStat = new StatsBuilder().withWin(false).build();
        Participant participant1 = new ParticipantBuilder().withChampionId(1).withStats(winStat).build();
        Participant participant2 = new ParticipantBuilder().withChampionId(10).withStats(winStat).build();
        Participant participant3 = new ParticipantBuilder().withChampionId(11).withStats(winStat).build();
        Participant participant4 = new ParticipantBuilder().withChampionId(12).withStats(winStat).build();

        Participant participant5 = new ParticipantBuilder().withChampionId(1).withStats(lossStat).build();


        List<Participant> participantsGame1 = new ArrayList<>();
        participantsGame1.add(participant1);
        participantsGame1.add(participant2);
        participantsGame1.add(participant3);
        participantsGame1.add(participant4);

        List<Participant> participantsGame2 = new ArrayList<>();
        participantsGame2.add(participant5);

        MatchDto match = new MatchDtoBuilder()
                .withParticipants(participantsGame1)
                .withPlatformId(platform.getTag())
                .withQueueId(queueType.getId()).build();

        MatchDto match2 = new MatchDtoBuilder()
                .withParticipants(participantsGame2)
                .withPlatformId(platform.getTag())
                .withQueueId(queueType.getId()).build();

        List<MatchDto> matches = new ArrayList<>();
        matches.add(match);
        matches.add(match2);

        when(championStatsRepository.findByParticipants_ChampionIdAndPlatformIdAndQueueId(1,platform.getTag(),queueType.getId())).thenReturn(matches);

        double winRate = statsCalculatorService.getWinrateForChampionWithIdAndPlatformAndQueueType(1,platform,queueType);

        Assert.assertEquals(50.00, winRate, 0);

    }



}
