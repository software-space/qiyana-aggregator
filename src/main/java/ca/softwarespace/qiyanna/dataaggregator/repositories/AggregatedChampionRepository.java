package ca.softwarespace.qiyanna.dataaggregator.repositories;

import ca.softwarespace.qiyanna.dataaggregator.models.dao.AggregatedChampionDao;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AggregatedChampionRepository {

  private final MongoTemplate mongoTemplate;

  public void saveOrUpdate(List<AggregatedChampionDao> aggregatedChampionDaos) {
    for (AggregatedChampionDao aggregatedChampionDao : aggregatedChampionDaos) {
      Criteria byAccountId = Criteria.where("accountId").is(aggregatedChampionDao.getAccountId());
      Criteria byChampionName = Criteria.where("name").is(aggregatedChampionDao.getName());
      Criteria criteria = new Criteria().andOperator(
          byAccountId,
          byChampionName
      );
      Query query = Query.query(criteria);
      AggregatedChampionDao aggregatedChampionDaoFromDb = mongoTemplate.findOne(query, AggregatedChampionDao.class);
      if (aggregatedChampionDaoFromDb == null) {
        mongoTemplate.save(aggregatedChampionDao);
      } else {
        // TODO yeah.. I do not want stuff like this. refactor it asap maybe there's a better way
        aggregatedChampionDaoFromDb.setPlayed(aggregatedChampionDao.getPlayed());
        aggregatedChampionDaoFromDb.setWins(aggregatedChampionDao.getWins());
        aggregatedChampionDaoFromDb.setLosses(aggregatedChampionDao.getLosses());
        aggregatedChampionDaoFromDb.setWinrate(aggregatedChampionDao.getWinrate());
        aggregatedChampionDaoFromDb.setAverageKills(aggregatedChampionDao.getAverageKills());
        aggregatedChampionDaoFromDb.setAverageDeaths(aggregatedChampionDao.getAverageDeaths());
        aggregatedChampionDaoFromDb.setAverageAssists(aggregatedChampionDao.getAverageAssists());
        aggregatedChampionDaoFromDb.setAverageCs(aggregatedChampionDao.getAverageCs());
        aggregatedChampionDaoFromDb.setAverageGold(aggregatedChampionDao.getAverageGold());
        aggregatedChampionDaoFromDb.setAverageCsPerMin(aggregatedChampionDao.getAverageCsPerMin());
        mongoTemplate.save(aggregatedChampionDaoFromDb);
      }
    }
  }
}
