package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.LottoNumber;
import lotto.validation.ValidationMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성

    @DisplayName("로또 번호에 1 미만의 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_1미만_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidationMessages.ERROR_LOTTO_NUMBER_OUT_OF_RANGE);
    }

    @DisplayName("로또 번호에 45 초과의 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_45초과_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidationMessages.ERROR_LOTTO_NUMBER_OUT_OF_RANGE);
    }

    @DisplayName("Lotto 생성 시 번호가 오름차순으로 정렬된다.")
    @Test
    void 로또_번호가_생성시_오름차순으로_정렬된다() {
        List<Integer> unsortedNumbers = List.of(6, 5, 4, 3, 2, 1);

        Lotto lotto = new Lotto(unsortedNumbers);
        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    @DisplayName("특정 번호를 포함하고 있는지 정확히 반환한다.")
    @Test
    void containsNumber_test() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(6);
        LottoNumber missNumber = new LottoNumber(7);

        boolean hasBonus = lotto.containsNumber(bonusNumber);
        boolean hasMiss = lotto.containsNumber(missNumber);

        assertThat(hasBonus).isTrue();
        assertThat(hasMiss).isFalse();
    }

    @DisplayName("두 로또 번호의 일치 개수를 정확히 반환한다.")
    @Test
    void countMatchingNumbers_test() {
        // given
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 7, 8, 9));
        Lotto lotto3 = new Lotto(List.of(10, 11, 12, 13, 14, 15));

        // when
        int matchCount3 = lotto1.countMatchingNumbers(lotto2);
        int matchCount0 = lotto1.countMatchingNumbers(lotto3);

        // then
        assertThat(matchCount3).isEqualTo(3);
        assertThat(matchCount0).isEqualTo(0);
    }
}
