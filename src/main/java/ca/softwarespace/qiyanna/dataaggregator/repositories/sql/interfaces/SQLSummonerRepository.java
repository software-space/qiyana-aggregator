package ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.SummonerDto;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import java.util.List;

public interface SQLSummonerRepository {

  SummonerDto add(SummonerDto dto);

  SummonerDto update(SummonerDto dto);

  List<SummonerDto> findAll();

  SummonerDto findByAccountId(String accountId);

  SummonerDto convertSummonerOriannaToDto(Summoner summoner);
}
