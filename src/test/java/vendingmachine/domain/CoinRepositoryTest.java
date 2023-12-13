package vendingmachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.Coin;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class CoinRepositoryTest {

    @Test
    @DisplayName("잔돈을 반환할 수 있다.")
    void giveChangeOf() {
        Map<Coin, Integer> coins = initializeCoins();
        CoinRepository coinRepository = new CoinRepository(coins);

        Map<Coin, Integer> result = coinRepository.giveChangeOf(new InputAmount(390));
        assertThat(result).contains(
                entry(Coin.COIN_500, 0),
                entry(Coin.COIN_100, 3),
                entry(Coin.COIN_50, 1),
                entry(Coin.COIN_10, 4));
    }

    private Map<Coin, Integer> initializeCoins() {
        Map<Coin, Integer> coins = new EnumMap<>(Coin.class);

        for (Coin coin : Coin.values()) {
            coins.put(coin, 5);
        }

        return coins;
    }
}