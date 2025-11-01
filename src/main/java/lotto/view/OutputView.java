package lotto.view;

import java.text.NumberFormat;
import java.util.Locale;
import lotto.domain.Lottos;
import lotto.dto.RankResultDTO;
import lotto.dto.StatisticsReportDTO;

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

    public void printStatisticsReport(StatisticsReportDTO report) {
        System.out.println("\n당첨 통계");
        System.out.println("---");

        // DTO에서 데이터를 꺼내 단순 출력 (get이 아닌 record의 접근자)
        for (RankResultDTO result : report.results()) {
            System.out.printf("%s (%s원) - %d개\n",
                    result.description(),
                    result.prize(),
                    result.count()
            );
        }

        System.out.printf("총 수익률은 %,.1f%%입니다.\n", report.profitRate());
    }

}
