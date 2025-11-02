package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Rank Enum 로직 테스트")
class RankTest {

    @DisplayName("일치 개수와 보너스 여부로 정확한 등수를 반환해야 한다")
    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",  // 6개 일치 (1등)
            "5, true, SECOND",  // 5개 일치 + 보너스 (2등)
            "5, false, THIRD",  // 5개 일치 (3등)
            "4, true, FOURTH",  // 4개 일치 (4등)
            "4, false, FOURTH", // 4개 일치 (4등)
            "3, true, FIFTH",   // 3개 일치 (5등)
            "3, false, FIFTH",  // 3개 일치 (5등)
            "2, true, MISS",    // 2개 일치 (꽝)
            "1, false, MISS",   // 1개 일치 (꽝)
            "0, false, MISS"    // 0개 일치 (꽝)
    })
    void valueOf_ReturnsCorrectRank(int matchCount, boolean hasBonus, Rank expectedRank) {
        Rank rank = Rank.valueOf(matchCount, hasBonus);
        assertThat(rank).isEqualTo(expectedRank);
    }

    @Test
    @DisplayName("각 등수의 상금이 요구사항과 일치해야 한다")
    void rank_HasCorrectPrize() {
        assertThat(Rank.FIRST.calculatePrizeForCount(1)).isEqualTo(2_000_000_000L);
        assertThat(Rank.SECOND.calculatePrizeForCount(1)).isEqualTo(30_000_000L);
        assertThat(Rank.THIRD.calculatePrizeForCount(1)).isEqualTo(1_500_000L);
        assertThat(Rank.FOURTH.calculatePrizeForCount(1)).isEqualTo(50_000L);
        assertThat(Rank.FIFTH.calculatePrizeForCount(1)).isEqualTo(5_000L);
        assertThat(Rank.MISS.calculatePrizeForCount(1)).isEqualTo(0L);
    }
}