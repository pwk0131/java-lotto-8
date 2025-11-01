package lotto.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Validator 유틸리티 테스트")
class ValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"1000j", "abc", " 1000"}) // " 1000"은 trim()으로 1000이 됨.
    @DisplayName("숫자가 아닌 금액 입력 시 예외를 발생시킨다")
    void parseAmount_fail_not_numeric(String input) {
        // " 1000"은 성공 케이스, 하지만 parseNumeric(trim())을 테스트
        if (input.equals(" 1000")) {
            assertThat(Validator.parseAmount(input)).isEqualTo(1000);
        } else {
            assertThatThrownBy(() -> Validator.parseAmount(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ValidationMessages.ERROR_INPUT_NOT_NUMERIC);
        }
    }

    @Test
    @DisplayName("정상적인 당첨 번호 문자열을 List<Integer>로 변환한다")
    void parseWinningNumbers_success() {
        String input = "1,2,3,4,5,6";
        List<Integer> numbers = Validator.parseWinningNumbers(input);
        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("당첨 번호 개수가 6개가 아니면 예외를 발생시킨다 (5개)")
    void parseWinningNumbers_fail_invalid_count() {
        String input = "1,2,3,4,5";
        assertThatThrownBy(() -> Validator.parseWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidationMessages.ERROR_WINNING_NUMBERS_INVALID_COUNT);
    }

    @Test
    @DisplayName("당첨 번호 개수가 6개가 아니면 예외를 발생시킨다 (7개)")
    void parseWinningNumbers_fail_invalid_count_over() {
        String input = "1,2,3,4,5,6,7";
        assertThatThrownBy(() -> Validator.parseWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidationMessages.ERROR_WINNING_NUMBERS_INVALID_COUNT);
    }

    @Test
    @DisplayName("당첨 번호에 중복이 있으면 예외를 발생시킨다")
    void parseWinningNumbers_fail_duplicate() {
        String input = "1,2,3,4,5,5";
        assertThatThrownBy(() -> Validator.parseWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidationMessages.ERROR_WINNING_NUMBERS_DUPLICATE);
    }

    @Test
    @DisplayName("당첨 번호에 숫자가 아닌 값이 있으면 예외를 발생시킨다")
    void parseWinningNumbers_fail_not_numeric() {
        String input = "1,2,3,4,5,a";
        assertThatThrownBy(() -> Validator.parseWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidationMessages.ERROR_WINNING_NUMBERS_INVALID_FORMAT);
    }
}
