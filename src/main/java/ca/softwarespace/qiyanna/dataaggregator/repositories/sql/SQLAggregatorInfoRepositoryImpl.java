package ca.softwarespace.qiyanna.dataaggregator.repositories.sql;

import static ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.AggregatorInfo.AGGREGATOR_INFO;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.AggregatorInfoDto;
import ca.softwarespace.qiyanna.dataaggregator.models.exceptions.RecordNotFoundException;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.AggregatorInfoRecord;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLAggregatorInfoRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SQLAggregatorInfoRepositoryImpl implements SQLAggregatorInfoRepository {

  private final DSLContext dsl;

  @Autowired
  public SQLAggregatorInfoRepositoryImpl(DSLContext dsl) {
    this.dsl = dsl;
  }

  @Transactional
  @Override
  public AggregatorInfoDto update(AggregatorInfoDto dto) throws RecordNotFoundException {
    dsl.update(AGGREGATOR_INFO)
        .set(AGGREGATOR_INFO.COUNT, dto.getCount())
        .execute();
    return findById(dto.getId());
  }

  @Transactional(readOnly = true)
  @Override
  public AggregatorInfoDto findById(int id) throws RecordNotFoundException {
    AggregatorInfoRecord queryResult = dsl.selectFrom(AGGREGATOR_INFO)
        .where(AGGREGATOR_INFO.ID.eq(id)).fetchOne();

    if (queryResult == null) {
      throw new RecordNotFoundException("No Aggregator info with ID: " + id);
    }
    return convertRecordToDto(queryResult);
  }

  private AggregatorInfoDto convertRecordToDto(AggregatorInfoRecord infoRecord) {
    return AggregatorInfoDto.builder()
        .id(infoRecord.getId())
        .count(infoRecord.getCount())
        .build();
  }
}
