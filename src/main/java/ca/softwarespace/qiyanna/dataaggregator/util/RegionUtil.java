package ca.softwarespace.qiyanna.dataaggregator.util;

import com.merakianalytics.orianna.types.common.Region;

public class RegionUtil {

  public static Region getRegionByTag(String tag) {
    switch (tag.toUpperCase()) {
      case "NA":
        return Region.NORTH_AMERICA;
      case "EUW":
        return Region.EUROPE_WEST;
      case "EUNE":
        return Region.EUROPE_NORTH_EAST;
      case "JP":
        return Region.JAPAN;
      case "KR":
        return Region.KOREA;
      case "LAN":
        return Region.LATIN_AMERICA_NORTH;
      case "LAS":
        return Region.LATIN_AMERICA_SOUTH;
      case "OCE":
        return Region.OCEANIA;
      case "RU":
        return Region.RUSSIA;
      case "TR":
        return Region.TURKEY;
      default:
        throw new IllegalArgumentException("No region with tag found : " + tag);
    }
  }

}
