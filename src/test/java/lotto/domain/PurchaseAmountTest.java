package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.validation.ValidationMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("PurchaseAmount 값 객체 테스트")
class PurchaseAmountTest {

    @Test
    @DisplayName("1000원 단위의 금액으로 객체를 생성할 수 있다")
    void create_purchase_amount_success() {
        int validAmount = 8000;

        PurchaseAmount amount = new PurchaseAmount(validAmount);

        assertThat(amount.getTicketCount()).isEqualTo(8);
    }

    @ParameterizedTest
    @ValueSource(ints = {1500, 2100, 8001})
    @DisplayName("1000원 단위가 아닌 금액은 예외를 발생시킨다")
    void create_fail_with_invalid_unit(int invalidAmount) {

        assertThatThrownBy(() -> new PurchaseAmount(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidationMessages.ERROR_AMOUNT_INVALID_UNIT);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 500, -1000, 1})
    @DisplayName("1000원 미만의 금액은 예외를 발생시킨다")
    void create_fail_with_less_than_minimum(int invalidAmount) {

        assertThatThrownBy(() -> new PurchaseAmount(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidationMessages.ERROR_AMOUNT_MINIMUM);
    }

    @Test
    @DisplayName("총 당첨금을 기준으로 수익률을 정확히 계산한다 (요구사항 예시)")
    void calculateProfitRate_test() {
        // given
        PurchaseAmount amount = new PurchaseAmount(8000); // 8000원 투자
        long totalPrize = 5000; // 5000원 당첨

        // when
        double profitRate = amount.calculateProfitRate(totalPrize);

        // then
        // (5000 / 8000) * 100 = 62.5
        assertThat(profitRate).isEqualTo(62.5);
    }
}