package ca.softwarespace.qiyanna.dataaggregator.services;

import ca.softwarespace.qiyanna.dataaggregator.Repositories.MatchListRepository;
import ca.softwarespace.qiyanna.dataaggregator.Repositories.MatchReferenceRepository;
import ca.softwarespace.qiyanna.dataaggregator.Repositories.SummonerRepository;
import ca.softwarespace.qiyanna.dataaggregator.RiotModels.MatchList;
import ca.softwarespace.qiyanna.dataaggregator.RiotModels.Summoner;
import ca.softwarespace.qiyanna.dataaggregator.Utils.Constants;
import ca.softwarespace.qiyanna.dataaggregator.Utils.RestClient;
import ca.softwarespace.qiyanna.dataaggregator.RiotModels.MatchReference;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Author: Steve Mbiele
 * Date: 5/15/2019
 */

@Service
public class MatchListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchListService.class);

    @Value("${riotgames.api.baseUrlNa}")
    private String baseUrl;

    @Value("${riotgames.api.summoners.byName}")
    private String summonerEndPoint;

//    @Value("${riotgames.api.base.summonersName}")
//    private String summonersName;

    @Value("${riotgames.api.matchlist.byaccount}")
    private String matchListEndPoint;

    @Value("${riotgames.api.key}")
    private String apiKey;

    private final SummonerRepository summonerRepository;

    private final MatchListRepository matchListRepository;

    private final MatchReferenceRepository matchReferenceRepository;

    private ObjectMapper objectMapper;

    public MatchListService(MatchReferenceRepository matchReferenceRepository, MatchListRepository matchListRepository, SummonerRepository summonerRepository) {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.matchReferenceRepository = matchReferenceRepository;
        this.matchListRepository = matchListRepository;
        this.summonerRepository = summonerRepository;
    }

//    @Scheduled(fixedDelayString = "${fixedRate.in.milliseconds}")
    public void getDataBySummonerName(String summonersName) {
//        getSummoner(summonersName);
    }

    /**
     * Retrieves the summoner's data form Riot Games API, saves it in  the data base and return it.
     *
     * @param summonersName the summoner's name to fetch.
     * @return A Summoner object.
     */
    @Async
    public CompletableFuture<Summoner> getSummoner(String summonersName) {
        Summoner summoner = summonerRepository.findByName(summonersName);
        if (summoner == null) {
            RestClient client = new RestClient();
            client.addHeader("X-Riot-Token", apiKey);
            client.addHeader("Content-Type", "application/json");
            client.addHeader("Accept", "*/*");
            String url = baseUrl + summonerEndPoint + summonersName;
            try {
                summoner = this.objectMapper.readValue(client.get(url), Summoner.class);
                if (!summonerRepository.existsById(summoner.getId()))
                    summonerRepository.save(summoner);
                return CompletableFuture.completedFuture(summoner);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return CompletableFuture.completedFuture(summoner);
    }

    /**
     * Retrieves the match list of a summoner from the Riot Games API and saves it in the Database.
     *
     * @param summoner the summoner's we are fetching the match list.
     */
    @Async
    public void fetchMatchList(Summoner summoner) {
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
                List<MatchReference> matchReferences = objectMapper.readValue(stringMatchReference,
                        new TypeReference<List<MatchReference>>() {});

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
            LOGGER.info("Done");

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Retrieves the match list of a summoner from the Riot Games API and saves it in the Database.
     *
     * @param summonersName the summoner's name to fetch match list from.
     */
    @Async
    public void fetchMatchList(String summonersName) {
        Summoner summoner;
        try {
            summoner = getSummoner(summonersName).get();
            fetchMatchList(summoner);
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private class DoRequest {
        private RestClient client;
        private String url;
        private MatchList matchList;
        private List<MatchReference> matchReferences;

        DoRequest(RestClient client, String url) {
            this.client = client;
            this.url = url;
        }

        MatchList getMatchList() {
            return matchList;
        }

        List<MatchReference> getMatchReferences() {
            return matchReferences;
        }

        DoRequest invoke() throws IOException {
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
