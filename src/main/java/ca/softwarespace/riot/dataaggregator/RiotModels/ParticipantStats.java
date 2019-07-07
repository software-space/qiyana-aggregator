package ca.softwarespace.riot.dataaggregator.RiotModels;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "ParticipantStats")
public class ParticipantStats {

    @Id
    @Column(name = "id")
    @JsonIgnore
    @GeneratedValue(generator = "participantStats_generator")
    @SequenceGenerator(
            name = "participantStats_generator",
            sequenceName = "participantStats_sequence",
            initialValue = 1000
    )
    private int id;

    @Column
    private boolean firstBloodAssist;

    @Column
    private long visionScore;

    @Column
    private long magicDamageDealtToChampions;

    @Column
    private long damageDealtToObjectives;

    @Column
    private int totalTimeCrowdControlDealt;

    @Column
    private int longestTimeSpentLiving;

    @Column
    private int perk1Var1;

    @Column
    private int perk1Var2;

    @Column
    private int perk1Var3;

    @Column
    private int tripleKills;

    @Column
    private int perk3Var3;

    @Column
    private int nodeNeutralizeAssist;

    @Column
    private int perk3Var2;

    @Column
    private int playerScore9;

    @Column
    private int kills;

    @Column
    private int playerScore1;

    @Column
    private int playerScore0;

    @Column
    private int playerScore3;

    @Column
    private int playerScore2;

    @Column
    private int playerScore5;

    @Column
    private int playerScore4;

    @Column
    private int playerScore7;

    @Column
    private int playerScore6;

    @Column
    private int perk5Var1;

    @Column
    private int perk5Var3;

    @Column
    private int perk5Var2;

    @Column
    private int totalScoreRank;

    @Column
    private int neutralMinionsKilled;

    @Column
    private long damageDealtToTurrets;

    @Column
    private long physicalDamageDealtToChampions;

    @Column
    private int nodeCapture;

    @Column
    private int largestMultiKill;

    @Column
    private int perk2Var2;

    @Column
    private int totalunitsHealed;

    @Column
    private int perk2Var3;

    @Column
    private int perk2Var1;

    @Column
    private int perk4Var1;

    @Column
    private int perk4Var2;

    @Column
    private int perk4Var3;

    @Column
    private int wardsKilled;

    @Column
    private int largestCriticalStrike;

    @Column
    private int largestKillingSpree;

    @Column
    private int quadraKills;

    @Column
    private int teamObjective;

    @Column
    private long magicDamageDealt;

    @Column
    private int item2;

    @Column
    private int item3;

    @Column
    private int item0;

    @Column
    private int neutralMinionsKilledTeamJungle;

    @Column
    private int item6;

    @Column
    private int item4;

    @Column
    private int item5;

    @Column
    private int perk1;

    @Column
    private int perk0;

    @Column
    private int perk3;

    @Column
    private int perk2;

    @Column
    private int perk5;

    @Column
    private int perk4;

    @Column
    private int perk3Var1;

    @Column
    private long damageSelfMitigated;

    @Column
    private long magicalDamageTaken;

    @Column
    private long trueDamageTaken;

    @Column
    private boolean firstInhibitorKill;

    @Column
    private int nodeNeutralize;

    @Column
    private int assists;

    @Column
    private int combatPlayerScore;

    @Column
    private int perkPrimaryStyle;

    @Column
    private int goldSpent;

    @Column
    private long trueDamageDealt;

    @Column
    private int participantId;

    @Column
    private long totalDamageTaken;

    @Column
    private long physicalDamageDealt;

    @Column
    private int sightWardsBoughtInGame;

    @Column
    private long totalDamageDealtToChampions;

    @Column
    private long physicalDamageTaken;

    @Column
    private int totalPlayerScore;

    @Column
    private boolean win;

    @Column
    private int objectivePlayerScore;

    @Column
    private long totalDamageDealt;

    @Column
    private int item1;

