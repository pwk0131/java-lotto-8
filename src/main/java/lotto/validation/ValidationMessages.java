package lotto.validation;

public class ValidationMessages {

    private static final String ERROR_PREFIX = "[ERROR] ";

    // 금액 관련
    public static final String ERROR_INPUT_NOT_NUMERIC = ERROR_PREFIX + "입력값이 숫자가 아닙니다.";
    public static final String ERROR_AMOUNT_INVALID_UNIT = ERROR_PREFIX + "구입 금액은 1,000원 단위여야 합니다.";
    public static final String ERROR_AMOUNT_MINIMUM = ERROR_PREFIX + "구입 금액은 1,000원 이상이어야 합니다.";

}
