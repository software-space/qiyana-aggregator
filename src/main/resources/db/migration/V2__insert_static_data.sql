DROP TABLE IF EXISTS Game_Mode CASCADE;
DROP TABLE IF EXISTS Role CASCADE;
DROP TABLE IF EXISTS Map CASCADE;

CREATE TABLE Game_Mode
(
  gameModeId  serial,
  name        varchar(1024),
  description varchar(2018),
  primary key (gameModeId)
);

CREATE TABLE Role
(
  roleId serial,
  name   varchar(50),
  primary key (roleId)
);

CREATE TABLE Map
(
  mapId serial,
  name  varchar(255),
  notes varchar(2048),
  primary key (mapId)
);

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



INSERT INTO queue (queueId, name)
VALUES (72, '1v1 Snowdown Showdown games'),
       (73, '2v2 Snowdown Showdown games'),
       (75, '6v6 Hexakill games'),
       (76, 'Ultra Rapid Fire games'),
       (78, 'One For All: Mirror Mode games'),
       (83, 'Co-op vs AI Ultra Rapid Fire games'),
       (98, '6v6 Hexakill games'),
       (100, '5v5 ARAM games'),
       (310, 'Nemesis games'),
       (313, 'Black Market Brawlers games'),
       (317, 'Definitely Not Dominion games'),
       (325, 'All Random games'),
       (400, '5v5 Draft Pick games'),
       (420, '5v5 Ranked Solo games'),
       (430, '5v5 Blind Pick games'),
       (440, '5v5 Ranked Flex games'),
       (450, 'Howling Abyss'),
       (460, 'Twisted Treeline'),
       (470, 'Twisted Treeline'),
       (600, '3v3 Blind Pick games'),
       (610, '3v3 Ranked Flex games'),
       (700, 'Clash games'),
       (800, 'Co-op vs. AI Intermediate Bot games'),
       (810, 'Co-op vs. AI Intro Bot games'),
       (820, 'Co-op vs. AI Beginner Bot games'),
       (830, 'Co-op vs. AI Intro Bot games'),
       (840, 'Co-op vs. AI Beginner Bot games'),
       (850, 'Co-op vs. AI Intermediate Bot games'),
       (900, 'ARURF games'),
       (910, 'Ascension games'),
       (920, 'Legend of the Poro King games'),
       (940, 'Nexus Siege games'),
       (950, 'Doom Bots Voting games'),
       (960, 'Doom Bots Standard games'),
       (980, 'Star Guardian Invasion: Normal games'),
       (990, 'Star Guardian Invasion: Onslaught games'),
       (1000, 'PROJECT: Hunters games'),
       (1010, 'Snow ARURF games'),
       (1020, 'One for All games'),
       (1030, 'Odyssey Extraction: Intro games'),
       (1040, 'Odyssey Extraction: Cadet games'),
       (1050, 'Odyssey Extraction: Crewmember games'),
       (1060, 'Odyssey Extraction: Captain games'),
       (1070, 'Odyssey Extraction: Onslaught games'),
       (1090, 'Teamfight Tactics games'),
       (1100, 'Ranked Teamfight Tactics games');

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
VALUES ('TOP'),
       ('JUNGLE'),
       ('MID'),
       ('MIDDLE'),
       ('BOT'),
       ('BOTTOM');

INSERT INTO Role(name)
VALUES ('DUO'),
       ('NONE'),
       ('SOLO'),
       ('DUO_CARRY'),
       ('DUO_SUPPORT');

INSERT INTO region(shortName)
VALUES ('NA'),
       ('EUW'),
       ('KR'),
       ('EUN'),
       ('JP'),
       ('LAN'),
       ('LAS'),
       ('OCE'),
       ('RU'),
       ('TR'),
       ('BR');

INSERT INTO Map(name, notes)
VALUES ('Summoner''s Rift', 'Original Summer variant'),
       ('Summoner''s Rift', 'Original Autumn variant'),
       ('The Proving Grounds', 'Tutorial map'),
       ('Twisted Treeline', 'Original version'),
       ('The Crystal Scar', 'Dominion map'),
       ('Twisted Treeline', 'Current version'),
       ('Summoner''s Rift', 'Current version'),
       ('Howling Abyss', 'ARAM map'),
       ('Butcher''s Bridge', 'ARAM map'),
       ('Cosmic Ruins', 'Dark Star: Singularity map'),
       ('Valoran City Park', 'Star Guardian Invasion map'),
       ('Substructure 43', 'PROJECT: Hunters map'),
       ('Crash Site', 'Odyssey: Extraction map'),
       ('Nexus Blitz', 'Nexus Blitz map');

INSERT INTO Tier(shortName)
VALUES ('IRON'),
       ('BRONZE'),
       ('SILVER'),
       ('GOLD'),
       ('PLATINUM'),
       ('DIAMOND'),
       ('MASTER'),
       ('GRANDMASTER'),
       ('UNRANKED'),
       ('CHALLENGER');