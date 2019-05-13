package ca.softwarespace.riot.dataaggregator.services;

import ca.softwarespace.riot.dataaggregator.RiotModels.MatchList;
import ca.softwarespace.riot.dataaggregator.RiotModels.Summoner;
import ca.softwarespace.riot.dataaggregator.Utils.RestClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class NormalGamesAggregationImpl implements AggretionService {

    @Value("${riotgames.api.baseUrl}")
    private String baseUrl;

    @Value("${riotgames.api.summoners.byName}")
    private String summonerEndPoint;

    @Value("${riotgames.api.base.summonersName}")
    private String summonersName;

    @Value("${riotgames.api.matchlist.byaccount}")
    private String matchListEndPoint;

    @Value("${riotgames.api.key}")
    private String apiKey;

    private ObjectMapper objectMapper;
    private int cmp = 0;

    public NormalGamesAggregationImpl() {
        objectMapper = new ObjectMapper();
    }

    @Override
    @Scheduled(fixedDelayString = "${fixedRate.in.milliseconds}")
    public void getData() {
        getSummoner();
    }

    private void getSummoner() {
        Summoner summoner = null;
        RestClient client = new RestClient();
        client.addHeader("X-Riot-Token", apiKey);
        client.addHeader("Content-Type", "application/json");
        client.addHeader("Accept", "*/*");
        String url = baseUrl + summonerEndPoint + summonersName;
        try {
            summoner = this.objectMapper.readValue(client.get(url), Summoner.class);
            getMatchList(summoner.getAccountId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        cmp++;
        System.out.println("Bob's value is " + summoner.getAccountId());
    }

    private void getMatchList(String accountId){
        RestClient client = new RestClient();
        client.addHeader("X-Riot-Token", apiKey);
        client.addHeader("Content-Type", "application/json");
        client.addHeader("Accept", "*/*");
        String url = baseUrl+ matchListEndPoint+accountId;
        String data = client.get(url);

        try {
            MatchList matchList =  objectMapper.readValue(data, MatchList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
