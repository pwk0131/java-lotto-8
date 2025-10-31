package lotto.domain;

import java.util.List;
import lotto.Lotto;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    // OutputView에서 출력을 담당할 toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Lotto lotto : lottos) {
            sb.append(lotto.toString()).append("\n");
        }
        return sb.toString().trim(); // 마지막 줄바꿈 제거
    }
}