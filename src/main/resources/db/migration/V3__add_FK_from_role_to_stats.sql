ALTER TABLE Summoner_Stats ADD column roleId integer;
ALTER TABLE Summoner_Stats ADD CONSTRAINT fk_role_Summoner_Stats FOREIGN KEY (roleId) REFERENCES Role(roleId);
ALTER TABLE Champion_Stats ADD column roleId integer;
ALTER TABLE Champion_Stats ADD CONSTRAINT fk_role_Champion_Stats FOREIGN KEY (roleId) REFERENCES Role(roleId);
ALTER TABLE summoner ADD column summonerId varchar (63);