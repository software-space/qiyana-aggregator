package ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.AggregatorInfoDto;
import ca.softwarespace.qiyanna.dataaggregator.models.exceptions.RecordNotFoundException;

public interface SQLAggregatorInfoRepository {

  AggregatorInfoDto update(AggregatorInfoDto dto) throws RecordNotFoundException;

  AggregatorInfoDto findById(int id) throws RecordNotFoundException;
}
