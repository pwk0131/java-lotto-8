package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.Lotto;
import lotto.validation.ValidationMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("WinningLotto 도메인 로직 테스트")
class WinningLottoTest {

    private Lotto winningNumbers;

    @BeforeEach
    void setUp() {
        // 당첨 번호: 1, 2, 3, 4, 5, 6
        winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
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
}