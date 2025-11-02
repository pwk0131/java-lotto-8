package lotto.controller;

import java.util.List;
import lotto.GameViews;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.Statistics;
import lotto.domain.WinningLotto;
import lotto.dto.StatisticsReportDTO;
import lotto.service.LottoGenerator;
import lotto.validation.Validator;

public class LottoGameController {

    private final GameViews gameViews;
    private final LottoGenerator lottoGenerator;

    public LottoGameController(GameViews gameViews, LottoGenerator lottoGenerator) {
        this.gameViews = gameViews;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        // 1. 구입금액 입력
        PurchaseAmount purchaseAmount = readPurchaseAmountWithRetry();
        // 2.로또 구매 및 출력
        Lottos lottos = buyLottos(purchaseAmount);
        // 3. 당첨 번호 입력
        Lotto winningNumbers = readWinningNumbersWithRetry();
        // 4. 보너스 번호 입력
        LottoNumber bonusNumber = readBonusNumberWithRetry(winningNumbers);
        // 5. 당첨 로또 생성
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        // 6. 결과 계산 및 출력
        showResults(lottos, winningLotto, purchaseAmount);
    }

    private PurchaseAmount readPurchaseAmountWithRetry() {
        try {
            return tryReadPurchaseAmount();
        } catch (IllegalArgumentException e) {
            gameViews.outputView().printError(e);
            return readPurchaseAmountWithRetry();
        }
    }

    private PurchaseAmount tryReadPurchaseAmount() {
        String input = gameViews.inputView().readPurchaseAmount();
        int amount = Validator.parseAmount(input);
        return new PurchaseAmount(amount);
    }

    private Lottos buyLottos(PurchaseAmount purchaseAmount) {
        // PurchaseAmount.buyLottos() 리팩토링 유지
        Lottos lottos = purchaseAmount.buyLottos(lottoGenerator, gameViews.outputView());
        gameViews.outputView().printLottos(lottos);

        return lottos;
    }

    private Lotto readWinningNumbersWithRetry() {
        try {
            return tryReadWinningNumbers();
        } catch (IllegalArgumentException e) {
            gameViews.outputView().printError(e);
            return readWinningNumbersWithRetry();
        }
    }
    
    private Lotto tryReadWinningNumbers() {
        String input = gameViews.inputView().readWinningNumbers();
        List<Integer> numbers = Validator.parseWinningNumbers(input);
        return new Lotto(numbers);
    }

    private LottoNumber readBonusNumberWithRetry(Lotto winningNumbers) {
        try {
            return tryReadBonusNumber(winningNumbers);
        } catch (IllegalArgumentException e) {
            gameViews.outputView().printError(e);
            return readBonusNumberWithRetry(winningNumbers);
        }
    }

    private LottoNumber tryReadBonusNumber(Lotto winningNumbers) {
        String input = gameViews.inputView().readBonusNumbers();
        int number = Validator.parseBonusNumber(input);
        LottoNumber bonusNumber = new LottoNumber(number);

        validateBonusNumberDuplication(winningNumbers, bonusNumber);

        return bonusNumber;
    }

    private void validateBonusNumberDuplication(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.containsNumber(bonusNumber)) {
            throw new IllegalArgumentException(Validator.ERROR_BONUS_DUPLICATE);
        }
    }

    private void showResults(Lottos lottos, WinningLotto winningLotto, PurchaseAmount purchaseAmount) {
        Statistics statistics = lottos.calculateStatistics(winningLotto);
        StatisticsReportDTO report = statistics.generateReport(purchaseAmount);

        gameViews.outputView().printStatisticsReport(report);
    }
}

