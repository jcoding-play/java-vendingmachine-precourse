package vendingmachine;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public int divide(int amount) {
        return amount / this.amount;
    }

    public int calculateTotalAmount(int quantity) {
        return this.amount * quantity;
    }
}
