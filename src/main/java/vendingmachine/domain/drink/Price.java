package vendingmachine.domain.drink;

import vendingmachine.utils.Constants;

import java.util.Objects;

public class Price implements Comparable<Price> {
    private static final int MINIMUM_PRICE = 100;

    private final int price;

    public Price(int price) {
        validatePrice(price);
        this.price = price;
    }

    private void validatePrice(int price) {
        if (price < MINIMUM_PRICE) {
            throw new IllegalArgumentException(
                    String.format("상품의 가격은 최소 %d원 이상이어야 합니다.", MINIMUM_PRICE));
        }
        if (isInvalidPrice(price)) {
            throw new IllegalArgumentException(
                    String.format("상품 가격은 %d원 단위어야 합니다.", Constants.VALID_UNIT));
        }
    }

    private boolean isInvalidPrice(int price) {
        return price % Constants.VALID_UNIT != Constants.VALID_REMAINED_AMOUNT;
    }

    public int get() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return price == price1.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }

    @Override
    public int compareTo(Price other) {
        return this.price - other.price;
    }
}
