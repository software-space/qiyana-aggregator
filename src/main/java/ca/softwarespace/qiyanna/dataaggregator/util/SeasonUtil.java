package ca.softwarespace.qiyanna.dataaggregator.util;

public class SeasonUtil {

  private SeasonUtil() {
  }

  public static int getSeasonNumberByName(String name) {
    switch (name.toUpperCase()) {
      case "PRESEASON_3":
        return 0;
      case "SEASON_3":
        return 1;
      case "PRESEASON_2014":
        return 2;
      case "SEASON_2014":
        return 3;
      case "PRESEASON_2015":
        return 4;
      case "SEASON_2015":
        return 5;
      case "PRESEASON_2016":
        return 6;
      case "SEASON_2016":
        return 7;
      case "PRESEASON_2017":
        return 8;
      case "SEASON_2017":
        return 9;
      case "PRESEASON_2018":
        return 10;
      case "SEASON_2018":
        return 11;
      case "PRESEASON_2019":
        return 12;
      case "SEASON_2019":
        return 13;
      default:
        throw new IllegalArgumentException("No season with name found : " + name);
    }
  }
}
