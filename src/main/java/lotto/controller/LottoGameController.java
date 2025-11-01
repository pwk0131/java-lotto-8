package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.service.LottoGenerator;
import lotto.validation.Validator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;

    public LottoGameController(InputView inputView, OutputView outputView, LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {

        // 1. 구입금액 입력
        PurchaseAmount purchaseAmount = readPurchaseAmountWithRetry();

        // 2.로또 구매 및 출력
        buyLottos(purchaseAmount);

        // 3. 당첨 번호 입력
        Lotto winningNumbers = readWinningNumbersWithRetry();

        // 4. 보너스 번호 입력
        LottoNumber bonusNumber = readBonusNumberWithRetry(winningNumbers);

        // 5. 당첨 로또 생성
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
    }

    // 구입 금액 입력
    private PurchaseAmount readPurchaseAmountWithRetry() {
        try {
            return tryReadPurchaseAmount();
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            return readPurchaseAmountWithRetry();
        }
    }

    private PurchaseAmount tryReadPurchaseAmount() {
        String input = inputView.readPurchaseAmount();
        int amount = Validator.parseAmount(input);
        return new PurchaseAmount(amount);
    }

    private Lottos buyLottos(PurchaseAmount purchaseAmount) {
        int count = purchaseAmount.getTicketCount();
        outputView.printTicketCount(count);

        List<Lotto> purchased = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            purchased.add(lottoGenerator.generate());
        }

        Lottos lottos = new Lottos(purchased);
        outputView.printLottos(lottos);

        return lottos;
    }

    private Lotto readWinningNumbersWithRetry() {
        try {
            return tryReadWinningNumbers();
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            return readWinningNumbersWithRetry();
        }
    }

    private Lotto tryReadWinningNumbers() {
        String input = inputView.readWinningNumbers();
        List<Integer> numbers = Validator.parseWinningNumbers(input);
        return new Lotto(numbers); // new Lotto()가 1~45 범위 검증을 수행
    }

    private LottoNumber readBonusNumberWithRetry(Lotto winningNumbers) {
        try {
            return tryReadBonusNumber(winningNumbers);
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            return readBonusNumberWithRetry(winningNumbers);
        }
    }

    private LottoNumber tryReadBonusNumber(Lotto winningNumbers) {
        String input = inputView.readBonusNumbers();
        int number = Validator.parseBonusNumber(input);
        LottoNumber bonusNumber = new LottoNumber(number); // 1~45 범위 검증

        validateBonusNumberDuplication(winningNumbers, bonusNumber);

        return bonusNumber;
    }

    private void validateBonusNumberDuplication(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.containsNumber(bonusNumber)) {
            throw new IllegalArgumentException(Validator.ERROR_BONUS_DUPLICATE);
        }
    }


}