package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.dto.RankResultDTO;
import lotto.dto.StatisticsReportDTO;

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

    public StatisticsReportDTO generateReport(PurchaseAmount purchaseAmount) {
        List<RankResultDTO> rankResults = new ArrayList<>();

        // View에 표시할 순서 (5등 -> 1등)
        for (Rank rank : List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)) {
            rankResults.add(createRankResultDTO(rank));
        }

        long totalPrize = calculateTotalPrize();
        double profitRate = purchaseAmount.calculateProfitRate(totalPrize);

        return new StatisticsReportDTO(rankResults, profitRate);
    }

    //DTO의 구성 요소인 RankResultDTO를 생성
    private RankResultDTO createRankResultDTO(Rank rank) {
        String description = rank.getDescription();
        String prize = rank.getFormattedPrize();
        int count = getCount(rank);
        return new RankResultDTO(description, prize, count);
    }
}