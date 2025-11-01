package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.validation.ValidationMessages;


public class Lotto {
    public static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);

        this.numbers = numbers.stream()
                .sorted()
                .map(LottoNumber::new) // LottoNumber가 개별 숫자 범위 검증
                .toList();
    }

    // TODO: 추가 기능 구현
    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicates(numbers);
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


    // 특정 로또 번호를 포함하고 있는지 확인
    public boolean containsNumber(LottoNumber number) {
        return this.numbers.contains(number);
    }

    public int countMatchingNumbers(Lotto other) {
        return (int) this.numbers.stream()
                .filter(other.numbers::contains)
                .count();
    }


    @Override
    public String toString() {
        List<Integer> values = numbers.stream()
                .map(LottoNumber::getValue)
                .toList();
        return values.toString();
    }

}
