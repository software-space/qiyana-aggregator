package ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.ChampionStatsDto;
import ca.softwarespace.qiyanna.dataaggregator.models.exceptions.RecordNotFoundException;

public interface SQLChampionStatsRepository {

  ChampionStatsDto findByIds(int championId, int laneId, int tierId, int patchId, int queueId, int regionId, int roleId)
      throws RecordNotFoundException;

  boolean update(ChampionStatsDto statsDto);

  boolean add(ChampionStatsDto dto);

}
