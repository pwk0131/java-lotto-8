package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Statistics calculateStatistics(WinningLotto winningLotto) {
        Map<Rank, Integer> result = createEmptyStatisticsMap();

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.determineRank(lotto); // 당첨 판별 로직 위임
            result.put(rank, result.get(rank) + 1);
        }

        return new Statistics(Collections.unmodifiableMap(result));
    }

    private Map<Rank, Integer> createEmptyStatisticsMap() {
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
        return result;
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