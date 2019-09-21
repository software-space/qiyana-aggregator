package ca.softwarespace.qiyanna.dataaggregator.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Participant {
    private int championId;
    private Stats stats;
}
