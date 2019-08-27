DROP TABLE IF EXISTS Summoner CASCADE;
DROP TABLE IF EXISTS Champion CASCADE;
DROP TABLE IF EXISTS Queue CASCADE;
DROP TABLE IF EXISTS Season CASCADE;
DROP TABLE IF EXISTS Lane CASCADE;
DROP TABLE IF EXISTS Patch CASCADE;
DROP TABLE IF EXISTS Region CASCADE;
DROP TABLE IF EXISTS Rank CASCADE;
DROP TABLE IF EXISTS Summoner_Stats CASCADE;
DROP TABLE IF EXISTS Champion_Stats CASCADE;

CREATE TABLE Summoner
(
  accountId     varchar(56),
  name          varchar(255),
  puuid         varchar(78),
  profileIconId integer,
  summonerLevel bigint,
  revisionDate  bigint,
  primary key (accountId)
);

Create TABLE Champion
(
  championId integer,
  name       varchar(255),
  primary key (championId)
);

CREATE TABLE Queue
(
  queueId integer,
  name    varchar(40),
  primary key (queueId)
);

CREATE TABLE Season
(
  seasonId integer,
  name     varchar(55),
  primary key (seasonId)
);

CREATE TABLE Lane
(
  laneId serial,
  name   varchar(10),
  primary key (laneId)
);

CREATE TABLE Patch
(
  patchId serial,
  name    varchar(50),
  primary key (patchId)
);

CREATE TABLE Region
(
  regionId  serial,
  shortName varchar(10),
  primary key (regionId)
);

CREATE TABLE Rank
(
  rankId    serial,
  shortName varchar(20),
  primary key (rankId)
);

CREATE TABLE Summoner_Stats
(
  SummonerStatsId serial,
  accountId       varchar(56) REFERENCES Summoner (accountId),
  seasonId        integer REFERENCES Season (seasonId),
  laneId          integer REFERENCES Lane (laneId),
  queueId         integer REFERENCES Queue (queueId),
  winRate         float,
  averageKda      float,
  winLossStreak   integer
);

CREATE TABLE Champion_Stats
(
  ChampionStatsId serial,
  championId      integer REFERENCES Champion (championId),
  laneId          integer REFERENCES Lane (laneId),
  queueId         integer REFERENCES Queue (queueId),
  rankId          integer REFERENCES Rank (rankId),
  patchId         integer REFERENCES Patch (patchId),
  regionId        integer REFERENCES Region (regionId),
  winRate         float,
  banRate         float,
  pickRate        float
);
