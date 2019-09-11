package ca.softwarespace.qiyanna.dataaggregator.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.merakianalytics.orianna.types.common.Region;
import java.util.Map;

public enum RegionEnum {
  NA("NA", Region.NORTH_AMERICA),
  EUW("EUW", Region.EUROPE_WEST),
  EUE("EUNE", Region.EUROPE_NORTH_EAST),
  JP("JP", Region.JAPAN),
  KR("KR", Region.KOREA),
  LAN("LAN", Region.LATIN_AMERICA_NORTH),
  LAS("LAS", Region.LATIN_AMERICA_SOUTH),
  OCE("OCE", Region.OCEANIA),
  RU("RU", Region.RUSSIA),
  TR("TR", Region.TURKEY);

  private final String tag;
  private final Region region;
  private static final Map<String, Region> BY_TAG = getByTag();

  RegionEnum(String tag, Region region) {
    this.tag = tag;
    this.region = region;
  }

  public String getTag() {
    return tag;
  }

  public Region getRegion() {
    return region;
  }

  public static Region getRegionByTag(final String tag) {
    return BY_TAG.get(tag.toUpperCase());
  }

  private static Map<String, Region> getByTag() {
    final Builder<String, Region> builder = ImmutableMap.builder();
    for (final RegionEnum regionEnum : values()) {
      builder.put(regionEnum.tag, regionEnum.region);
    }
    return builder.build();
  }
}
