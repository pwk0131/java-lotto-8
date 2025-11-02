package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.service.LottoGenerator;
import lotto.validation.ValidationMessages;
import lotto.view.OutputView;

public class PurchaseAmount {
    public static final int TICKET_PRICE = 1_000;

    private final int amount;

    public PurchaseAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        validateMinimumAmount(amount);
        validateThousandUnit(amount);
    }

    private void validateMinimumAmount(int amount) {
        if (amount < TICKET_PRICE) {
            throw new IllegalArgumentException(ValidationMessages.ERROR_AMOUNT_MINIMUM);
        }
    }

    private void validateThousandUnit(int amount) {
        if (amount % TICKET_PRICE != 0) {
            throw new IllegalArgumentException(ValidationMessages.ERROR_AMOUNT_INVALID_UNIT);
        }
    }

    // 외부 도메인에서의 접근 차단
    int getTicketCount() {
        return amount / TICKET_PRICE;
    }

    // 로또 생성 및 개수 출력을 담당 (Tell, Don't Ask)
    public Lottos buyLottos(LottoGenerator lottoGenerator, OutputView outputView) {
        int count = getTicketCount(); // 내부(private/package-private) 호출은 OK
        outputView.printTicketCount(count); // View에 출력을 "명령"

        List<Lotto> purchased = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            purchased.add(lottoGenerator.generate());
        }
        return new Lottos(purchased);
    }

    public double calculateProfitRate(long totalPrize) {
        if (this.amount == 0) {
            return 0.0;
        }
        return (double) totalPrize / this.amount * 100.0;
    }
}
