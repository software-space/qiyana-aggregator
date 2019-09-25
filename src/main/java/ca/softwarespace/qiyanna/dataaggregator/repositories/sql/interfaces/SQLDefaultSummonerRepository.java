package ca.softwarespace.qiyanna.dataaggregator.repositories.sql.interfaces;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.DefaultSummonerDto;
import java.util.List;

public interface SQLDefaultSummonerRepository {

  List<DefaultSummonerDto> findAll();
}
