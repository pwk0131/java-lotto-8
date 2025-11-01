package lotto.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.Lotto;

public class Validator {

    private static final String COMMA = ",";

    private Validator() {
    }

    public static int parseAmount(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ValidationMessages.ERROR_INPUT_NOT_NUMERIC);
        }
    }

    public static List<Integer> parseWinningNumbers(String input) {
        String[] parts = input.split(COMMA);
        validateWinningNumbersCount(parts);

        List<Integer> numbers = parseNumericList(parts);
        validateWinningNumbersDuplicates(numbers);

        // (1~45 범위 검증은 new Lotto() 생성자가 담당)
        return numbers;
    }

    private static int parseNumeric(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ValidationMessages.ERROR_INPUT_NOT_NUMERIC);
        }
    }

    private static List<Integer> parseNumericList(String[] parts) {
        try {
            return Arrays.stream(parts)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ValidationMessages.ERROR_WINNING_NUMBERS_INVALID_FORMAT);
        }
    }

    private static void validateWinningNumbersCount(String[] parts) {
        if (parts.length != Lotto.LOTTO_SIZE) {
            throw new IllegalArgumentException(ValidationMessages.ERROR_WINNING_NUMBERS_INVALID_COUNT);
        }
    }

    private static void validateWinningNumbersDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != Lotto.LOTTO_SIZE) {
            throw new IllegalArgumentException(ValidationMessages.ERROR_WINNING_NUMBERS_DUPLICATE);
        }
    }
}
