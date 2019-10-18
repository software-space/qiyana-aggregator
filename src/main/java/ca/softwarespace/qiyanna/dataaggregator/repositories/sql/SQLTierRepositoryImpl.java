package ca.softwarespace.qiyanna.dataaggregator.repositories.sql;

import static ca.softwarespace.qiyanna.dataaggregator.models.generated.Tables.TIER;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.TierDto;
import ca.softwarespace.qiyanna.dataaggregator.models.exceptions.RecordNotFoundException;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.TierRecord;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLTierRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SQLTierRepositoryImpl implements SQLTierRepository {

  private final DSLContext dsl;

  @Autowired
  public SQLTierRepositoryImpl(DSLContext dsl) {
    this.dsl = dsl;
  }

  @Transactional(readOnly = true)
  @Override
  public TierDto findByTierName(String tierName) throws RecordNotFoundException {
    TierRecord queryResult = dsl.selectFrom(TIER)
        .where(TIER.SHORTNAME.like(tierName)).fetchOne();
    if (queryResult == null) {
      throw new RecordNotFoundException("There is no tier with the name : " + tierName);
    }
    return convertRecordToDto(queryResult);
  }

  @Override
  public TierDto findById(int id) throws RecordNotFoundException {
    TierRecord queryResult = dsl.selectFrom(TIER)
        .where(TIER.TIERID.eq(id)).fetchOne();
    if (queryResult == null) {
      throw new RecordNotFoundException("There is no tier with the id : " + id);
    }
    return convertRecordToDto(queryResult);
  }

  private TierDto convertRecordToDto(TierRecord record) {
    return TierDto.builder()
        .shortName(record.getShortname())
        .tierId(record.getTierid())
        .build();
  }
}
