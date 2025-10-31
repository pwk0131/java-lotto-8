package lotto.controller;

import lotto.domain.PurchaseAmount;
import lotto.validation.Validator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        PurchaseAmount purchaseAmount = readPurchaseAmountWithRetry();
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

}