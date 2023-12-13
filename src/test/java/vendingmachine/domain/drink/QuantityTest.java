package vendingmachine.domain.drink;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class QuantityTest {

    @Test
    @DisplayName("음료 수량에 대한 정보를 알 수 있다.")
    void createQuantity() {
        Quantity quantity = new Quantity(0);
        assertThat(quantity).isEqualTo(new Quantity(0));
    }

    @Test
    @DisplayName("상품 수량이 0보다 작다면 예외가 발생한다.")
    void invalidQuantity() {
        assertThatThrownBy(() -> new Quantity(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상품 수량이 0보다 작을 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1, false", "0, true"})
    @DisplayName("수량이 0인지 알 수 있다.")
    void isZero(int number, boolean expected) {
        Quantity quantity = new Quantity(number);
        boolean actual = quantity.isZero();

        assertThat(actual).isEqualTo(expected);
    }
}