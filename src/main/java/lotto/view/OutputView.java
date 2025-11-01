package lotto.view;

import java.text.NumberFormat;
import java.util.Locale;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.Statistics;

public class OutputView {

    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.KOREA);

    public void printError(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public void printTicketCount(int count) {
        System.out.printf("\n%d개를 구매했습니다.\n", count);
    }

    public void printLottos(Lottos lottos) {
        System.out.println(lottos.toString());
    }

    public void printStatisticsHeader() {
        System.out.println("\n당첨 통계");
        System.out.println("---");
    }

    public void printStatistics(Statistics statistics) {
        printRankResult(statistics, Rank.FIFTH);
        printRankResult(statistics, Rank.FOURTH);
        printRankResult(statistics, Rank.THIRD);
        printRankResult(statistics, Rank.SECOND);
        printRankResult(statistics, Rank.FIRST);
    }

    private void printRankResult(Statistics statistics, Rank rank) {
        String description = formatRankDescription(rank);
        String prize = formatPrize(rank.getPrize());
        int count = statistics.getCount(rank);
        System.out.printf("%s (%s원) - %d개\n", description, prize, count);
    }

    private String formatRankDescription(Rank rank) {
        if (rank.isBonusRank()) {
            return String.format("%d개 일치, 보너스 볼 일치", rank.getMatchCount());
        }
        return String.format("%d개 일치", rank.getMatchCount());
    }

    private String formatPrize(long prize) {
        return NUMBER_FORMAT.format(prize);
    }

    public void printProfitRate(double rate) {
        System.out.printf("총 수익률은 %,.1f%%입니다.\n", rate);
    }

}
