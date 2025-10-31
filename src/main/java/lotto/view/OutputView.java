package lotto.view;

import lotto.domain.Lottos;

public class OutputView {

    public void printError(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public void printTicketCount(int count) {
        System.out.printf("\n%d개를 구매했습니다.\n", count);
    }

    public void printLottos(Lottos lottos) {
        System.out.println(lottos.toString());
    }

}
