package lotto.domain;

import lotto.validation.ValidationMessages;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.containsNumber(bonusNumber)) {
            throw new IllegalArgumentException(ValidationMessages.ERROR_BONUS_DUPLICATE);
        }
    }

    public Rank determineRank(Lotto userLotto) {
        int matchCount = userLotto.countMatchingNumbers(winningNumbers);
        boolean hasBonus = userLotto.containsNumber(bonusNumber);

        return Rank.valueOf(matchCount, hasBonus);
    }

}