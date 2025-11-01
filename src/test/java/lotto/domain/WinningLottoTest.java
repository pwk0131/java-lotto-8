package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.validation.ValidationMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("WinningLotto 도메인 로직 테스트")
class WinningLottoTest {

    private Lotto winningNumbers;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        // 당첨 번호: 1, 2, 3, 4, 5, 6
        winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // 보너스 번호: 7
        LottoNumber bonusNumber = new LottoNumber(7);
        winningLotto = new WinningLotto(winningNumbers, bonusNumber);
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호가 중복되지 않으면 성공적으로 생성된다")
    void create_success_when_not_duplicate() {
        LottoNumber bonusNumber = new LottoNumber(7); // 중복 아님

        assertThatCode(() -> new WinningLotto(winningNumbers, bonusNumber))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호가 중복되면 예외를 발생시킨다")
    void create_fail_when_bonus_is_duplicate() {
        LottoNumber duplicateBonus = new LottoNumber(6); // 6은 당첨번호에 포함

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, duplicateBonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidationMessages.ERROR_BONUS_DUPLICATE);
    }

    @Test
    @DisplayName("1등(6개 일치) 등수를 정확히 판별한다")
    void determineRank_FIRST() {
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Rank rank = winningLotto.determineRank(userLotto);
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("2등(5개 + 보너스 일치) 등수를 정확히 판별한다")
    void determineRank_SECOND() {
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7)); // 5개 일치 + 보너스 7
        Rank rank = winningLotto.determineRank(userLotto);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("3등(5개 일치, 보너스 불일치) 등수를 정확히 판별한다")
    void determineRank_THIRD() {
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8)); // 5개 일치 + 보너스(7) 불일치
        Rank rank = winningLotto.determineRank(userLotto);
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("5등(3개 일치) 등수를 정확히 판별한다")
    void determineRank_FIFTH() {
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        Rank rank = winningLotto.determineRank(userLotto);
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }
}