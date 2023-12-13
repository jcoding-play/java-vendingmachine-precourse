package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.Coin;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CoinGenerator {
    private final TotalAmount totalAmount;

    public CoinGenerator(TotalAmount totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Map<Coin, Integer> generate() {
        Map<Coin, Integer> result = new EnumMap<>(Coin.class);

        for (Coin coin : Coin.values()) {
            int quantity = pickQuantityOf(coin);
            totalAmount.subtractBy(coin, quantity);

            result.put(coin, quantity);
        }

        return result;
    }

    private int pickQuantityOf(Coin coin) {
        List<Integer> interchangeableCounts = totalAmount.findInterchangeableCounts(coin);
        return Randoms.pickNumberInList(interchangeableCounts);
    }
}