    @Column
    private int neutralMinionsKilledEnemyJungle;

    @Column
    private int deaths;

    @Column
    private int wardsPlaced;

    @Column
    private int perkSubStyle;

    @Column
    private int turretKills;

    @Column
    private boolean firstBloodKill;

    @Column
    private long trueDamageDealtToChampions;

    @Column
    private int goldEarned;

    @Column
    private int killingSprees;

    @Column
    private int unrealKills;

    @Column
    private int altarsCaptured;

    @Column
    private boolean firstTowerAssist;

    @Column
    private boolean firstTowerKill;

    @Column
    private int champLevel;

    @Column
    private int doubleKills;

    @Column
    private int nodeCaptureAssist;

    @Column
    private int inhibitorKills;

    @Column
    private boolean firstInhibitorAssist;

    @Column
    private int perk0Var1;

    @Column
    private int perk0Var2;

    @Column
    private int perk0Var3;

    @Column
    private int visionWardsBoughtInGame;

    @Column
    private int altarsNeutralized;

    @Column
    private int pentaKills;

    @Column
    private long totalHeal;

    @Column
    private int totalMinionsKilled;

    @Column
    private long timeCCingOthers;

    public ParticipantStats() {
    }

    public ParticipantStats(boolean firstBloodAssist, long visionScore, long magicDamageDealtToChampions,
                            long damageDealtToObjectives, int totalTimeCrowdControlDealt, int longestTimeSpentLiving,
                            int perk1Var1, int perk1Var2, int perk1Var3, int tripleKills, int perk3Var3,
                            int nodeNeutralizeAssist, int perk3Var2, int playerScore9, int kills, int playerScore1,
                            int playerScore0, int playerScore3, int playerScore2, int playerScore5, int playerScore4,
                            int playerScore7, int playerScore6, int perk5Var1, int perk5Var3, int perk5Var2,
                            int totalScoreRank, int neutralMinionsKilled, long damageDealtToTurrets,
                            long physicalDamageDealtToChampions, int nodeCapture, int largestMultiKill,
                            int perk2Var2, int totalunitsHealed, int perk2Var3, int perk2Var1, int perk4Var1,
                            int perk4Var2, int perk4Var3, int wardsKilled, int largestCriticalStrike,
                            int largestKillingSpree, int quadraKills, int teamObjective, long magicDamageDealt,
                            int item2, int item3, int item0, int neutralMinionsKilledTeamJungle, int item6, int item4,
                            int item5, int perk1, int perk0, int perk3, int perk2, int perk5, int perk4, int perk3Var1,
                            long damageSelfMitigated, long magicalDamageTaken, long trueDamageTaken,
                            boolean firstInhibitorKill, int nodeNeutralize, int assists, int combatPlayerScore,
                            int perkPrimaryStyle, int goldSpent, long trueDamageDealt, int participantId,
                            long totalDamageTaken, long physicalDamageDealt, int sightWardsBoughtInGame,
                            long totalDamageDealtToChampions, long physicalDamageTaken, int totalPlayerScore,
                            boolean win, int objectivePlayerScore, long totalDamageDealt, int item1,
                            int neutralMinionsKilledEnemyJungle, int deaths, int wardsPlaced, int perkSubStyle,
                            int turretKills, boolean firstBloodKill, long trueDamageDealtToChampions, int goldEarned,
                            int killingSprees, int unrealKills, int altarsCaptured, boolean firstTowerAssist,
                            boolean firstTowerKill, int champLevel, int doubleKills, int nodeCaptureAssist,
                            int inhibitorKills, boolean firstInhibitorAssist, int perk0Var1, int perk0Var2,
                            int perk0Var3, int visionWardsBoughtInGame, int altarsNeutralized, int pentaKills,
                            long totalHeal, int totalMinionsKilled, long timeCCingOthers) {
        this.firstBloodAssist = firstBloodAssist;
        this.visionScore = visionScore;
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
        this.damageDealtToObjectives = damageDealtToObjectives;
        this.totalTimeCrowdControlDealt = totalTimeCrowdControlDealt;
        this.longestTimeSpentLiving = longestTimeSpentLiving;
        this.perk1Var1 = perk1Var1;
        this.perk1Var2 = perk1Var2;
        this.perk1Var3 = perk1Var3;
        this.tripleKills = tripleKills;
        this.perk3Var3 = perk3Var3;
        this.nodeNeutralizeAssist = nodeNeutralizeAssist;
        this.perk3Var2 = perk3Var2;
        this.playerScore9 = playerScore9;
        this.kills = kills;
        this.playerScore1 = playerScore1;
        this.playerScore0 = playerScore0;
        this.playerScore3 = playerScore3;
        this.playerScore2 = playerScore2;
        this.playerScore5 = playerScore5;
        this.playerScore4 = playerScore4;
        this.playerScore7 = playerScore7;
        this.playerScore6 = playerScore6;
        this.perk5Var1 = perk5Var1;
        this.perk5Var3 = perk5Var3;
        this.perk5Var2 = perk5Var2;
        this.totalScoreRank = totalScoreRank;
        this.neutralMinionsKilled = neutralMinionsKilled;
        this.damageDealtToTurrets = damageDealtToTurrets;
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
        this.nodeCapture = nodeCapture;
        this.largestMultiKill = largestMultiKill;
        this.perk2Var2 = perk2Var2;
        this.totalunitsHealed = totalunitsHealed;
        this.perk2Var3 = perk2Var3;
        this.perk2Var1 = perk2Var1;
        this.perk4Var1 = perk4Var1;
        this.perk4Var2 = perk4Var2;
        this.perk4Var3 = perk4Var3;
        this.wardsKilled = wardsKilled;
        this.largestCriticalStrike = largestCriticalStrike;
        this.largestKillingSpree = largestKillingSpree;
        this.quadraKills = quadraKills;
        this.teamObjective = teamObjective;
        this.magicDamageDealt = magicDamageDealt;
        this.item2 = item2;
        this.item3 = item3;
        this.item0 = item0;
        this.neutralMinionsKilledTeamJungle = neutralMinionsKilledTeamJungle;
        this.item6 = item6;
        this.item4 = item4;
        this.item5 = item5;
        this.perk1 = perk1;
        this.perk0 = perk0;
        this.perk3 = perk3;
        this.perk2 = perk2;
        this.perk5 = perk5;
        this.perk4 = perk4;
        this.perk3Var1 = perk3Var1;
        this.damageSelfMitigated = damageSelfMitigated;
        this.magicalDamageTaken = magicalDamageTaken;
        this.trueDamageTaken = trueDamageTaken;
        this.firstInhibitorKill = firstInhibitorKill;
        this.nodeNeutralize = nodeNeutralize;
        this.assists = assists;
        this.combatPlayerScore = combatPlayerScore;
        this.perkPrimaryStyle = perkPrimaryStyle;
        this.goldSpent = goldSpent;
        this.trueDamageDealt = trueDamageDealt;
        this.participantId = participantId;
        this.totalDamageTaken = totalDamageTaken;
        this.physicalDamageDealt = physicalDamageDealt;
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
        this.physicalDamageTaken = physicalDamageTaken;
        this.totalPlayerScore = totalPlayerScore;
        this.win = win;
        this.objectivePlayerScore = objectivePlayerScore;
        this.totalDamageDealt = totalDamageDealt;
        this.item1 = item1;
        this.neutralMinionsKilledEnemyJungle = neutralMinionsKilledEnemyJungle;
        this.deaths = deaths;
        this.wardsPlaced = wardsPlaced;
        this.perkSubStyle = perkSubStyle;
        this.turretKills = turretKills;
        this.firstBloodKill = firstBloodKill;
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
        this.goldEarned = goldEarned;
        this.killingSprees = killingSprees;
        this.unrealKills = unrealKills;
        this.altarsCaptured = altarsCaptured;
        this.firstTowerAssist = firstTowerAssist;
        this.firstTowerKill = firstTowerKill;
        this.champLevel = champLevel;
        this.doubleKills = doubleKills;
        this.nodeCaptureAssist = nodeCaptureAssist;
        this.inhibitorKills = inhibitorKills;
        this.firstInhibitorAssist = firstInhibitorAssist;
        this.perk0Var1 = perk0Var1;
        this.perk0Var2 = perk0Var2;
        this.perk0Var3 = perk0Var3;
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
        this.altarsNeutralized = altarsNeutralized;
        this.pentaKills = pentaKills;
        this.totalHeal = totalHeal;
        this.totalMinionsKilled = totalMinionsKilled;
        this.timeCCingOthers = timeCCingOthers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFirstBloodAssist() {
        return firstBloodAssist;
    }

    public void setFirstBloodAssist(boolean firstBloodAssist) {
        this.firstBloodAssist = firstBloodAssist;
    }

    public long getVisionScore() {
        return visionScore;
    }

    public void setVisionScore(long visionScore) {
        this.visionScore = visionScore;
    }

    public long getMagicDamageDealtToChampions() {
        return magicDamageDealtToChampions;
    }

    public void setMagicDamageDealtToChampions(long magicDamageDealtToChampions) {
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
    }

    public long getDamageDealtToObjectives() {
        return damageDealtToObjectives;
    }

    public void setDamageDealtToObjectives(long damageDealtToObjectives) {
        this.damageDealtToObjectives = damageDealtToObjectives;
    }

    public int getTotalTimeCrowdControlDealt() {
        return totalTimeCrowdControlDealt;
    }

    public void setTotalTimeCrowdControlDealt(int totalTimeCrowdControlDealt) {
        this.totalTimeCrowdControlDealt = totalTimeCrowdControlDealt;
    }

    public int getLongestTimeSpentLiving() {
        return longestTimeSpentLiving;
    }

    public void setLongestTimeSpentLiving(int longestTimeSpentLiving) {
        this.longestTimeSpentLiving = longestTimeSpentLiving;
    }

    public int getPerk1Var1() {
        return perk1Var1;
    }

    public void setPerk1Var1(int perk1Var1) {
        this.perk1Var1 = perk1Var1;
    }

    public int getPerk1Var2() {
        return perk1Var2;
    }

    public void setPerk1Var2(int perk1Var2) {
        this.perk1Var2 = perk1Var2;
    }

    public int getPerk1Var3() {
        return perk1Var3;
    }

    public void setPerk1Var3(int perk1Var3) {
        this.perk1Var3 = perk1Var3;
    }

    public int getTripleKills() {
        return tripleKills;
    }

    public void setTripleKills(int tripleKills) {
        this.tripleKills = tripleKills;
    }

    public int getPerk3Var3() {
        return perk3Var3;
    }

    public void setPerk3Var3(int perk3Var3) {
        this.perk3Var3 = perk3Var3;
    }

    public int getNodeNeutralizeAssist() {
        return nodeNeutralizeAssist;
    }

    public void setNodeNeutralizeAssist(int nodeNeutralizeAssist) {
        this.nodeNeutralizeAssist = nodeNeutralizeAssist;
    }

    public int getPerk3Var2() {
        return perk3Var2;
    }

    public void setPerk3Var2(int perk3Var2) {
        this.perk3Var2 = perk3Var2;
    }

    public int getPlayerScore9() {
        return playerScore9;
    }

    public void setPlayerScore9(int playerScore9) {
        this.playerScore9 = playerScore9;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getPlayerScore1() {
        return playerScore1;
    }

    public void setPlayerScore1(int playerScore1) {
        this.playerScore1 = playerScore1;
    }

    public int getPlayerScore0() {
        return playerScore0;
    }

    public void setPlayerScore0(int playerScore0) {
        this.playerScore0 = playerScore0;
    }

    public int getPlayerScore3() {
        return playerScore3;
    }

    public void setPlayerScore3(int playerScore3) {
        this.playerScore3 = playerScore3;
    }

    public int getPlayerScore2() {
        return playerScore2;
    }

    public void setPlayerScore2(int playerScore2) {
        this.playerScore2 = playerScore2;
    }

    public int getPlayerScore5() {
        return playerScore5;
    }

    public void setPlayerScore5(int playerScore5) {
        this.playerScore5 = playerScore5;
    }

    public int getPlayerScore4() {
        return playerScore4;
    }

    public void setPlayerScore4(int playerScore4) {
        this.playerScore4 = playerScore4;
    }

    public int getPlayerScore7() {
        return playerScore7;
    }

    public void setPlayerScore7(int playerScore7) {
        this.playerScore7 = playerScore7;
    }

    public int getPlayerScore6() {
        return playerScore6;
    }

    public void setPlayerScore6(int playerScore6) {
        this.playerScore6 = playerScore6;
    }

    public int getPerk5Var1() {
        return perk5Var1;
    }

    public void setPerk5Var1(int perk5Var1) {
        this.perk5Var1 = perk5Var1;
    }

    public int getPerk5Var3() {
        return perk5Var3;
    }

    public void setPerk5Var3(int perk5Var3) {
        this.perk5Var3 = perk5Var3;
    }

    public int getPerk5Var2() {
        return perk5Var2;
    }

    public void setPerk5Var2(int perk5Var2) {
        this.perk5Var2 = perk5Var2;
    }

    public int getTotalScoreRank() {
        return totalScoreRank;
    }

    public void setTotalScoreRank(int totalScoreRank) {
        this.totalScoreRank = totalScoreRank;
    }

    public int getNeutralMinionsKilled() {
        return neutralMinionsKilled;
    }

    public void setNeutralMinionsKilled(int neutralMinionsKilled) {
        this.neutralMinionsKilled = neutralMinionsKilled;
    }

    public long getDamageDealtToTurrets() {
        return damageDealtToTurrets;
    }

    public void setDamageDealtToTurrets(long damageDealtToTurrets) {
        this.damageDealtToTurrets = damageDealtToTurrets;
    }

    public long getPhysicalDamageDealtToChampions() {
        return physicalDamageDealtToChampions;
    }

    public void setPhysicalDamageDealtToChampions(long physicalDamageDealtToChampions) {
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
    }

    public int getNodeCapture() {
        return nodeCapture;
    }

    public void setNodeCapture(int nodeCapture) {
        this.nodeCapture = nodeCapture;
    }

    public int getLargestMultiKill() {
        return largestMultiKill;
    }

    public void setLargestMultiKill(int largestMultiKill) {
        this.largestMultiKill = largestMultiKill;
    }

    public int getPerk2Var2() {
        return perk2Var2;
    }

    public void setPerk2Var2(int perk2Var2) {
        this.perk2Var2 = perk2Var2;
    }

    public int getTotalunitsHealed() {
        return totalunitsHealed;
    }

    public void setTotalunitsHealed(int totalunitsHealed) {
        this.totalunitsHealed = totalunitsHealed;
    }

    public int getPerk2Var3() {
        return perk2Var3;
    }

    public void setPerk2Var3(int perk2Var3) {
        this.perk2Var3 = perk2Var3;
    }

    public int getPerk2Var1() {
        return perk2Var1;
    }

    public void setPerk2Var1(int perk2Var1) {
        this.perk2Var1 = perk2Var1;
    }

    public int getPerk4Var1() {
        return perk4Var1;
    }

    public void setPerk4Var1(int perk4Var1) {
        this.perk4Var1 = perk4Var1;
    }

    public int getPerk4Var2() {
        return perk4Var2;
    }

    public void setPerk4Var2(int perk4Var2) {
        this.perk4Var2 = perk4Var2;
    }

    public int getPerk4Var3() {
        return perk4Var3;
    }

    public void setPerk4Var3(int perk4Var3) {
        this.perk4Var3 = perk4Var3;
    }

    public int getWardsKilled() {
        return wardsKilled;
    }

    public void setWardsKilled(int wardsKilled) {
        this.wardsKilled = wardsKilled;
    }

    public int getLargestCriticalStrike() {
        return largestCriticalStrike;
    }

    public void setLargestCriticalStrike(int largestCriticalStrike) {
        this.largestCriticalStrike = largestCriticalStrike;
    }

    public int getLargestKillingSpree() {
        return largestKillingSpree;
    }

    public void setLargestKillingSpree(int largestKillingSpree) {
        this.largestKillingSpree = largestKillingSpree;
    }

    public int getQuadraKills() {
        return quadraKills;
    }

    public void setQuadraKills(int quadraKills) {
        this.quadraKills = quadraKills;
    }

    public int getTeamObjective() {
        return teamObjective;
    }

    public void setTeamObjective(int teamObjective) {
        this.teamObjective = teamObjective;
    }

    public long getMagicDamageDealt() {
        return magicDamageDealt;
    }

    public void setMagicDamageDealt(long magicDamageDealt) {
        this.magicDamageDealt = magicDamageDealt;
    }

    public int getItem2() {
        return item2;
    }

    public void setItem2(int item2) {
        this.item2 = item2;
    }

    public int getItem3() {
        return item3;
    }

    public void setItem3(int item3) {
        this.item3 = item3;
    }

    public int getItem0() {
        return item0;
    }

    public void setItem0(int item0) {
        this.item0 = item0;
    }

    public int getNeutralMinionsKilledTeamJungle() {
        return neutralMinionsKilledTeamJungle;
    }

    public void setNeutralMinionsKilledTeamJungle(int neutralMinionsKilledTeamJungle) {
        this.neutralMinionsKilledTeamJungle = neutralMinionsKilledTeamJungle;
    }

    public int getItem6() {
        return item6;
    }

    public void setItem6(int item6) {
        this.item6 = item6;
    }

    public int getItem4() {
        return item4;
    }

    public void setItem4(int item4) {
        this.item4 = item4;
    }

    public int getItem5() {
        return item5;
    }

    public void setItem5(int item5) {
        this.item5 = item5;
    }

    public int getPerk1() {
        return perk1;
    }

    public void setPerk1(int perk1) {
        this.perk1 = perk1;
    }

    public int getPerk0() {
        return perk0;
    }

    public void setPerk0(int perk0) {
        this.perk0 = perk0;
    }

    public int getPerk3() {
        return perk3;
    }

    public void setPerk3(int perk3) {
        this.perk3 = perk3;
    }

    public int getPerk2() {
        return perk2;
    }

    public void setPerk2(int perk2) {
        this.perk2 = perk2;
    }

    public int getPerk5() {
        return perk5;
    }

    public void setPerk5(int perk5) {
        this.perk5 = perk5;
    }

    public int getPerk4() {
        return perk4;
    }

    public void setPerk4(int perk4) {
        this.perk4 = perk4;
    }

    public int getPerk3Var1() {
        return perk3Var1;
    }

    public void setPerk3Var1(int perk3Var1) {
        this.perk3Var1 = perk3Var1;
    }

    public long getDamageSelfMitigated() {
        return damageSelfMitigated;
    }

    public void setDamageSelfMitigated(long damageSelfMitigated) {
        this.damageSelfMitigated = damageSelfMitigated;
    }

    public long getMagicalDamageTaken() {
        return magicalDamageTaken;
    }

    public void setMagicalDamageTaken(long magicalDamageTaken) {
        this.magicalDamageTaken = magicalDamageTaken;
    }

    public long getTrueDamageTaken() {
        return trueDamageTaken;
    }

    public void setTrueDamageTaken(long trueDamageTaken) {
        this.trueDamageTaken = trueDamageTaken;
    }

    public boolean isFirstInhibitorKill() {
        return firstInhibitorKill;
    }

    public void setFirstInhibitorKill(boolean firstInhibitorKill) {
        this.firstInhibitorKill = firstInhibitorKill;
    }

    public int getNodeNeutralize() {
        return nodeNeutralize;
    }

    public void setNodeNeutralize(int nodeNeutralize) {
        this.nodeNeutralize = nodeNeutralize;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getCombatPlayerScore() {
        return combatPlayerScore;
    }

    public void setCombatPlayerScore(int combatPlayerScore) {
        this.combatPlayerScore = combatPlayerScore;
    }

    public int getPerkPrimaryStyle() {
        return perkPrimaryStyle;
    }

    public void setPerkPrimaryStyle(int perkPrimaryStyle) {
        this.perkPrimaryStyle = perkPrimaryStyle;
    }

    public int getGoldSpent() {
        return goldSpent;
    }

    public void setGoldSpent(int goldSpent) {
        this.goldSpent = goldSpent;
    }

    public long getTrueDamageDealt() {
        return trueDamageDealt;
    }

    public void setTrueDamageDealt(long trueDamageDealt) {
        this.trueDamageDealt = trueDamageDealt;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public long getTotalDamageTaken() {
        return totalDamageTaken;
    }

    public void setTotalDamageTaken(long totalDamageTaken) {
        this.totalDamageTaken = totalDamageTaken;
    }

    public long getPhysicalDamageDealt() {
        return physicalDamageDealt;
    }

    public void setPhysicalDamageDealt(long physicalDamageDealt) {
        this.physicalDamageDealt = physicalDamageDealt;
    }

    public int getSightWardsBoughtInGame() {
        return sightWardsBoughtInGame;
    }

    public void setSightWardsBoughtInGame(int sightWardsBoughtInGame) {
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
    }

    public long getTotalDamageDealtToChampions() {
        return totalDamageDealtToChampions;
    }

    public void setTotalDamageDealtToChampions(long totalDamageDealtToChampions) {
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
    }

    public long getPhysicalDamageTaken() {
        return physicalDamageTaken;
    }

    public void setPhysicalDamageTaken(long physicalDamageTaken) {
        this.physicalDamageTaken = physicalDamageTaken;
    }

    public int getTotalPlayerScore() {
        return totalPlayerScore;
    }

    public void setTotalPlayerScore(int totalPlayerScore) {
        this.totalPlayerScore = totalPlayerScore;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getObjectivePlayerScore() {
        return objectivePlayerScore;
    }

    public void setObjectivePlayerScore(int objectivePlayerScore) {
        this.objectivePlayerScore = objectivePlayerScore;
    }

    public long getTotalDamageDealt() {
        return totalDamageDealt;
    }

    public void setTotalDamageDealt(long totalDamageDealt) {
        this.totalDamageDealt = totalDamageDealt;
    }

    public int getItem1() {
        return item1;
    }

    public void setItem1(int item1) {
        this.item1 = item1;
    }

    public int getNeutralMinionsKilledEnemyJungle() {
        return neutralMinionsKilledEnemyJungle;
    }

    public void setNeutralMinionsKilledEnemyJungle(int neutralMinionsKilledEnemyJungle) {
        this.neutralMinionsKilledEnemyJungle = neutralMinionsKilledEnemyJungle;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getWardsPlaced() {
        return wardsPlaced;
    }

    public void setWardsPlaced(int wardsPlaced) {
        this.wardsPlaced = wardsPlaced;
    }

    public int getPerkSubStyle() {
        return perkSubStyle;
    }

    public void setPerkSubStyle(int perkSubStyle) {
        this.perkSubStyle = perkSubStyle;
    }

    public int getTurretKills() {
        return turretKills;
    }

    public void setTurretKills(int turretKills) {
        this.turretKills = turretKills;
    }

    public boolean isFirstBloodKill() {
        return firstBloodKill;
    }

    public void setFirstBloodKill(boolean firstBloodKill) {
        this.firstBloodKill = firstBloodKill;
    }

    public long getTrueDamageDealtToChampions() {
        return trueDamageDealtToChampions;
    }

    public void setTrueDamageDealtToChampions(long trueDamageDealtToChampions) {
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
    }

    public int getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(int goldEarned) {
        this.goldEarned = goldEarned;
    }

    public int getKillingSprees() {
        return killingSprees;
    }

    public void setKillingSprees(int killingSprees) {
        this.killingSprees = killingSprees;
    }

    public int getUnrealKills() {
        return unrealKills;
    }

    public void setUnrealKills(int unrealKills) {
        this.unrealKills = unrealKills;
    }

    public int getAltarsCaptured() {
        return altarsCaptured;
    }

    public void setAltarsCaptured(int altarsCaptured) {
        this.altarsCaptured = altarsCaptured;
    }

    public boolean isFirstTowerAssist() {
        return firstTowerAssist;
    }

    public void setFirstTowerAssist(boolean firstTowerAssist) {
        this.firstTowerAssist = firstTowerAssist;
    }

    public boolean isFirstTowerKill() {
        return firstTowerKill;
    }

    public void setFirstTowerKill(boolean firstTowerKill) {
        this.firstTowerKill = firstTowerKill;
    }

    public int getChampLevel() {
        return champLevel;
    }

    public void setChampLevel(int champLevel) {
        this.champLevel = champLevel;
    }

    public int getDoubleKills() {
        return doubleKills;
    }

    public void setDoubleKills(int doubleKills) {
        this.doubleKills = doubleKills;
    }

    public int getNodeCaptureAssist() {
        return nodeCaptureAssist;
    }

    public void setNodeCaptureAssist(int nodeCaptureAssist) {
        this.nodeCaptureAssist = nodeCaptureAssist;
    }

    public int getInhibitorKills() {
        return inhibitorKills;
    }

    public void setInhibitorKills(int inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    public boolean isFirstInhibitorAssist() {
        return firstInhibitorAssist;
    }

    public void setFirstInhibitorAssist(boolean firstInhibitorAssist) {
        this.firstInhibitorAssist = firstInhibitorAssist;
    }

    public int getPerk0Var1() {
        return perk0Var1;
    }

    public void setPerk0Var1(int perk0Var1) {
        this.perk0Var1 = perk0Var1;
    }

    public int getPerk0Var2() {
        return perk0Var2;
    }

    public void setPerk0Var2(int perk0Var2) {
        this.perk0Var2 = perk0Var2;
    }

    public int getPerk0Var3() {
        return perk0Var3;
    }

    public void setPerk0Var3(int perk0Var3) {
        this.perk0Var3 = perk0Var3;
    }

    public int getVisionWardsBoughtInGame() {
        return visionWardsBoughtInGame;
    }

    public void setVisionWardsBoughtInGame(int visionWardsBoughtInGame) {
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
    }

    public int getAltarsNeutralized() {
        return altarsNeutralized;
    }

    public void setAltarsNeutralized(int altarsNeutralized) {
        this.altarsNeutralized = altarsNeutralized;
    }

    public int getPentaKills() {
        return pentaKills;
    }

    public void setPentaKills(int pentaKills) {
        this.pentaKills = pentaKills;
    }

    public long getTotalHeal() {
        return totalHeal;
    }

    public void setTotalHeal(long totalHeal) {
        this.totalHeal = totalHeal;
    }

    public int getTotalMinionsKilled() {
        return totalMinionsKilled;
    }

    public void setTotalMinionsKilled(int totalMinionsKilled) {
        this.totalMinionsKilled = totalMinionsKilled;
    }

    public long getTimeCCingOthers() {
        return timeCCingOthers;
    }

    public void setTimeCCingOthers(long timeCCingOthers) {
        this.timeCCingOthers = timeCCingOthers;
    }
}