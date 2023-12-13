package vendingmachine.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
}