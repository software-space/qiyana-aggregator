package ca.softwarespace.qiyanna.dataaggregator.repositories.sql;

import static ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.ChampionStats.CHAMPION_STATS;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.ChampionStatsDto;
import ca.softwarespace.qiyanna.dataaggregator.models.exceptions.RecordNotFoundException;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.ChampionStatsRecord;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLChampionStatsRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SQLChampionStatsRepositoryImpl implements SQLChampionStatsRepository {

  private final DSLContext dslContext;

  @Autowired
  public SQLChampionStatsRepositoryImpl(DSLContext dslContext) {
    this.dslContext = dslContext;
  }

  @Transactional(readOnly = true)
  @Override
  public ChampionStatsDto findByIds(int championId, int laneId, int tierId, int patchId,
      int queueId, int regionId, int roleId) throws RecordNotFoundException {
    ChampionStatsRecord queryResult = dslContext.selectFrom(CHAMPION_STATS)
        .where(CHAMPION_STATS.CHAMPIONID.eq(championId))
        .and(CHAMPION_STATS.LANEID.eq(laneId))
        .and(CHAMPION_STATS.TIERID.eq(tierId))
        .and(CHAMPION_STATS.PATCHID.eq(patchId))
        .and(CHAMPION_STATS.QUEUEID.eq(queueId))
        .and(CHAMPION_STATS.REGIONID.eq(regionId))
        .and(CHAMPION_STATS.ROLEID.eq(roleId)).fetchOne();

    if (queryResult == null) {
      throw new RecordNotFoundException("No Champion Stat found with different Ids");
    } else {
      return convertRecordToDto(queryResult);
    }
  }

  @Override
  public boolean update(ChampionStatsDto statsDto) {
    dslContext.update(CHAMPION_STATS)
        .set(CHAMPION_STATS.BANRATE, statsDto.getBanRate())
        .set(CHAMPION_STATS.WINRATE, statsDto.getWinRate())
        .set(CHAMPION_STATS.PICKRATE, statsDto.getPickRate())
        .where(CHAMPION_STATS.CHAMPIONSTATSID.eq(statsDto.getChampionStatsId()));
    return false;
  }

  @Override
  public boolean add(ChampionStatsDto dto) {
    ChampionStatsRecord persisted = dslContext.insertInto(CHAMPION_STATS)
        .set(createRecord(dto))
        .returning()
        .fetchOne();

    return persisted != null;
  }

  private ChampionStatsDto convertRecordToDto(ChampionStatsRecord record) {
    return ChampionStatsDto.builder()
        .banRate(record.getBanrate())
        .championId(record.getChampionid())
        .laneId(record.getLaneid())
        .patchId(record.getPatchid())
        .pickRate(record.getPickrate())
        .queueId(record.getQueueid())
        .regionId(record.getRegionid())
        .championStatsId(record.getChampionstatsid())
        .roleId(record.getRoleid())
        .tierId(record.getTierid())
        .winRate(record.getWinrate())
        .build();
  }

  private ChampionStatsRecord createRecord(ChampionStatsDto dto) {
    ChampionStatsRecord record = new ChampionStatsRecord();
    record.setBanrate(dto.getBanRate());
    record.setChampionid(dto.getChampionId());
    record.setLaneid(dto.getLaneId());
    record.setPatchid(dto.getPatchId() == 0 ? null : dto.getPatchId());
    record.setPickrate(dto.getPickRate());
    record.setQueueid(dto.getQueueId());
    record.setRegionid(dto.getRegionId());
    record.setRoleid(dto.getRoleId());
    record.setTierid(dto.getTierId());
    record.setWinrate(dto.getWinRate());
    return record;
  }
}
