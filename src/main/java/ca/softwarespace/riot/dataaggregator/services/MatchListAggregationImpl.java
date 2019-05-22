package ca.softwarespace.riot.dataaggregator.services;

import ca.softwarespace.riot.dataaggregator.Repositories.MatchListRepository;
import ca.softwarespace.riot.dataaggregator.Repositories.MatchReferenceRepository;
import ca.softwarespace.riot.dataaggregator.Repositories.SummonerRepository;
import ca.softwarespace.riot.dataaggregator.RiotModels.MatchList;
import ca.softwarespace.riot.dataaggregator.RiotModels.MatchReference;
import ca.softwarespace.riot.dataaggregator.RiotModels.Summoner;
import ca.softwarespace.riot.dataaggregator.Utils.Constants;
import ca.softwarespace.riot.dataaggregator.Utils.RestClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author: Steve Mbiele
 * Date: 5/15/2019
 */

@Service
public class MatchListAggregationImpl implements AggretionService {

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

    @Autowired
    private SummonerRepository summonerRepository;

    @Autowired
    private MatchListRepository matchListRepository;

    @Autowired
    private MatchReferenceRepository matchReferenceRepository;

    private ObjectMapper objectMapper;

    public MatchListAggregationImpl() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    @Scheduled(fixedDelayString = "${fixedRate.in.milliseconds}")
    public void getData() {
        getSummoner();
    }

    private void getSummoner() {
        Summoner summoner;
        RestClient client = new RestClient();
        client.addHeader("X-Riot-Token", apiKey);
        client.addHeader("Content-Type", "application/json");
        client.addHeader("Accept", "*/*");
        String url = baseUrl + summonerEndPoint + summonersName;
        try {
            summoner = this.objectMapper.readValue(client.get(url), Summoner.class);
            if (!summonerRepository.existsById(summoner.getId()))
                summonerRepository.save(summoner);
            getMatchList(summoner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getMatchList(Summoner summoner) {
        RestClient client = new RestClient();
        client.addHeader("X-Riot-Token", apiKey);
        client.addHeader("Content-Type", "application/json");
        client.addHeader("Accept", "*/*");

        String url = baseUrl + matchListEndPoint + summoner.getAccountId();
        try {

            if (!matchListRepository.existsBySummoner(summoner)) {
                String data = client.get(url);
                JsonNode json = objectMapper.readTree(data);
                JsonNode jsonMatchReference = json.get("matches");
                String stringMatchReference = jsonMatchReference.toString();
                MatchList matchList = objectMapper.readValue(data, MatchList.class);
                List<MatchReference> matchReferences = objectMapper.readValue(stringMatchReference, new TypeReference<List<MatchReference>>() {
                });
                List<MatchReference> toRemove = new ArrayList<>();
                List<MatchReference> toUpdate = new ArrayList<>();
                matchList.setSummoner(summoner);
                matchListRepository.save(matchList);
                for (MatchReference reference : matchReferences) {
                    Optional<MatchReference> record = matchReferenceRepository.findById(reference.getGameId());
                    if (!record.isPresent()) {
                        reference.addMatchList(matchList);
                    } else {
                        MatchReference saved = record.get();
                        toRemove.add(reference);
                        saved.addMatchList(matchList);
                        toUpdate.add(saved);

                    }
                }
                matchReferences.removeAll(toRemove);
                matchReferenceRepository.saveAll(matchReferences);
                matchReferenceRepository.saveAll(toUpdate);

            } else {
                MatchList old = matchListRepository.findBySummoner(summoner);
                url = url + "?" + Constants.MATCH_LIST_BEGIN_INDEX_PARAMETER + "=" + old.getEndIndex();

                DoRequest doRequest = new DoRequest(client, url).invoke();
                MatchList matchList = doRequest.getMatchList();
                List<MatchReference> matchReferences = doRequest.getMatchReferences();

                List<MatchReference> allByMatchLists = matchReferenceRepository.findAllByMatchLists(old);
                List<MatchReference> toRemove = new ArrayList<>();
                List<MatchReference> toUpdate = new ArrayList<>();


                for (MatchReference reference : matchReferences) {
                    Optional<MatchReference> record = matchReferenceRepository.findById(reference.getGameId());
                    if (!record.isPresent()) {
                        reference.addMatchList(matchList);
                    } else {
                        MatchReference saved = record.get();
                        toRemove.add(reference);
                        if (!saved.getMatchLists().contains(old)) {
                            saved.addMatchList(matchList);
                            toUpdate.add(saved);
                        }
                    }

                }

                matchReferences.removeAll(toRemove);

                if (matchReferences.size() == 0) {
                    matchList.setTotalGames(allByMatchLists.size());
                    matchList.setStartIndex(0);
                    matchList.setEndIndex(0);
                }
                matchList.setId(old.getId());
                matchList.setSummoner(summoner);
                matchListRepository.save(matchList);

                matchReferenceRepository.saveAll(matchReferences);
                matchReferenceRepository.saveAll(toUpdate);
            }
            System.out.println("done");

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2000);
        }
    }

    private class DoRequest {
        private RestClient client;
        private String url;
        private MatchList matchList;
        private List<MatchReference> matchReferences;

        public DoRequest(RestClient client, String url) {
            this.client = client;
            this.url = url;
        }

        public MatchList getMatchList() {
            return matchList;
        }

        public List<MatchReference> getMatchReferences() {
            return matchReferences;
        }

        public DoRequest invoke() throws IOException {
            String data = client.get(url);
            JsonNode json = objectMapper.readTree(data);
            JsonNode jsonMatchReference = json.get(Constants.MATCHES_NODE_IN_MATCH_LIST);
            String stringMatchReference = jsonMatchReference.toString();
            matchList = objectMapper.readValue(data, MatchList.class);
            matchReferences = objectMapper.readValue(stringMatchReference, new TypeReference<List<MatchReference>>() {
            });
            return this;
        }
    }
}
