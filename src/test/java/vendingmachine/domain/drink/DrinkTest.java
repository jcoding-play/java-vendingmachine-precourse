package vendingmachine.domain.drink;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class DrinkTest {

    @ParameterizedTest
    @CsvSource(value = {"1, false", "0, true"})
    @DisplayName("해당 음료의 개수가 있는지 없는지 알 수 있다.")
    void isSoldOut(int quantity, boolean expected) {
        Drink drink = new Drink("콜라", 1500, quantity);
        boolean actual = drink.isSoldOut();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"콜라, true", "사이다, false"})
    @DisplayName("상품명이 일치하는지 알 수 있다.")
    void isMatchName(String name, boolean expected) {
        Drink drink = new Drink("콜라", 1500, 1);
        boolean actual = drink.isMatchName(name);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void decreaseQuantity() {
    }
}