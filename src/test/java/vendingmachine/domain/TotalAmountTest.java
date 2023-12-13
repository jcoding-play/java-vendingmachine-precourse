package vendingmachine.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class TotalAmountTest {

    @Test
    @DisplayName("자판기가 보유하는 금액을 알 수 있다.")
    void createTotalAmount() {
        TotalAmount totalAmount = new TotalAmount(100);
        assertThat(totalAmount).isEqualTo(new TotalAmount(100));
    }

    @Test
    @DisplayName("자판기가 보유한 금액이 0원보다 작다면 예외가 발생한다.")
    void isLessThanMinimumAmount() {
        assertThatThrownBy(() -> new TotalAmount(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자판기가 보유한 금액이 0원보다 작을 수 없습니다.");
    }

    @Test
    @DisplayName("금액이 10원 단위로 나뉘어 떨어지지 않으면 예외가 발생한다.")
    void invalidTotalAmount() {
        assertThatThrownBy(() -> new TotalAmount(111))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자판기가 보유한 금액은 10원 단위어야 합니다.");
    }
}