package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.validation.ValidationMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("LottoNumber 값 객체 테스트")
class LottoNumberTest {

    @Test
    @DisplayName("1~45 범위의 숫자로 LottoNumber를 생성할 수 있다")
    void create_lotto_number_success() {
        LottoNumber number = new LottoNumber(45);
        assertThat(number.toString()).isEqualTo("45");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    @DisplayName("1 미만의 숫자로 생성 시 예외를 발생시킨다")
    void create_fail_less_than_min(int invalidNumber) {
        assertThatThrownBy(() -> new LottoNumber(invalidNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidationMessages.ERROR_LOTTO_NUMBER_OUT_OF_RANGE);
    }

    @ParameterizedTest
    @ValueSource(ints = {46, 100})
    @DisplayName("45 초과의 숫자로 생성 시 예외를 발생시킨다")
    void create_fail_greater_than_max(int invalidNumber) {
        assertThatThrownBy(() -> new LottoNumber(invalidNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidationMessages.ERROR_LOTTO_NUMBER_OUT_OF_RANGE);
    }
}