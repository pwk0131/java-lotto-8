package lotto.domain;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.BiPredicate;

public enum Rank {
    // 1등: 6개 번호 일치 / 2,000,000,000원
    FIRST(6, 2_000_000_000L, (match, bonus) -> match == 6),
    // 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
    SECOND(5, 30_000_000L, (match, bonus) -> match == 5 && bonus),
    // 3등: 5개 번호 일치 / 1,500,000원
    THIRD(5, 1_500_000L, (match, bonus) -> match == 5 && !bonus),
    // 4등: 4개 번호 일치 / 50,000원
    FOURTH(4, 50_000L, (match, bonus) -> match == 4),
    // 5등: 3개 번호 일치 / 5,000원
    FIFTH(3, 5_000L, (match, bonus) -> match == 3),
    MISS(0, 0L, (match, bonus) -> true); // Predicate 체인의 default 역할

    private final int matchCount;
    private final long prize;
    private final BiPredicate<Integer, Boolean> condition;

    Rank(int matchCount, long prize, BiPredicate<Integer, Boolean> condition) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.condition = condition;
    }

    public static Rank valueOf(int matchCount, boolean hasBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.condition.test(matchCount, hasBonus))
                .findFirst()
                .orElse(MISS);
    }

    // Statistics에서 DTO 생성을 위해 사용
    public long getPrize() {
        return prize;
    }

    public boolean isBonusRank() {
        return this == SECOND;
    }

    // DTO 생성을 위해 포맷팅된 상금 문자열을 반환
    public String getFormattedPrize() {
        return NumberFormat.getInstance(Locale.KOREA).format(prize);
    }

    // DTO 생성을 위해 포맷팅된 등수 설명 문자열을 반환
    public String getDescription() {
        if (isBonusRank()) {
            return String.format("%d개 일치, 보너스 볼 일치", matchCount);
        }
        return String.format("%d개 일치", matchCount);
    }
}