package vendingmachine.domain.drink;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PriceTest {

    @Test
    @DisplayName("음료 가격에 대한 정보를 알 수 있다.")
    void createPrice() {
        Price price = new Price(100);
        assertThat(price).isEqualTo(new Price(100));
    }

    @Test
    @DisplayName("상품 가격이 100원보다 작다면 예외가 발생한다.")
    void isLessThanMinimumPrice() {
        assertThatThrownBy(() -> new Price(90))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상품의 가격은 최소 100원 이상이어야 합니다.");
    }

    @Test
    @DisplayName("상품 가격이 10원으로 나누어떨어지지 않으면 예외가 발생한다.")
    void invalidPrice() {
        assertThatThrownBy(() -> new Price(111))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상품 가격은 10원 단위어야 합니다.");
    }
}