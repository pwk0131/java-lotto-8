package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

@DisplayName("RandomLottoGenerator 테스트")
class RandomLottoGeneratorTest {

    private final LottoGenerator lottoGenerator = new RandomLottoGenerator();

    @DisplayName("generate() 메서드가 유효한 Lotto 객체를 반환한다")
    @RepeatedTest(10)
        // 10번 반복하여 랜덤 생성의 안정성 확인
    void generate_returns_valid_lotto() {
        Lotto lotto = lottoGenerator.generate();

        // 1. null이 아니어야 함
        assertThat(lotto).isNotNull();

        // 2. Lotto 클래스의 인스턴스여야 함
        assertThat(lotto).isInstanceOf(Lotto.class);

        // 3. (가장 중요) Lotto 생성자의 유효성 검사(6개, 중복X, 1-45범위)를
        //    통과했는지 확인. 예외가 발생하지 않으면 성공.
        assertThatCode(() -> lottoGenerator.generate())
                .doesNotThrowAnyException();
    }
}
