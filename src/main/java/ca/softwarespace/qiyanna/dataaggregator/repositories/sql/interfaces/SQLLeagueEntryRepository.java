package ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.LeagueEntryDto;
import com.merakianalytics.orianna.types.core.league.LeagueEntry;

public interface SQLLeagueEntryRepository {

  LeagueEntryDto add(LeagueEntryDto dto);

  LeagueEntryDto update(LeagueEntryDto dto);

  LeagueEntryDto findByAccountId(String accountId);

  LeagueEntryDto convertLeagueEntryOriannaToDto(LeagueEntry leagueEntry, String accountId,
      int tierId, int rankId);

}
