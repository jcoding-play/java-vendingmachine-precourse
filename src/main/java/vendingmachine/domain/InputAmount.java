package vendingmachine.domain;

import vendingmachine.Coin;
import vendingmachine.utils.Constants;

import java.util.Objects;

public class InputAmount {
    private int inputAmount;

    public InputAmount(int inputAmount) {
        validateInputAmount(inputAmount);
        this.inputAmount = inputAmount;
    }

    private void validateInputAmount(int inputAmount) {
        if (inputAmount < Constants.MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(
                    String.format("투입 금액이 %d원보다 작을 수 없습니다.", Constants.MINIMUM_AMOUNT));
        }
        if (isInvalidAmount(inputAmount)) {
            throw new IllegalArgumentException(
                    String.format("투입 금액은 %d원 단위어야 합니다.", Constants.VALID_UNIT));
        }
    }

    private boolean isInvalidAmount(int inputAmount) {
        return inputAmount % Constants.VALID_UNIT != Constants.VALID_REMAINED_AMOUNT;
    }

    public void subtractBy(int price) {
        inputAmount -= price;
    }

    public boolean isLessThan(int minimumPrice) {
        return inputAmount < minimumPrice;
    }

    public int divideBy(Coin coin) {
        return coin.divide(inputAmount);
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
