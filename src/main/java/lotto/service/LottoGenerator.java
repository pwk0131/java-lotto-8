package lotto.service;

import lotto.Lotto;

// 로또 생성 인터페이스
@FunctionalInterface
public interface LottoGenerator {
    Lotto generate();
}
