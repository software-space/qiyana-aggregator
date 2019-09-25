package ca.softwarespace.qiyanna.dataaggregator.repositories.sql;

import static ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.DefaultSummonerName.DEFAULT_SUMMONER_NAME;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.DefaultSummonerDto;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.DefaultSummonerNameRecord;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLDefaultSummonerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SQLDefaultSummonerRepositoryImpl implements SQLDefaultSummonerRepository {

  private final DSLContext dsl;

  @Autowired
  public SQLDefaultSummonerRepositoryImpl(DSLContext dsl) {
    this.dsl = dsl;
  }


  @Transactional(readOnly = true)
  @Override
  public List<DefaultSummonerDto> findAll() {
    List<DefaultSummonerNameRecord> queryResults = dsl.selectFrom(DEFAULT_SUMMONER_NAME)
        .fetchInto(DefaultSummonerNameRecord.class);
    return queryResults.stream().map(this::convertRecorfToDto).collect(Collectors.toList());
  }

  private DefaultSummonerDto convertRecorfToDto(DefaultSummonerNameRecord record) {
    return DefaultSummonerDto.builder()
        .id(record.getId())
        .name(record.getName())
        .regionName(record.getRegionname())
        .build();
  }
}
