CREATE TABLE Game_Mode
(
  gameModeId integer,
  name varchar(1024),
  description varchar(2048),
  primary key (mapId)
);

ALTER TABLE qiyanna.queue ALTER COLUMN name TYPE varchar(1024);
ALTER TABLE  qiyanna.queue ADD column gameModeId TYPE integer;
ALTER TABLE qiyanna.queue ADD CONSTRAINT fk_game_mode FOREIGN KEY (gameModeId) REFERENCES Game_Mode(gameModeId);

INSERT INTO Game_Mode (gameModeId, name, description)
  VALUES (1, 'CLASSIC', 	'Classic Summoner''s Rift and Twisted Treeline games'),
  VALUES (2, 'ODIN', 	'Dominion/Crystal Scar games'),
  VALUES (3, 'ARAM', 	'ARAM games'),
  VALUES (4, 'TUTORIAL', 	'Tutorial games'),
  VALUES (5, 'URF', 	'URF games'),
  VALUES (6, 'DOOMBOTSTEEMO', 	'Doom Bot games'),
  VALUES (7, 'ONEFORALL', 	'One for All games'),
  VALUES (8, 'ASCENSION', 	'Ascension games'),
  VALUES (9, 'FIRSTBLOOD', 	'Snowdown Showdown games'),
  VALUES (10, 'KINGPORO', 	'Legend of the Poro King games'),
  VALUES (11, 'SIEGE', 	'Nexus Siege games'),
  VALUES (12, 'ASSASSINATE', 	'Blood Hunt Assassin games'),
  VALUES (13, 'ARSR', 	'All Random Summoner''s Rift games'),
  VALUES (14, 'DARKSTAR', 	'Dark Star: Singularity games'),
  VALUES (15, 'STARGUARDIAN', 	'Star Guardian Invasion games'),
  VALUES (16, 'PROJECT', 	'PROJECT: Hunters games'),
  VALUES (17, 'GAMEMODEX', 	'Nexus Blitz games'),
  VALUES (48, 'ODYSSEY', 	'Odyssey: Extraction games'),




INSERT INTO qiyanna.queue (queueId, name, mapId)
  VALUES (72, '1v1 Snowdown Showdown games', 12),
  VALUES (73, '2v2 Snowdown Showdown games', 12),
  VALUES (75, '6v6 Hexakill games', 11),
  VALUES (76, 'Ultra Rapid Fire games', 11),
  VALUES (78, 'One For All: Mirror Mode games', 12),
  VALUES (83, 'Co-op vs AI Ultra Rapid Fire games', 11),
  VALUES (98, '6v6 Hexakill games', 10),
  VALUES (100, '5v5 ARAM games', 14),
  VALUES (310, 'Nemesis games', 11),
  VALUES (313, 'Black Market Brawlers games', 11),
  VALUES (313, 'Definitely Not Dominion games', 8),

