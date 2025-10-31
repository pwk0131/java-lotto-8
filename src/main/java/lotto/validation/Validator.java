package lotto.validation;

public class Validator {

    private Validator() {
    }

    public static int parseAmount(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ValidationMessages.ERROR_INPUT_NOT_NUMERIC);
        }
    }
}
