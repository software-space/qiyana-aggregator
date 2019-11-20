package ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.LaneDto;
import ca.softwarespace.qiyanna.dataaggregator.models.exceptions.RecordNotFoundException;

public interface SQLLaneRepository {
	LaneDto findById(int id) throws RecordNotFoundException;
}
