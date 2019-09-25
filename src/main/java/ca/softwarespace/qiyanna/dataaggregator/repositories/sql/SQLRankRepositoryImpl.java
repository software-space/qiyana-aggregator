package ca.softwarespace.qiyanna.dataaggregator.repositories.sql;

import static ca.softwarespace.qiyanna.dataaggregator.models.generated.Tables.RANK;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.RankDto;
import ca.softwarespace.qiyanna.dataaggregator.models.exceptions.RecordNotFoundException;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.RankRecord;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLRankRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SQLRankRepositoryImpl implements SQLRankRepository {
  private final DSLContext dsl;

  @Autowired
  public SQLRankRepositoryImpl(DSLContext dsl) {
    this.dsl = dsl;
  }

  @Transactional(readOnly = true)
  @Override
  public RankDto findByRankName(String rankName) throws RecordNotFoundException {
    RankRecord queryResult = dsl.selectFrom(RANK).where(RANK.NAME.like(rankName)).fetchOne();
    if (queryResult == null) {
      throw new RecordNotFoundException("No rank with the name: " + rankName);
    }
    return convertRecordToDto(queryResult);
  }

  private RankDto convertRecordToDto(RankRecord record) {
    return RankDto.builder()
        .name(record.getName())
        .rankId(record.getRankid())
        .build();
  }
}
