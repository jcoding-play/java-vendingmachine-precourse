package vendingmachine.domain.drink;

import java.util.Objects;

public class Name {
    private static final int MINIMUM_LENGTH_OF_NAME = 1;
    private static final int MAXIMUM_LENGTH_OF_NAME = 10;

    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        int length = name.length();

        if (length < MINIMUM_LENGTH_OF_NAME || length > MAXIMUM_LENGTH_OF_NAME) {
            throw new IllegalArgumentException(
                    String.format("상품명이 공백이거나 %d자를 초과할 수 없습니다.", MAXIMUM_LENGTH_OF_NAME));
        }
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

    public String get() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
