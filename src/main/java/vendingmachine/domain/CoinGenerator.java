package vendingmachine.domain;

import vendingmachine.Coin;

import java.util.Map;

@FunctionalInterface
public interface CoinGenerator {

    Map<Coin, Integer> generate(TotalAmount totalAmount);
}
