package ca.softwarespace.qiyanna.dataaggregator.repositories.sql;

import static ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.Summoner.SUMMONER;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.SummonerDto;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.SummonerRecord;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLSummonerRepository;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import java.util.List;
import java.util.stream.Collectors;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SQLSummonerRepositoryImpl implements SQLSummonerRepository {

  private final DSLContext dsl;

  @Autowired
  public SQLSummonerRepositoryImpl(DSLContext dsl) {
    this.dsl = dsl;
  }

  @Transactional
  @Override
  public SummonerDto add(SummonerDto dto) {
    SummonerRecord persisted = dsl.insertInto(SUMMONER)
        .set(createRecord(dto))
        .returning().fetchOne();
    return convertRecordToDto(persisted);
  }

  @Transactional
  @Override
  public SummonerDto update(SummonerDto dto) {
    dsl.update(SUMMONER)
        .set(SUMMONER.NAME, dto.getName())
        .set(SUMMONER.SUMMONERID, dto.getSummonerId())
        .set(SUMMONER.SUMMONERLEVEL, dto.getSummonerLevel())
        .set(SUMMONER.PROFILEICONID, dto.getProfileIconId())
        .set(SUMMONER.PUUID, dto.getPuuid())
        .set(SUMMONER.REVISIONDATE, dto.getRevisionDate())
        .where(SUMMONER.ACCOUNTID.equalIgnoreCase(dto.getAccountId())).execute();
    return findByAccountId(dto.getAccountId());
  }

  @Transactional(readOnly = true)
  @Override
  public List<SummonerDto> findAll() {
    List<SummonerRecord> queryResults = dsl.selectFrom(SUMMONER).fetchInto(SummonerRecord.class);
    return queryResults.stream().map(this::convertRecordToDto).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  @Override
  public SummonerDto findByAccountId(String accountId) {
    SummonerRecord queryResult = dsl.selectFrom(SUMMONER)
        .where(SUMMONER.ACCOUNTID.eq(accountId))
        .fetchOne();
    if (queryResult == null) {
      return null;
    } else {
      return convertRecordToDto(queryResult);
    }
  }

  public SummonerDto convertSummonerOriannaToDto(Summoner summoner) {
    return SummonerDto.builder()
        .accountId(summoner.getAccountId())
        .summonerId(summoner.getId())
        .name(summoner.getName())
        .profileIconId(summoner.getProfileIcon().getId())
        .puuid(summoner.getPuuid())
        .revisionDate(summoner.getUpdated().getMillis())
        .summonerLevel(summoner.getLevel())
        .build();
  }

  private SummonerRecord createRecord(SummonerDto dto) {
    SummonerRecord record = new SummonerRecord();
    record.setAccountid(dto.getAccountId());
    record.setSummonerid(dto.getSummonerId());
    record.setName(dto.getName());
    record.setProfileiconid(dto.getProfileIconId());
    record.setPuuid(dto.getPuuid());
    record.setRevisiondate(dto.getRevisionDate());
    record.setSummonerlevel(dto.getSummonerLevel());
    return record;
  }

  private SummonerDto convertRecordToDto(SummonerRecord record) {
    return SummonerDto.builder()
        .accountId(record.getAccountid())
        .summonerId(record.getSummonerid())
        .name(record.getName())
        .profileIconId(record.getProfileiconid())
        .puuid(record.getPuuid())
        .revisionDate(record.getRevisiondate())
        .summonerLevel(record.getSummonerlevel())
        .build();
  }
}
