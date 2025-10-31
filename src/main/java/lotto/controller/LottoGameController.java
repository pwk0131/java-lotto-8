package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.Lotto;
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

    private void buyLottos(PurchaseAmount purchaseAmount) {
        int count = purchaseAmount.getTicketCount();
        outputView.printTicketCount(count);

        List<Lotto> purchased = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            purchased.add(lottoGenerator.generate());
        }

        Lottos lottos = new Lottos(purchased);
        outputView.printLottos(lottos);
    }

}