package ca.softwarespace.qiyanna.dataaggregator.repositories;

import com.merakianalytics.orianna.types.dto.match.Match;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

public class MatchRepository implements MongoRepository<Match, String > {

  @Override
  public <S extends Match> S save(S s) {
    return null;
  }

  @Override
  public <S extends Match> List<S> saveAll(Iterable<S> iterable) {
    return null;
  }

  @Override
  public Optional<Match> findById(String s) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(String s) {
    return false;
  }

  @Override
  public List<Match> findAll() {
    return null;
  }

  @Override
  public Iterable<Match> findAllById(Iterable<String> iterable) {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(String s) {

  }

  @Override
  public void delete(Match match) {

  }

  @Override
  public void deleteAll(Iterable<? extends Match> iterable) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public List<Match> findAll(Sort sort) {
    return null;
  }

  @Override
  public <S extends Match> S insert(S s) {
    return null;
  }

  @Override
  public <S extends Match> List<S> insert(Iterable<S> iterable) {
    return null;
  }

  @Override
  public Page<Match> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public <S extends Match> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  @Override
  public <S extends Match> List<S> findAll(Example<S> example) {
    return null;
  }

  @Override
  public <S extends Match> List<S> findAll(Example<S> example, Sort sort) {
    return null;
  }

  @Override
  public <S extends Match> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  @Override
  public <S extends Match> long count(Example<S> example) {
    return 0;
  }

  @Override
  public <S extends Match> boolean exists(Example<S> example) {
    return false;
  }
}
