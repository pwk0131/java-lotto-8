package lotto.domain;

import lotto.validation.ValidationMessages;

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
}
