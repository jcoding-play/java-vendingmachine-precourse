package vendingmachine.domain.drink;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NameTest {

    @Test
    @DisplayName("음료 상품명에 대한 정보를 알 수 있다.")
    void createName() {
        Name name = new Name("abcdefghij");
        assertThat(name).isEqualTo(new Name("abcdefghij"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "abcdefghijk"})
    @DisplayName("상품명이 공백이거나 10자를 초과하면 예외가 발생한다.")
    void invalidName(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상품명이 공백이거나 10자를 초과할 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"king, true", "queen, false"})
    @DisplayName("이름이 일치하는지 여부를 알 수 있다.")
    void isSameName(String name, boolean expected) {
        Name king = new Name("king");
        boolean actual = king.isSameName(name);

        assertThat(actual).isEqualTo(expected);
    }
}