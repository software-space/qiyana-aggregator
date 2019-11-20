package ca.softwarespace.qiyanna.dataaggregator.services;

import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_DOCUMENT_PARTICIPANT_CHAMPION_KEY;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_DOCUMENT_PARTICIPANT_LANE;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_DOCUMENT_PARTICIPANT_ROLE;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_DOCUMENT_PARTICIPANT_STAT_WIN;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_DOCUMENT_PARTICIPANT_TIER;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_DOCUMENT_QUEUE_KEY;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_PLATFORM_ID;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.RESULT_KEY;

import ca.softwarespace.qiyanna.dataaggregator.events.DataCollectionEvent;
import ca.softwarespace.qiyanna.dataaggregator.models.CalculationResult;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.ChampionStatsDto;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.LaneDto;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.RoleDto;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.TierDto;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLAggregatorInfoRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLChampionStatsRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLDefaultSummonerRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLLaneRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLLeagueEntryRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLRankRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLRoleRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLSummonerRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLTierRepository;
import ca.softwarespace.qiyanna.dataaggregator.services.interfaces.StatsCalculatorService;
import com.merakianalytics.orianna.Orianna;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StatsCalculatorServiceImpl implements StatsCalculatorService {

  private final MongoTemplate mongoTemplate;
  private final SQLSummonerRepository sqlSummonerRepository;
  private final SQLAggregatorInfoRepository sqlAggregatorInfoRepository;
  private final SQLDefaultSummonerRepository sqlDefaultSummonerRepository;
  private final SQLLeagueEntryRepository sqlLeagueEntryRepository;
  private final SQLTierRepository sqlTierRepository;
  private final SQLRankRepository sqlRankRepository;
  private final SQLLaneRepository sqlLaneRepository;
  private final SQLRoleRepository sqlRoleRepository;
  private final SQLChampionStatsRepository championStatsRepository;

  @Autowired
  public StatsCalculatorServiceImpl(DSLContext dsl, MongoTemplate mongoTemplate,
      SQLSummonerRepository sqlSummonerRepository,
      SQLAggregatorInfoRepository sqlAggregatorInfoRepository,
      SQLDefaultSummonerRepository sqlDefaultSummonerRepository,
      SQLLeagueEntryRepository sqlLeagueEntryRepository,
      SQLTierRepository sqlTierRepository, SQLRankRepository sqlRankRepository,
      SQLLaneRepository sqlLaneRepository, SQLRoleRepository sqlRoleRepository,
      SQLChampionStatsRepository championStatsRepository) {
    this.mongoTemplate = mongoTemplate;
    this.sqlSummonerRepository = sqlSummonerRepository;
    this.sqlAggregatorInfoRepository = sqlAggregatorInfoRepository;
    this.sqlDefaultSummonerRepository = sqlDefaultSummonerRepository;
    this.sqlLeagueEntryRepository = sqlLeagueEntryRepository;
    this.sqlTierRepository = sqlTierRepository;
    this.sqlRankRepository = sqlRankRepository;
    this.sqlLaneRepository = sqlLaneRepository;
    this.sqlRoleRepository = sqlRoleRepository;
    this.championStatsRepository = championStatsRepository;
  }


  public double calculateChampionWins(int championId, int queueId, int roleId, int laneId,
      int tierId, String regionName) {
    double wins = 0;
    try {
      String region;
      LaneDto lane = sqlLaneRepository.findById(laneId);
      RoleDto role = sqlRoleRepository.findById(roleId);
      if (!regionName.equalsIgnoreCase("KR") && !regionName.equalsIgnoreCase("RU")) {
        region = regionName.toLowerCase() + "1";
      } else {
        region = regionName.toLowerCase();
      }
      MatchOperation firstMatchOperation = Aggregation
          .match(new Criteria(MATCH_DOCUMENT_QUEUE_KEY).is(queueId)
              .and(MATCH_DOCUMENT_PARTICIPANT_CHAMPION_KEY).is(championId)
              .and(MATCH_PLATFORM_ID).is(region));
      ProjectionOperation projectionOperation = Aggregation.project().and(
          aggregationOperationContext -> {
            Document filterExpression = new Document();
            filterExpression.put("input", "$participants");
            filterExpression.put("as", "participant");
            filterExpression.put("cond",
                new Document("$eq", Arrays.<Object>asList("$$participant.championId", championId)));
            return new Document("$filter", filterExpression);
          }).as("participants");
      MatchOperation secondMatchOperation = Aggregation
          .match(new Criteria(MATCH_DOCUMENT_PARTICIPANT_LANE).is(lane.getName().toUpperCase())
              .and(MATCH_DOCUMENT_PARTICIPANT_ROLE).is(role.getName().toUpperCase()));
      MatchOperation thirdMatchOperation = Aggregation
          .match(new Criteria(MATCH_DOCUMENT_PARTICIPANT_STAT_WIN).is(true));
      ArrayList<AggregationOperation> operations = new ArrayList<>();
      operations.add(firstMatchOperation);
      operations.add(projectionOperation);
//      operations.add(secondMatchOperation);
//      operations.add(thirdMatchOperation);
      GroupOperation groupOperation = Aggregation.group().count().as(RESULT_KEY);
      operations.add(groupOperation);

      Aggregation aggregation = Aggregation.newAggregation(operations);
      AggregationResults<CalculationResult> results = mongoTemplate
          .aggregate(aggregation, "dto.match.Match", CalculationResult.class);

      int i = 0;
      while (results.iterator().hasNext()) {
        i++;
        System.out.println(results.iterator().next().getResult() +" Level: " + i);
      }

      return results.iterator().next().getResult();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return wins;
  }

  public double calculateChampionLosses(int championId, int queueId, int roleId, int laneId,
      int tierId, String regionName) {
    double wins = 0;
    try {
      String region;
      LaneDto lane = sqlLaneRepository.findById(laneId);
      RoleDto role = sqlRoleRepository.findById(roleId);
      TierDto tier = sqlTierRepository.findById(tierId);
      if (!regionName.equalsIgnoreCase("KR") && !regionName.equalsIgnoreCase("RU")) {
        region = regionName.toLowerCase() + "1";
      } else {
        region = regionName.toLowerCase();
      }
      MatchOperation firstMatchOperation = Aggregation
          .match(new Criteria(MATCH_DOCUMENT_QUEUE_KEY).is(queueId)
              .and(MATCH_DOCUMENT_PARTICIPANT_CHAMPION_KEY).is(championId)
              .and(MATCH_PLATFORM_ID).is(region));
      ProjectionOperation projectionOperation = Aggregation.project().and(
          aggregationOperationContext -> {
            Document filterExpression = new Document();
            filterExpression.put("input", "$participants");
            filterExpression.put("as", "participant");
            filterExpression.put("cond",
                new Document("$eq", Arrays.<Object>asList("$$participant.championId", championId)));
            return new Document("$filter", filterExpression);
          }).as("participants");
      MatchOperation secondMatchOperation = Aggregation
          .match(new Criteria(MATCH_DOCUMENT_PARTICIPANT_LANE).is(lane.getName().toUpperCase())
              .and(MATCH_DOCUMENT_PARTICIPANT_ROLE).is(role.getName().toUpperCase())
              .and(MATCH_DOCUMENT_PARTICIPANT_TIER).is(tier.getShortName().toUpperCase()));
      MatchOperation thirdMatchOperation = Aggregation
          .match(new Criteria(MATCH_DOCUMENT_PARTICIPANT_STAT_WIN).is(false));
      GroupOperation groupOperation = Aggregation.group().count().as(RESULT_KEY);

      ArrayList<AggregationOperation> operations = new ArrayList<>();
      operations.add(firstMatchOperation);
      operations.add(projectionOperation);
      operations.add(secondMatchOperation);
      operations.add(thirdMatchOperation);
      operations.add(groupOperation);

      Aggregation aggregation = Aggregation.newAggregation(operations);
      AggregationResults<CalculationResult> results = mongoTemplate
          .aggregate(aggregation, "dto.match.Match", CalculationResult.class);

      int i = 0;
      while (results.iterator().hasNext()) {
        i++;
        System.out.println(results.iterator().next().getResult() +" Level: " + i);
      }
      return results.iterator().next().getResult();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return wins;
  }

  private double prep( MatchOperation firstMatchOperation,
      ProjectionOperation projectionOperation, MatchOperation secondMatchOperation,
      MatchOperation thirdMatchOperation) {
    GroupOperation groupOperation = Aggregation.group().count().as(RESULT_KEY);

    ArrayList<AggregationOperation> operations = new ArrayList<>();
    operations.add(firstMatchOperation);
    operations.add(projectionOperation);
    operations.add(secondMatchOperation);
    operations.add(thirdMatchOperation);
    operations.add(groupOperation);

    Aggregation aggregation = Aggregation.newAggregation(operations);
    AggregationResults<CalculationResult> results = mongoTemplate
        .aggregate(aggregation, "dto.match.Match", CalculationResult.class);

    int i = 0;
    while (results.iterator().hasNext()) {
      i++;
      System.out.println(results.iterator().next().getResult() +" Level: " + i);
    }

    return results.iterator().next().getResult();
  }


  //      TODO: THIS IS SOME TESTS
  private void orchestrator() {
    double totalMatches =  mongoTemplate.getCollection("dto.match.Match").countDocuments();
    Orianna.getChampions().stream().forEach(champion -> {
      double wins = calculateChampionWins(57, 420, 3, 4, 4, "NA");
      double losses = calculateChampionLosses(57, 420, 3, 4, 4, "NA");
      double winRate = wins / (wins+losses);
      double pickRate = (wins+losses) / totalMatches; // TODO: this shuold be depending on the Tier and queue
      double banRate = 0.20; // TODO: work on a way to calculate the ban rate
      ChampionStatsDto statsDto = ChampionStatsDto.builder()
          .winRate(winRate)
          .tierId(4)
          .roleId(3)
          .regionId(1)
          .queueId(420)
          .pickRate(pickRate)
          .laneId(4)
          .championId(champion.getId())
          .banRate(banRate)
          .build();
      championStatsRepository.add(statsDto);
    });
  }

  @Override
  public void onApplicationEvent(DataCollectionEvent dataCollectionEvent) {
    orchestrator();
    log.info(dataCollectionEvent.getMessage());
  }
}
