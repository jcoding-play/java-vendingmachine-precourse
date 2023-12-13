package vendingmachine.domain.drink;

import java.util.Objects;

public class Price {
    private static final int MINIMUM_PRICE = 100;
    private static final int VALID_UNIT = 10;
    private static final int VALID_REMAINED_AMOUNT = 0;

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
                    String.format("상품 가격은 %d원 단위어야 합니다.", VALID_UNIT));
        }
    }

    private boolean isInvalidPrice(int price) {
        return price % VALID_UNIT != VALID_REMAINED_AMOUNT;
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
}
