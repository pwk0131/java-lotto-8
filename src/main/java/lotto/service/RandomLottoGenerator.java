package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.Lotto;
import lotto.domain.LottoNumber;

public class RandomLottoGenerator implements LottoGenerator {
    private static final int LOTTO_NUMBER_COUNT = 6;

    @Override
    public Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                LottoNumber.MIN_NUMBER,
                LottoNumber.MAX_NUMBER,
                LOTTO_NUMBER_COUNT
        );
        return new Lotto(numbers); // Lotto 생성자가 정렬 및 검증
    }
}
