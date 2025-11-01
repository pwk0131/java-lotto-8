package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.LottoNumber;
import lotto.validation.ValidationMessages;


public class Lotto {
    public static final int LOTTO_SIZE = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);

        this.numbers = numbers.stream()
                .sorted()
                .toList();
    }

    // TODO: 추가 기능 구현
    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicates(numbers);
        validateRange(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ValidationMessages.ERROR_LOTTO_INVALID_SIZE);
        }
    }

    private void validateDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ValidationMessages.ERROR_LOTTO_DUPLICATE_NUMBERS);
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            validateNumberRange(number);
        }
    }

    private void validateNumberRange(int number) {
        if (number < LottoNumber.MIN_NUMBER || number > LottoNumber.MAX_NUMBER) {
            throw new IllegalArgumentException(ValidationMessages.ERROR_LOTTO_NUMBER_OUT_OF_RANGE);
        }
    }

    // 특정 로또 번호를 포함하고 있는지 확인
    public boolean containsNumber(LottoNumber number) {
        return this.numbers.contains(number.getValue());
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

}
