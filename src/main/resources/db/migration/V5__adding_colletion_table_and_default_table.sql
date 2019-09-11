DROP TABLE IF EXISTS Default_Summoner_name CASCADE;
DROP TABLE IF EXISTS Aggregator_info CASCADE;

CREATE TABLE Default_Summoner_name
(
  id         serial,
  name       varchar(255),
  regionName varchar(6),
  primary key (id)
);

CREATE TABLE Aggregator_info
(
  id    serial,
  count integer,
  primary key (id)
);

INSERT INTO Default_Summoner_name (id, name, regionName)
VALUES (1, 'fastboyz', 'NA'),
       (2, 'Marcarrian', 'EUW');

INSERT INTO Aggregator_info (id, count)
VALUES (1,0);
