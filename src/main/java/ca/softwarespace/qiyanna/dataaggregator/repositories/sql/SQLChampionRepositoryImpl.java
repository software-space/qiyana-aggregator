package ca.softwarespace.qiyanna.dataaggregator.repositories.sql;

import static ca.softwarespace.qiyanna.dataaggregator.models.generated.Tables.CHAMPION;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.ChampionDto;
import ca.softwarespace.qiyanna.dataaggregator.models.exceptions.RecordNotFoundException;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.ChampionRecord;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLChampionRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SQLChampionRepositoryImpl implements SQLChampionRepository {

  private final DSLContext dslContext;

  @Autowired
  public SQLChampionRepositoryImpl(DSLContext dslContext) {
    this.dslContext = dslContext;
  }

  @Override
  public boolean add(ChampionDto dto) {
    ChampionRecord persisted = dslContext.insertInto(CHAMPION)
        .set(createRecord(dto))
        .returning()
        .fetchOne();

    return persisted != null;
  }

  @Override
  public ChampionDto findById(int id) throws RecordNotFoundException {
    ChampionRecord queryResult = dslContext.selectFrom(CHAMPION)
        .where(CHAMPION.CHAMPIONID.eq(id)).fetchOne();
    if (queryResult == null) {
      throw new RecordNotFoundException("No Champion found with ID: " + id);
    } else {
      return convertRecordToDto(queryResult);
    }
  }

  @Override
  public List<ChampionDto> findAll() {
    List<ChampionRecord> queryResults = dslContext.selectFrom(CHAMPION)
        .fetchInto(ChampionRecord.class);
    return queryResults.stream().map(this::convertRecordToDto).collect(Collectors.toList());
  }

  private ChampionDto convertRecordToDto(ChampionRecord record) {
    return ChampionDto.builder()
        .id(record.getChampionid())
        .name(record.getName())
        .build();
  }

  private ChampionRecord createRecord(ChampionDto dto) {
    ChampionRecord record = new ChampionRecord();
    record.setChampionid(dto.getId());
    record.setName(dto.getName());
    return record;
  }
}
