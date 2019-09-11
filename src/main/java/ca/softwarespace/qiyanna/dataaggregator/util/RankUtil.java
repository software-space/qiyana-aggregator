package ca.softwarespace.qiyanna.dataaggregator.util;

public class RankUtil {

  private RankUtil() {
  }

  public static int getRankIdByTag(String tag) {
    switch (tag.toUpperCase()) {
      case "I":
        return 1;
      case "II":
        return 2;
      case "III":
        return 3;
      case "IV":
        return 4;
      default:
        throw new IllegalArgumentException("No rank with tag found: " + tag);
    }
  }

}
