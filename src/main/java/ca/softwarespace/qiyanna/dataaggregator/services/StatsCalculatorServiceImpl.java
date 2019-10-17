package ca.softwarespace.qiyanna.dataaggregator.services;

import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_DOCUMENT_PARTICIPANT_CHAMPION_KEY;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_DOCUMENT_PARTICIPANT_LANE;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_DOCUMENT_PARTICIPANT_ROLE;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_DOCUMENT_PARTICIPANT_STAT_WIN;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_DOCUMENT_QUEUE_KEY;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_PLATFORM_ID;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.RESULT_KEY;

import ca.softwarespace.qiyanna.dataaggregator.events.DataCollectionEvent;
import ca.softwarespace.qiyanna.dataaggregator.models.CalculationResult;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.LaneDto;
import ca.softwarespace.qiyanna.dataaggregator.models.dto.RoleDto;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLAggregatorInfoRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLDefaultSummonerRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLLaneRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLLeagueEntryRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLRankRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLRoleRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLSummonerRepository;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLTierRepository;
import ca.softwarespace.qiyanna.dataaggregator.services.interfaces.StatsCalculatorService;
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

  @Autowired
  public StatsCalculatorServiceImpl(DSLContext dsl, MongoTemplate mongoTemplate,
      SQLSummonerRepository sqlSummonerRepository,
      SQLAggregatorInfoRepository sqlAggregatorInfoRepository,
      SQLDefaultSummonerRepository sqlDefaultSummonerRepository,
      SQLLeagueEntryRepository sqlLeagueEntryRepository,
      SQLTierRepository sqlTierRepository, SQLRankRepository sqlRankRepository,
      SQLLaneRepository sqlLaneRepository, SQLRoleRepository sqlRoleRepository) {
    this.mongoTemplate = mongoTemplate;
    this.sqlSummonerRepository = sqlSummonerRepository;
    this.sqlAggregatorInfoRepository = sqlAggregatorInfoRepository;
    this.sqlDefaultSummonerRepository = sqlDefaultSummonerRepository;
    this.sqlLeagueEntryRepository = sqlLeagueEntryRepository;
    this.sqlTierRepository = sqlTierRepository;
    this.sqlRankRepository = sqlRankRepository;
    this.sqlLaneRepository = sqlLaneRepository;
    this.sqlRoleRepository = sqlRoleRepository;
  }


  public void calculateChampionWins(int championId, int queueId, int roleId, int laneId,
      int tierId, String regionName) {
    try {
      LaneDto lane = sqlLaneRepository.findById(laneId);
      RoleDto role = sqlRoleRepository.findById(roleId);
      if (regionName.equalsIgnoreCase("NA")) {
        regionName = "NA1";
      } else {
        regionName = regionName.toUpperCase();
      }
      MatchOperation firstMatchOperation = Aggregation
          .match(new Criteria(MATCH_DOCUMENT_QUEUE_KEY).is(queueId)
              .and(MATCH_DOCUMENT_PARTICIPANT_CHAMPION_KEY).is(championId)
              .and(MATCH_PLATFORM_ID).is(regionName));
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
      results.forEach(c -> System.out.println(c.getResult()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  @Override
  public void onApplicationEvent(DataCollectionEvent dataCollectionEvent) {
    log.info(dataCollectionEvent.getMessage());
  }
}
