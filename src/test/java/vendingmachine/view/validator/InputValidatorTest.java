package vendingmachine.view.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class InputValidatorTest {
    private InputValidator inputValidator;

    @BeforeEach
    void setUp() {
        inputValidator = new InputValidator();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("입력이 null 이거나 공백이면 예외가 발생한다.")
    void validateInput(String input) {
        assertThatThrownBy(() -> inputValidator.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력은 공백일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "!", "12A"})
    @DisplayName("자판기가 보유하고 있는 금액에 대한 입력이 숫자가 아니면 예외가 발생한다.")
    void validateTotalAmount(String input) {
        assertThatThrownBy(() -> inputValidator.validateTotalAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자판기가 보유하고 있는 금액에 대한 입력은 숫자만 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"[콜라,a,20];[사이다,1000,10]", "[콜라,1500,20]-[사이다,1000,10]", "[콜라,1500,20];[사이다,1000,!]"})
    @DisplayName("상품명과 가격, 수량 대한 입력 형식이 올바르지 않으면 예외가 발생한다.")
    void validateFormat(String input) {
        assertThatThrownBy(() -> inputValidator.validateDrinks(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상품명과 가격, 수량에 대한 입력 형식이 올바르지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "!", "12A"})
    @DisplayName("투입 금액에 대한 입력이 숫자가 아니면 예외가 발생한다.")
    void validateInputAmount(String input) {
        assertThatThrownBy(() -> inputValidator.validateInputAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("투입 금액에 대한 입력은 숫자만 가능합니다.");
    }
}