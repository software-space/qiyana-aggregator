package ca.softwarespace.qiyanna.dataaggregator.repositories.sql;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.LaneDto;
import ca.softwarespace.qiyanna.dataaggregator.models.exceptions.RecordNotFoundException;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.LaneRecord;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLLaneRepository;

import static ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.Lane.LANE;

@Repository
public class SQLLaneRepositoryImpl implements SQLLaneRepository {

	private final DSLContext dsl;

	@Autowired
	public SQLLaneRepositoryImpl(DSLContext dsl) {
		this.dsl = dsl;
	}

	@Override
	public LaneDto findById(int id) throws RecordNotFoundException {
		LaneRecord queryResult = dsl.selectFrom(LANE)
				.where(LANE.LANEID.eq(id)).fetchOne();

		if (queryResult == null) {
			throw new RecordNotFoundException("No Aggregator info with ID: " + id);
		}
		return convertRecordToDto(queryResult);
	}

	private LaneDto convertRecordToDto(LaneRecord infoRecord) {
		return LaneDto.builder()
				.laneId(infoRecord.getLaneid())
				.name(infoRecord.getName())
				.build();
	}
}
