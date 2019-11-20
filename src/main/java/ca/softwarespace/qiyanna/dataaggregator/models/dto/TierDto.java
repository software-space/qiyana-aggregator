package ca.softwarespace.qiyanna.dataaggregator.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TierDto {

  private int tierId;
  private String shortName;
}
