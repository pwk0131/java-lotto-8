package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Lottos 일급 컬렉션 테스트")
class LottosTest {

    @Test
    @DisplayName("toString() 메서드가 로또 목록을 올바르게 포맷팅한다")
    void toString_formats_lotto_list_correctly() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(7, 8, 9, 10, 11, 12));
        Lottos lottos = new Lottos(List.of(lotto1, lotto2));

        String result = lottos.toString();

        String expected = "[1, 2, 3, 4, 5, 6]\n[7, 8, 9, 10, 11, 12]";
        assertThat(result).isEqualTo(expected);
    }
}
