package ca.softwarespace.qiyanna.dataaggregator.RiotModels;

/**
 * Author: Steve Mbiele
 * Date: 5/26/2019
 */
public class Mastery {

    private int masterId;
    private int rank;

    public Mastery() {
    }

    public Mastery(int masterId, int rank) {
        this.masterId = masterId;
        this.rank = rank;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
