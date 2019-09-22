package ca.softwarespace.qiyanna.dataaggregator.models.builders;

import ca.softwarespace.qiyanna.dataaggregator.models.dto.Stats;

public class StatsBuilder {

    private Stats stats;

    public StatsBuilder() {
        stats = new Stats();
    }

    public StatsBuilder withWin(boolean isWin) {
        this.stats.setWin(isWin);
        return this;
    }

    public Stats build() {
        return this.stats;
    }
}
