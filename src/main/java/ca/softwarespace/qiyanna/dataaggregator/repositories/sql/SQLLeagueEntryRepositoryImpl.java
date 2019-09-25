package ca.softwarespace.qiyanna.dataaggregator.repositories.sql;

import static ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.LeagueEntry.LEAGUE_ENTRY;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.LeagueEntryDto;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.LeagueEntryRecord;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLLeagueEntryRepository;
import com.merakianalytics.orianna.types.core.league.LeagueEntry;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SQLLeagueEntryRepositoryImpl implements SQLLeagueEntryRepository {

  private final DSLContext dsl;

  @Autowired
  public SQLLeagueEntryRepositoryImpl(DSLContext dsl) {
    this.dsl = dsl;
  }

  @Transactional
  @Override
  public LeagueEntryDto add(LeagueEntryDto dto) {
    LeagueEntryRecord persisted = dsl.insertInto(LEAGUE_ENTRY)
        .set(createRecord(dto))
        .returning().fetchOne();

    return convertRecordToDto(persisted);
  }

  @Transactional
  @Override
  public LeagueEntryDto update(LeagueEntryDto dto) {
    dsl.update(LEAGUE_ENTRY)
        .set(LEAGUE_ENTRY.QUEUEID, dto.getQueueId())
        .set(LEAGUE_ENTRY.RANKID, dto.getRankId())
        .set(LEAGUE_ENTRY.ACCOUNTID, dto.getAccountId())
        .set(LEAGUE_ENTRY.TIERID, dto.getTierId())
        .set(LEAGUE_ENTRY.FRESHBLOOD, dto.isFreshBlood())
        .set(LEAGUE_ENTRY.HOTSTREAK, dto.isHotStreak())
        .set(LEAGUE_ENTRY.INACTIVE, dto.isInactive())
        .set(LEAGUE_ENTRY.LEAGUEID, dto.getLeagueId())
        .set(LEAGUE_ENTRY.LEAGUEPOINTS, dto.getLeaguePoints())
        .set(LEAGUE_ENTRY.LOSSES, dto.getLosses())
        .set(LEAGUE_ENTRY.VETERAN, dto.isVeteran())
        .set(LEAGUE_ENTRY.WINS, dto.getWins()).execute();
    return findByAccountId(dto.getAccountId());
  }

  @Transactional(readOnly = true)
  @Override
  public LeagueEntryDto findByAccountId(String accountId) {
    LeagueEntryRecord queryResult = dsl.selectFrom(LEAGUE_ENTRY)
        .where(LEAGUE_ENTRY.ACCOUNTID.eq(accountId)).fetchOne();
    if (queryResult == null) {
      return null;
    } else {
      return convertRecordToDto(queryResult);
    }
  }

  @Override
  public LeagueEntryDto convertLeagueEntryOriannaToDto(LeagueEntry leagueEntry, String accountId,
      int tierId, int rankId) {
    return LeagueEntryDto.builder()
        .accountId(accountId)
        .freshBlood(leagueEntry.isFreshBlood())
        .hotStreak(leagueEntry.isOnHotStreak())
        .inactive(leagueEntry.isInactive())
        .leagueId(leagueEntry.getLeague().getId())
        .leaguePoints(leagueEntry.getLeaguePoints())
        .losses(leagueEntry.getLosses())
        .queueId(leagueEntry.getQueue().getId())
        .rankId(rankId)
        .tierId(tierId)
        .veteran(leagueEntry.isVeteran())
        .wins(leagueEntry.getWins())
        .build();
  }

  private LeagueEntryDto convertRecordToDto(LeagueEntryRecord record) {
    return LeagueEntryDto.builder()
        .accountId(record.getAccountid())
        .freshBlood(record.getFreshblood())
        .hotStreak(record.getHotstreak())
        .inactive(record.getInactive())
        .leagueEntryId(record.getLeagueentryid())
        .leagueId(record.getLeagueid())
        .leaguePoints(record.getLeaguepoints())
        .losses(record.getLosses())
        .queueId(record.getQueueid())
        .rankId(record.getRankid())
        .tierId(record.getTierid())
        .veteran(record.getVeteran())
        .wins(record.getWins())
        .build();
  }

  private LeagueEntryRecord createRecord(LeagueEntryDto dto) {
    LeagueEntryRecord record = new LeagueEntryRecord();
    record.setAccountid(dto.getAccountId());
    record.setFreshblood(dto.isFreshBlood());
    record.setHotstreak(dto.isHotStreak());
    record.setInactive(dto.isInactive());
    record.setLeagueid(dto.getLeagueId());
    record.setLeaguepoints(dto.getLeaguePoints());
    record.setLosses(dto.getLosses());
    record.setQueueid(dto.getQueueId());
    record.setRankid(dto.getRankId());
    record.setTierid(dto.getTierId());
    record.setVeteran(dto.isVeteran());
    record.setWins(dto.getWins());
    return record;
  }
}
