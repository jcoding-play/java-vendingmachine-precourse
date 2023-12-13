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
    @DisplayName("음료를 구입할 수 있는지 알 수 있다.")
    void canBuyDrink_true() {
        Map<Coin, Integer> coins = initializeCoins();
        CoinRepository coinRepository = new CoinRepository(coins);
        Drinks drinks = new Drinks(Arrays.asList(
                new Drink("콜라", 1500, 10),
                new Drink("사이다", 1000, 20)));
        InputAmount inputAmount = new InputAmount(3000);
        VendingMachine vendingMachine = new VendingMachine(coinRepository, drinks, inputAmount);

        boolean result = vendingMachine.canBuyDrink();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("음료가 모두 팔렸으면 음료를 살 수 없다.")
    void canBuyDrink_false_V1() {
        Map<Coin, Integer> coins = initializeCoins();
        CoinRepository coinRepository = new CoinRepository(coins);
        Drinks drinks = new Drinks(Arrays.asList(
                new Drink("콜라", 1500, 0),
                new Drink("사이다", 1000, 0)));
        InputAmount inputAmount = new InputAmount(3000);
        VendingMachine vendingMachine = new VendingMachine(coinRepository, drinks, inputAmount);

        boolean result = vendingMachine.canBuyDrink();

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("음료의 최저 가격보다 투입 금액이 작으면 음료를 살 수 없다.")
    void canBuyDrink_false_V2() {
        Map<Coin, Integer> coins = initializeCoins();
        CoinRepository coinRepository = new CoinRepository(coins);
        Drinks drinks = new Drinks(Arrays.asList(
                new Drink("콜라", 1500, 10),
                new Drink("사이다", 1000, 20)));
        InputAmount inputAmount = new InputAmount(900);
        VendingMachine vendingMachine = new VendingMachine(coinRepository, drinks, inputAmount);

        boolean result = vendingMachine.canBuyDrink();

        assertThat(result).isFalse();
    }
}