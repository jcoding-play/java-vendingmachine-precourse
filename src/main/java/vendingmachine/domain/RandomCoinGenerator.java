package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.Coin;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class RandomCoinGenerator implements CoinGenerator {

    @Override
    public Map<Coin, Integer> generate(TotalAmount totalAmount) {
        Map<Coin, Integer> result = new EnumMap<>(Coin.class);

        for (Coin coin : Coin.values()) {
            int quantity = pickQuantity(totalAmount, coin);
            totalAmount.subtractBy(coin, quantity);

            result.put(coin, quantity);
        }

        return result;
    }

    private int pickQuantity(TotalAmount totalAmount, Coin coin) {
        List<Integer> interchangeableCounts = totalAmount.findInterchangeableCounts(coin);
        return Randoms.pickNumberInList(interchangeableCounts);
    }
}
