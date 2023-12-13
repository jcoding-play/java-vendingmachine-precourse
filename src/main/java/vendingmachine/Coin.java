package vendingmachine;

import java.util.Arrays;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static Coin from(int amount) {
        return Arrays.stream(values())
                .filter(coin -> coin.isMatchAmount(amount))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 동전이 없습니다."));
    }

    private boolean isMatchAmount(int amount) {
        return this.amount == amount;
    }

    public int divide(int amount) {
        return amount / this.amount;
    }

    public int calculateTotalAmount(int quantity) {
        return this.amount * quantity;
    }

    public int getAmount() {
        return amount;
    }
}
