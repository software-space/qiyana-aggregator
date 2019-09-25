package ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.TierDto;
import ca.softwarespace.qiyanna.dataaggregator.models.exceptions.RecordNotFoundException;

public interface SQLTierRepository {

  TierDto findByTierName(String tierName) throws RecordNotFoundException;
}
