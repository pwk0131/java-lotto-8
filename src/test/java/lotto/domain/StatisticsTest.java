package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Statistics 일급 컬렉션 테스트")
class StatisticsTest {

    @Test
    @DisplayName("총 당첨금을 정확히 계산한다")
    void calculateTotalPrize_test() {
        // 5등 1개 (5,000원)
        // 3등 1개 (1,500,000원)
        Map<Rank, Integer> results = new EnumMap<>(Rank.class);
        results.put(Rank.FIRST, 0);
        results.put(Rank.SECOND, 0);
        results.put(Rank.THIRD, 1);
        results.put(Rank.FOURTH, 0);
        results.put(Rank.FIFTH, 1);
        results.put(Rank.MISS, 5);

        Statistics statistics = new Statistics(results);

        long totalPrize = statistics.calculateTotalPrize();

        // 1,500,000 + 5,000 = 1,505,000
        assertThat(totalPrize).isEqualTo(1_505_000L);
    }

    @Test
    @DisplayName("특정 등수의 개수를 정확히 반환한다")
    void getCount_test() {
        Map<Rank, Integer> results = new EnumMap<>(Rank.class);
        results.put(Rank.FIFTH, 1);
        results.put(Rank.FIRST, 0);

        Statistics statistics = new Statistics(results);

        assertThat(statistics.getCount(Rank.FIFTH)).isEqualTo(1);
        assertThat(statistics.getCount(Rank.FIRST)).isEqualTo(0);
        assertThat(statistics.getCount(Rank.SECOND)).isEqualTo(0);
    }
}