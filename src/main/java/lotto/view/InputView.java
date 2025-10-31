package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String PROMPT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String PROMPT_WINNING_NUMBERS = "\n당첨 번호를 입력해 주세요.";
    private static final String PROMPT_BONUS_NUMBER = "\n보너스 번호를 입력해 주세요.";

    public String readPurchaseAmount() {
        System.out.println(PROMPT_PURCHASE_AMOUNT);
        return Console.readLine();
    }

    public String readWinningNumbers() {
        System.out.println(PROMPT_WINNING_NUMBERS);
        return Console.readLine();
    }

    public String readBonusNumbers() {
        System.out.println(PROMPT_BONUS_NUMBER);
        return Console.readLine();
    }
}
