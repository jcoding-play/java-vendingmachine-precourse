package vendingmachine.domain;

import vendingmachine.Coin;
import vendingmachine.utils.Constants;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TotalAmount {
    private int totalAmount;

    public TotalAmount(int totalAmount) {
        validateTotalAmount(totalAmount);
        this.totalAmount = totalAmount;
    }

    private void validateTotalAmount(int totalAmount) {
        if (totalAmount < Constants.MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(
                    String.format("자판기가 보유한 금액이 %d원보다 작을 수 없습니다.", Constants.MINIMUM_AMOUNT));
        }
        if (isInvalidAmount(totalAmount)) {
            throw new IllegalArgumentException(
                    String.format("자판기가 보유한 금액은 %d원 단위어야 합니다.", Constants.VALID_UNIT));
        }
    }

    private boolean isInvalidAmount(int totalAmount) {
        return totalAmount % Constants.VALID_UNIT != Constants.VALID_REMAINED_AMOUNT;
    }

    public List<Integer> findInterchangeableCounts(Coin coin) {
        return findInterchangeableCounts(coin.divide(totalAmount));
    }

    private List<Integer> findInterchangeableCounts(int result) {
        return IntStream.rangeClosed(0, result)
                .boxed()
                .collect(Collectors.toList());
    }

    public void subtractBy(int amount) {
        totalAmount -= amount;
    }

    public boolean isGreaterThan(int amount) {
        return totalAmount > amount;
    }

    public boolean isEqual(int amount) {
        return totalAmount == amount;
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
