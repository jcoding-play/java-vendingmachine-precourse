package vendingmachine.domain;

import java.util.Objects;

public class TotalAmount {
    private static final int MINIMUM_TOTAL_AMOUNT = 0;
    private static final int VALID_UNIT = 10;
    private static final int VALID_REMAINED_AMOUNT = 0;

    private final int totalAmount;

    public TotalAmount(int totalAmount) {
        validateTotalAmount(totalAmount);
        this.totalAmount = totalAmount;
    }

    private void validateTotalAmount(int totalAmount) {
        if (totalAmount < MINIMUM_TOTAL_AMOUNT) {
            throw new IllegalArgumentException(
                    String.format("자판기가 보유한 금액이 %d원보다 작을 수 없습니다.", MINIMUM_TOTAL_AMOUNT));
        }
        if (isInvalidAmount(totalAmount)) {
            throw new IllegalArgumentException(
                    String.format("자판기가 보유한 금액은 %d원 단위어야 합니다.", VALID_UNIT));
        }
    }

    private boolean isInvalidAmount(int totalAmount) {
        return totalAmount % VALID_UNIT != VALID_REMAINED_AMOUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalAmount that = (TotalAmount) o;
        return totalAmount == that.totalAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalAmount);
    }
}
