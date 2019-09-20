package ca.softwarespace.qiyanna.dataaggregator.services;

import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_DOCUMENT_PARTICIPANT_CHAMPION_KEY;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_DOCUMENT_PARTICIPANT_STAT_WIN;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.MATCH_DOCUMENT_QUEUE_KEY;
import static ca.softwarespace.qiyanna.dataaggregator.util.Constants.RESULT_KEY;

import ca.softwarespace.qiyanna.dataaggregator.events.DataCollectionEvent;
import ca.softwarespace.qiyanna.dataaggregator.models.CalculationResult;
import ca.softwarespace.qiyanna.dataaggregator.services.interfaces.StatsCalculatorService;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.jooq.DSLContext;
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

  private final DSLContext dsl;
  private final MongoTemplate mongoTemplate;

  public StatsCalculatorServiceImpl(DSLContext dsl, MongoTemplate mongoTemplate) {
    this.dsl = dsl;
    this.mongoTemplate = mongoTemplate;
  }


  private void calculateChampionWins(int championId, int queueId, int roleId, int laneId,
      int tierId, int regionId) {

    MatchOperation firstMatchOperation = Aggregation
        .match(new Criteria(MATCH_DOCUMENT_QUEUE_KEY).is(queueId)
            .and(MATCH_DOCUMENT_PARTICIPANT_CHAMPION_KEY).is(championId));
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
        .match(new Criteria(MATCH_DOCUMENT_PARTICIPANT_STAT_WIN).is(true));
    GroupOperation groupOperation = Aggregation.group().count().as(RESULT_KEY);

    ArrayList<AggregationOperation> operations = new ArrayList<>();
    operations.add(firstMatchOperation);
    operations.add(projectionOperation);
    operations.add(secondMatchOperation);
    operations.add(groupOperation);

    Aggregation aggregation = Aggregation.newAggregation(operations);
    AggregationResults<CalculationResult> results = mongoTemplate
        .aggregate(aggregation, "dto.match.Match", CalculationResult.class);
    results.forEach(c -> System.out.println(c.getResult()));
  }

  @Override
  public void onApplicationEvent(DataCollectionEvent dataCollectionEvent) {
    log.info(dataCollectionEvent.getMessage());
  }
}
