package lotto.validation;

public class ValidationMessages {

    private static final String ERROR_PREFIX = "[ERROR] ";

    // 금액 관련
    public static final String ERROR_INPUT_NOT_NUMERIC = ERROR_PREFIX + "입력값이 숫자가 아닙니다.";
    public static final String ERROR_AMOUNT_INVALID_UNIT = ERROR_PREFIX + "구입 금액은 1,000원 단위여야 합니다.";
    public static final String ERROR_AMOUNT_MINIMUM = ERROR_PREFIX + "구입 금액은 1,000원 이상이어야 합니다.";

    // 로또 번호 공통
    public static final String ERROR_LOTTO_NUMBER_OUT_OF_RANGE = ERROR_PREFIX + "로또 번호는 1부터 45 사이의 숫자여야 합니다.";

    // Lotto 클래스 내부 (도메인 검증)
    public static final String ERROR_LOTTO_INVALID_SIZE = ERROR_PREFIX + "로또 번호는 6개여야 합니다.";
    public static final String ERROR_LOTTO_DUPLICATE_NUMBERS = ERROR_PREFIX + "로또 번호에 중복된 숫자가 있습니다.";

    // 당첨 번호 입력 검증용
    public static final String ERROR_WINNING_NUMBERS_INVALID_FORMAT = ERROR_PREFIX + "당첨 번호는 쉼표(,)로 구분된 숫자 6개여야 합니다.";
    public static final String ERROR_WINNING_NUMBERS_INVALID_COUNT = ERROR_PREFIX + "당첨 번호는 6개여야 합니다.";
    public static final String ERROR_WINNING_NUMBERS_DUPLICATE = ERROR_PREFIX + "당첨 번호에 중복된 숫자가 있습니다.";

    private ValidationMessages() {
    }
}
