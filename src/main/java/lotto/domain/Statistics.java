package lotto.domain;

import java.util.Map;

public class Statistics {
    private final Map<Rank, Integer> results;

    public Statistics(Map<Rank, Integer> results) {
        this.results = results;
    }

    public long calculateTotalPrize() {
        return results.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    public int getCount(Rank rank) {
        return results.getOrDefault(rank, 0);
    }
}