package vendingmachine.domain;

import vendingmachine.utils.Constants;

import java.util.Objects;

public class InputAmount {
    private static final int MINIMUM_TOTAL_AMOUNT = 0;

    private final int inputAmount;

    public InputAmount(int inputAmount) {
        validateInputAmount(inputAmount);
        this.inputAmount = inputAmount;
    }

    private void validateInputAmount(int inputAmount) {
        if (inputAmount < MINIMUM_TOTAL_AMOUNT) {
            throw new IllegalArgumentException(
                    String.format("투입 금액이 %d원보다 작을 수 없습니다.", MINIMUM_TOTAL_AMOUNT));
        }
        if (isInvalidAmount(inputAmount)) {
            throw new IllegalArgumentException(
                    String.format("투입 금액은 %d원 단위어야 합니다.", Constants.VALID_UNIT));
        }
    }

    private boolean isInvalidAmount(int inputAmount) {
        return inputAmount % Constants.VALID_UNIT != Constants.VALID_REMAINED_AMOUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputAmount that = (InputAmount) o;
        return inputAmount == that.inputAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputAmount);
    }
}
