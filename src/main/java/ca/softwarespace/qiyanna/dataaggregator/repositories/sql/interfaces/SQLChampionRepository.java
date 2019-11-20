package ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.ChampionDto;
import ca.softwarespace.qiyanna.dataaggregator.models.exceptions.RecordNotFoundException;
import java.util.List;

public interface SQLChampionRepository {

  boolean add (ChampionDto dto);

  ChampionDto findById(int id) throws RecordNotFoundException;

  List<ChampionDto> findAll();

}
