package vendingmachine.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.Coin;
import vendingmachine.domain.drink.Drink;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class VendingMachineTest {

    @Test
    @DisplayName("음료를 구입할 수 있다.")
    void buyDrink() {
        Map<Coin, Integer> coins = initializeCoins();
        CoinRepository coinRepository = new CoinRepository(coins);
        Drinks drinks = new Drinks(Arrays.asList(
                new Drink("콜라", 1500, 10),
                new Drink("사이다", 1000, 20)));
        InputAmount inputAmount = new InputAmount(3000);
        VendingMachine vendingMachine = new VendingMachine(coinRepository, drinks, inputAmount);

        vendingMachine.buyDrink("콜라");

        int result = vendingMachine.getInputAmount();
        assertThat(result).isEqualTo(1500);
    }

    private Map<Coin, Integer> initializeCoins() {
        Map<Coin, Integer> coins = new EnumMap<>(Coin.class);

        for (Coin coin : Coin.values()) {
            coins.put(coin, 5);
        }

        return coins;
    }

    @Test
    void canBuyDrink() {
    }

    @Test
    void giveChange() {
    }
}