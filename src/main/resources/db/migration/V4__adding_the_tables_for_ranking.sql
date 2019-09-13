DROP TABLE IF EXISTS Rank CASCADE;
DROP TABLE IF EXISTS League_entry CASCADE;

CREATE TABLE rank
(
  rankId serial,
  name   varchar(4),
  primary key (rankId)
);

CREATE TABLE League_entry
(
  leagueEntryId serial,
  queueId       integer REFERENCES queue (queueId),
  summonerId    varchar(56) REFERENCES summoner (accountId),
  wins          integer,
  hotStreak     boolean,
  veteran       boolean,
  losses        integer,
  rankId        integer REFERENCES rank (rankId),
  leagueId      varchar(100),
  inactive      boolean,
  freshBlood    boolean,
  tierId        integer REFERENCES tier (tierId),
  leaguePoints  integer CHECK (leaguePoints <= 100),
  primary key (leagueEntryId)
);

INSERT INTO rank(name)
VALUES ('I'),
       ('II'),
       ('III'),
       ('IV');