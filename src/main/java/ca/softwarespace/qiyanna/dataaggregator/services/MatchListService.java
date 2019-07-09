package ca.softwarespace.qiyanna.dataaggregator.services;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Author: Steve Mbiele
 * Date: 5/15/2019
 */

@Service
public class MatchListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchListService.class);


    public MatchListService() {

    }
    public Summoner oriannaTest(String summonerName) {
        return  Orianna.summonerNamed(summonerName).get();
    }

}
