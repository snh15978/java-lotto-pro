package step3.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Lotto {
    private static final int LOTTO_NUMBER_SIZE = 6;

    private List<Number> numbers;

    public Lotto(List<Number> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Number> numbers) {
        if (new HashSet<>(numbers).size() < LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("중복된 숫자가 존재합니다.");
        }

        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 입력 숫자는 6개여야합니다.");
        }
    }

    public List<Number> getNumbers() {
        return numbers;
    }

    public boolean contains(Number number) {
        return numbers.contains(number);
    }

    public Award match(WinningLotto winningLotto) {
        return Award.valueOf(Math.toIntExact(
            numbers.stream()
                .filter(lottoNumber -> winningLotto.contains(lottoNumber))
                .count())
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
