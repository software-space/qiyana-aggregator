package ca.softwarespace.qiyanna.dataaggregator.models;

public class CommunityPatch {

  private String name;
  private long start;
  private int season;

  public CommunityPatch(String name, long start, int season) {
    this.name = name;
    this.start = start;
    this.season = season;
  }

  public CommunityPatch() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getStart() {
    return start;
  }

  public void setStart(long start) {
    this.start = start;
  }

  public int getSeason() {
    return season;
  }

  public void setSeason(int season) {
    this.season = season;
  }
}
