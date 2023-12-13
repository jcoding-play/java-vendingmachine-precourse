package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.Coin;

import java.util.*;
import java.util.stream.Collectors;

public class RandomCoinGenerator implements CoinGenerator {
    private static final int INITIAL_COUNT = 0;

    @Override
    public Map<Coin, Integer> generate(TotalAmount totalAmount) {
        List<Integer> coinAmounts = findCoinAmounts();
        return pickCoins(totalAmount, coinAmounts);
    }

    private List<Integer> findCoinAmounts() {
        return Arrays.stream(Coin.values())
                .map(Coin::getAmount)
                .collect(Collectors.toList());
    }

    private Map<Coin, Integer> pickCoins(TotalAmount totalAmount, List<Integer> coinAmounts) {
        Map<Coin, Integer> result = initialize();

        while (canGenerateCoin(totalAmount, coinAmounts)) {
            int coinAmount = Randoms.pickNumberInList(coinAmounts);
            totalAmount.subtractBy(coinAmount);

            Coin coin = Coin.from(coinAmount);
            result.put(coin, result.get(coin) + 1);
        }

        return result;
    }

    private Map<Coin, Integer> initialize() {
        Map<Coin, Integer> result = new EnumMap<>(Coin.class);

        for (Coin coin : Coin.values()) {
            result.put(coin, INITIAL_COUNT);
        }

        return result;
    }

    private boolean canGenerateCoin(TotalAmount totalAmount, List<Integer> coinAmounts) {
        int minCoinAmount = findMinCoinAmount(coinAmounts);
        return totalAmount.isGreaterThan(minCoinAmount) || totalAmount.isEqual(minCoinAmount);
    }

    private int findMinCoinAmount(List<Integer> coinAmounts) {
        return coinAmounts.stream()
                .min(Comparator.naturalOrder())
                .get();
    }
}
