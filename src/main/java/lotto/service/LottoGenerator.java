package lotto.service;

import lotto.domain.Lotto;

// 로또 생성 인터페이스
@FunctionalInterface
public interface LottoGenerator {
    Lotto generate();
}
