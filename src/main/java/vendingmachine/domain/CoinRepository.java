package vendingmachine.domain;

import vendingmachine.Coin;

import java.util.EnumMap;
import java.util.Map;

public class CoinRepository {
    private final Map<Coin, Integer> store;

    public CoinRepository(Map<Coin, Integer> store) {
        this.store = store;
    }

    public Map<Coin, Integer> giveChangeOf(InputAmount inputAmount) {
        Map<Coin, Integer> changes = new EnumMap<>(Coin.class);

        for (Coin coin : store.keySet()) {
            int changeCount = calculateChangeCount(inputAmount, coin);

            changes.put(coin, changeCount);
            store.put(coin, store.get(coin) - changeCount);
        }

        return changes;
    }

    private int calculateChangeCount(InputAmount inputAmount, Coin coin) {
        int coinQuantity = store.get(coin);
        int result = inputAmount.divideBy(coin);

        return calculateChangeCountOf(coinQuantity, result);
    }

    private int calculateChangeCountOf(int coinQuantity, int result) {
        return Math.min(coinQuantity, result);
    }
}
