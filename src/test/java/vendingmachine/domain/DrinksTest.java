package vendingmachine.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import vendingmachine.domain.drink.Drink;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class DrinksTest {

    @Test
    @DisplayName("판매하는 음료에 대한 정보들을 알 수 있다.")
    void createDrinks() {
        Drinks drinks = new Drinks(Arrays.asList(new Drink("콜라", 1500, 20), new Drink("사이다", 1000, 10)));
        assertThat(drinks).isEqualTo(new Drinks(Arrays.asList(new Drink("콜라", 1500, 20), new Drink("사이다", 1000, 10))));
    }

    @Test
    @DisplayName("음료의 이름이 중복되는 음료가 존재하면 예외가 발생한다.")
    void hasDuplicatedDrink() {
        List<Drink> drinks = Arrays.asList(new Drink("콜라", 1500, 20), new Drink("콜라", 2000, 20));

        assertThatThrownBy(() -> new Drinks(drinks))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음료의 이름이 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("판매하는 음료에 개수가 1보다 작다면 예외가 발생한다.")
    void invalidDrinks() {
        assertThatThrownBy(() -> new Drinks(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("판매하는 음료는 최소 1개 이상이어야 합니다.");
    }

    @Test
    @DisplayName("음료 중 최저 가격을 찾을 수 있다.")
    void findMinimumPrice() {
        Drinks drinks = new Drinks(Arrays.asList(
                new Drink("콜라", 1500, 20),
                new Drink("사이다", 1000, 10)));

        int result = drinks.findMinimumPrice();
        assertThat(result).isEqualTo(1000);
    }

    @ParameterizedTest
    @CsvSource(value = {"0, 1, false", "1, 0, false", "1, 1, false", "0, 0, true"})
    @DisplayName("음료가 전부 팔렸는지 알 수 있다.")
    void isSoldOut(int firstQuantity, int secondQuantity, boolean expected) {
        Drinks drinks = new Drinks(Arrays.asList(
                new Drink("콜라", 1500, firstQuantity),
                new Drink("사이다", 1000, secondQuantity)));

        boolean actual = drinks.isSoldOut();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"콜라, true", "사이다, true", "환타, false"})
    @DisplayName("상품들 중 해당 이름과 같은 음료를 포함하는지 알 수 있다.")
    void contain(String name, boolean expected) {
        Drinks drinks = new Drinks(Arrays.asList(
                new Drink("콜라", 1500, 10),
                new Drink("사이다", 1000, 20)));

        boolean actual = drinks.contain(name);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("이름을 통해 음료를 찾을 수 있다.")
    void findDrinkFrom() {
        Drinks drinks = new Drinks(Arrays.asList(
                new Drink("콜라", 1500, 10),
                new Drink("사이다", 1000, 20)));

        Drink drink = drinks.findDrinkFrom("콜라");
        assertThat(drink).isEqualTo(new Drink("콜라", 1500, 10));
    }
}