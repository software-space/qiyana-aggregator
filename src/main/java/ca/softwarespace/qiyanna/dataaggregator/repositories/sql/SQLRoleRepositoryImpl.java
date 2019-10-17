package ca.softwarespace.qiyanna.dataaggregator.repositories.sql;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.RoleDto;
import ca.softwarespace.qiyanna.dataaggregator.models.exceptions.RecordNotFoundException;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.RoleRecord;
import ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces.SQLRoleRepository;

import static ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.Role.ROLE;

@Repository
public class SQLRoleRepositoryImpl implements SQLRoleRepository {

	private final DSLContext dsl;

	@Autowired
	public SQLRoleRepositoryImpl(DSLContext dsl) {
		this.dsl = dsl;
	}

	@Override
	public RoleDto findById(int id) throws RecordNotFoundException {
		RoleRecord queryResult = dsl.selectFrom(ROLE)
				.where(ROLE.ROLEID.eq(id)).fetchOne();

		if (queryResult == null) {
			throw new RecordNotFoundException("No Aggregator info with ID: " + id);
		}
		return convertRecordToDto(queryResult);
	}

	private RoleDto convertRecordToDto(RoleRecord infoRecord) {
		return RoleDto.builder()
				.roleId(infoRecord.getRoleid())
				.name(infoRecord.getName())
				.build();
	}
}
