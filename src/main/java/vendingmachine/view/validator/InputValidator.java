package vendingmachine.view.validator;

import org.junit.platform.commons.util.StringUtils;

import java.util.regex.Pattern;

public class InputValidator {
    private static final String INPUT_BLANK_EXCEPTION_MESSAGE = "입력은 공백일 수 없습니다.";
    private static final String NOT_DIGIT_EXCEPTION_MESSAGE = "자판기가 보유하고 있는 금액에 대한 입력은 숫자만 가능합니다.";
    private static final Pattern ONLY_DIGIT = Pattern.compile("-?[0-9]+");

    protected void validateInput(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException(INPUT_BLANK_EXCEPTION_MESSAGE);
        }
    }

    public void validateTotalAmount(String input) {
        validateInput(input);

        if (isNotDigit(input)) {
            throw new IllegalArgumentException(NOT_DIGIT_EXCEPTION_MESSAGE);
        }
    }

    private boolean isNotDigit(String input) {
        return !ONLY_DIGIT.matcher(input)
                .matches();
    }
}
