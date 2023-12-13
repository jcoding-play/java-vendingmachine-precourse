package vendingmachine.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class InputAmountTest {

    @Test
    @DisplayName("사용자가 투입한 금액을 알 수 있다.")
    void createInputAmount() {
        InputAmount inputAmount = new InputAmount(0);
        assertThat(inputAmount).isEqualTo(new InputAmount(0));
    }

    @Test
    @DisplayName("투입 금액이 0원보다 작다면 예외가 발생한다.")
    void isLessThanMinimumAmount() {
        assertThatThrownBy(() -> new InputAmount(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("투입 금액이 0원보다 작을 수 없습니다.");
    }

    @Test
    @DisplayName("투입 금액이 10원 단위로 나뉘어 떨어지지 않으면 예외가 발생한다.")
    void invalidInputAmount() {
        assertThatThrownBy(() -> new InputAmount(111))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("투입 금액은 10원 단위어야 합니다.");
    }
}