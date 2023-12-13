package vendingmachine.domain.drink;

import java.util.Objects;

public class Quantity {
    private static final int MINIMUM_QUANTITY = 0;

    private final int quantity;

    public Quantity(int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity < MINIMUM_QUANTITY) {
            throw new IllegalArgumentException(
                    String.format("상품 수량이 %d보다 작을 수 없습니다.", MINIMUM_QUANTITY));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity1 = (Quantity) o;
        return quantity == quantity1.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }
}
