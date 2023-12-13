package vendingmachine.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.Drinks;
import vendingmachine.domain.drink.Drink;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class ConverterTest {

    @Test
    @DisplayName("입력된 정보를 통해 음료들을 얻을 수 있다.")
    void toDrinks() {
        Drinks drinks = Converter.toDrinks("[콜라,1500,20];[사이다,1000,10]");
        assertThat(drinks).isEqualTo(
                new Drinks(Arrays.asList(
                        new Drink("콜라", 1500, 20),
                        new Drink("사이다", 1000, 10))
                )
        );
    }
}