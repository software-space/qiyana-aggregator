CREATE TABLE Game_Mode
(
  gameModeId  serial,
  name        varchar(1024),
  description varchar(2018),
  primary key (gameModeId)
);

ALTER TABLE queue
  ALTER COLUMN name TYPE varchar(1024);
ALTER TABLE qiyanna.queue
  ADD column gameModeId integer;
ALTER TABLE qiyanna.queue
  ADD CONSTRAINT fk_game_mode FOREIGN KEY (gameModeId) REFERENCES Game_Mode (gameModeId);

INSERT INTO Game_Mode (gameModeId, name, description)
VALUES (1, 'CLASSIC', 'Classic Summoner''s Rift and Twisted Treeline games'),
       (2, 'ODIN', 'Dominion/Crystal Scar games'),
       (3, 'ARAM', 'ARAM games'),
       (4, 'TUTORIAL', 'Tutorial games'),
       (5, 'URF', 'URF games'),
       (6, 'DOOMBOTSTEEMO', 'Doom Bot games'),
       (7, 'ONEFORALL', 'One for All games'),
       (8, 'ASCENSION', 'Ascension games'),
       (9, 'FIRSTBLOOD', 'Snowdown Showdown games'),
       (10, 'KINGPORO', 'Legend of the Poro King games'),
       (11, 'SIEGE', 'Nexus Siege games'),
       (12, 'ASSASSINATE', 'Blood Hunt Assassin games'),
       (13, 'ARSR', 'All Random Summoner''s Rift games'),
       (14, 'DARKSTAR', 'Dark Star: Singularity games'),
       (15, 'STARGUARDIAN', 'Star Guardian Invasion games'),
       (16, 'PROJECT', 'PROJECT: Hunters games'),
       (17, 'GAMEMODEX', 'Nexus Blitz games'),
       (18, 'ODYSSEY', 'Odyssey: Extraction games');



INSERT INTO qiyanna.queue (queueId, name, gameModeId)
VALUES (72, '1v1 Snowdown Showdown games', 12),
       (73, '2v2 Snowdown Showdown games', 12),
       (75, '6v6 Hexakill games', 11),
       (76, 'Ultra Rapid Fire games', 11),
       (78, 'One For All: Mirror Mode games', 12),
       (83, 'Co-op vs AI Ultra Rapid Fire games', 11),
       (98, '6v6 Hexakill games', 10),
       (100, '5v5 ARAM games', 14),
       (310, 'Nemesis games', 11),
       (313, 'Black Market Brawlers games', 11),
       (317, 'Definitely Not Dominion games', 8),
       (325, 'All Random games', 13),
       (400, '5v5 Draft Pick games', 1),
       (420, '5v5 Ranked Solo games', 1),
       (430, '5v5 Blind Pick games', 1),
       (440, '5v5 Ranked Flex games', 1),
       (450, 'Howling Abyss', 3),
       (460, 'Twisted Treeline', 1),
       (470, 'Twisted Treeline', 1),
       (600, '3v3 Blind Pick games', 1),
       (610, '3v3 Ranked Flex games', 1),
       (700, 'Clash games', 1),
       (800, 'Co-op vs. AI Intermediate Bot games', 1),
       (810, 'Co-op vs. AI Intro Bot games', 1),
       (820, 'Co-op vs. AI Beginner Bot games', 1),
       (830, 'Co-op vs. AI Intro Bot games', 1),
       (840, 'Co-op vs. AI Beginner Bot games', 1),
       (850, 'Co-op vs. AI Intermediate Bot games', 1),
       (900, 'ARURF games', 5),
       (910, 'Ascension games', 8),
       (920, 'Legend of the Poro King games', 10),
       (940, 'Nexus Siege games', 11),
       (950, 'Doom Bots Voting games', 6),
       (960, 'Doom Bots Standard games', 6),
       (980, 'Star Guardian Invasion: Normal games', 15),
       (990, 'Star Guardian Invasion: Onslaught games', 15),
       (1000, 'PROJECT: Hunters games', 16),
       (1010, 'Snow ARURF games', 5),
       (1020, 'One for All games', 7),
       (1030, 'Odyssey Extraction: Intro games', 18),
       (1040, 'Odyssey Extraction: Cadet games', 18),
       (1050, 'Odyssey Extraction: Crewmember games', 18),
       (1060, 'Odyssey Extraction: Captain games', 18),
       (1070, 'Odyssey Extraction: Onslaught games', 18),
       (1090, 'Teamfight Tactics games', null),
       (1100, 'Ranked Teamfight Tactics games', null);

INSERT INTO season (seasonId, name)
VALUES (0, 'PRESEASON 3'),
       (1, 'SEASON 3'),
       (2, 'PRESEASON 2014'),
       (3, 'SEASON 2014'),
       (4, 'PRESEASON 2015'),
       (5, 'SEASON 2015'),
       (6, 'PRESEASON 2016'),
       (7, 'SEASON 2016'),
       (8, 'PRESEASON 2017'),
       (9, 'SEASON 2017'),
       (10, 'PRESEASON 2018'),
       (11, 'SEASON 2018'),
       (12, 'PRESEASON 2019'),
       (13, 'SEASON 2019');

INSERT INTO lane (name)
  VALUES
    ('TOP'),
    ('JUNGLE'),
    ('MID'),
    ('MIDDLE'),
    ('BOT'),
    ('BOTTOM');

