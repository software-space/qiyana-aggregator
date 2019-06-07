package ca.softwarespace.riot.dataaggregator.RiotModels;

import java.util.Map;

/**
 * Author: Steve Mbiele
 * Date: 5/26/2019
 */
public class ParticipantTimeline {

    private String lane;
    private int participantId;
    private String role;
    private Map<String, Double> csDiffPerMinDeltas;
    private Map<String, Double> goldPerMinDeltas;
    private Map<String, Double> creepsPerMinDeltas;
    private Map<String, Double> xpPerMinDeltas;
    private Map<String, Double> damageTakenDiffPerMinDeltas;
    private Map<String, Double> damageTakenPerMinDeltas;

    public ParticipantTimeline() {
    }

    public ParticipantTimeline(String lane, int participantId, String role, Map<String, Double> csDiffPerMinDeltas,
                               Map<String, Double> goldPerMinDeltas, Map<String, Double> creepsPerMinDeltas,
                               Map<String, Double> xpPerMinDeltas, Map<String, Double> damageTakenDiffPerMinDeltas,
                               Map<String, Double> damageTakenPerMinDeltas) {
        this.lane = lane;
        this.participantId = participantId;
        this.role = role;
        this.csDiffPerMinDeltas = csDiffPerMinDeltas;
        this.goldPerMinDeltas = goldPerMinDeltas;
        this.creepsPerMinDeltas = creepsPerMinDeltas;
        this.xpPerMinDeltas = xpPerMinDeltas;
        this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
        this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Map<String, Double> getCsDiffPerMinDeltas() {
        return csDiffPerMinDeltas;
    }

    public void setCsDiffPerMinDeltas(Map<String, Double> csDiffPerMinDeltas) {
        this.csDiffPerMinDeltas = csDiffPerMinDeltas;
    }

    public Map<String, Double> getGoldPerMinDeltas() {
        return goldPerMinDeltas;
    }

    public void setGoldPerMinDeltas(Map<String, Double> goldPerMinDeltas) {
        this.goldPerMinDeltas = goldPerMinDeltas;
    }

    public Map<String, Double> getCreepsPerMinDeltas() {
        return creepsPerMinDeltas;
    }

    public void setCreepsPerMinDeltas(Map<String, Double> creepsPerMinDeltas) {
        this.creepsPerMinDeltas = creepsPerMinDeltas;
    }

    public Map<String, Double> getXpPerMinDeltas() {
        return xpPerMinDeltas;
    }

    public void setXpPerMinDeltas(Map<String, Double> xpPerMinDeltas) {
        this.xpPerMinDeltas = xpPerMinDeltas;
    }

    public Map<String, Double> getDamageTakenDiffPerMinDeltas() {
        return damageTakenDiffPerMinDeltas;
    }

    public void setDamageTakenDiffPerMinDeltas(Map<String, Double> damageTakenDiffPerMinDeltas) {
        this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
    }

    public Map<String, Double> getDamageTakenPerMinDeltas() {
        return damageTakenPerMinDeltas;
    }

    public void setDamageTakenPerMinDeltas(Map<String, Double> damageTakenPerMinDeltas) {
        this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
    }
}
