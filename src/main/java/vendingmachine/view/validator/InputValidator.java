package vendingmachine.view.validator;

import org.junit.platform.commons.util.StringUtils;

import java.util.regex.Pattern;

public class InputValidator {
    private static final String ABOUT_INPUT_AMOUNT_MESSAGE = "투입 금액에 대한 ";
    private static final String ABOUT_TOTAL_AMOUNT_MESSAGE = "자판기가 보유하고 있는 금액에 대한 ";
    private static final String INPUT_BLANK_EXCEPTION_MESSAGE = "입력은 공백일 수 없습니다.";
    private static final String NOT_DIGIT_EXCEPTION_MESSAGE = "입력은 숫자만 가능합니다.";
    private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "상품명과 가격, 수량에 대한 입력 형식이 올바르지 않습니다.";

    private static final Pattern VALID_FORMAT = Pattern.compile("\\[.+,-?[0-9]+,-?[0-9]+](;\\[.+,-?[0-9]+,-?[0-9]+])");
    private static final Pattern ONLY_DIGIT = Pattern.compile("-?[0-9]+");

    protected void validateInput(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException(INPUT_BLANK_EXCEPTION_MESSAGE);
        }
    }

    public void validateTotalAmount(String input) {
        validateInput(input);

        if (isNotDigit(input)) {
            throw new IllegalArgumentException(ABOUT_TOTAL_AMOUNT_MESSAGE + NOT_DIGIT_EXCEPTION_MESSAGE);
        }
    }

    private boolean isNotDigit(String input) {
        return !ONLY_DIGIT.matcher(input)
                .matches();
    }

    public void validateDrinks(String input) {
        validateInput(input);

        if (isInvalidInputFormat(input)) {
            throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private boolean isInvalidInputFormat(String input) {
        return !VALID_FORMAT.matcher(input)
                .matches();
    }

    public void validateInputAmount(String input) {
        validateInput(input);

        if (isNotDigit(input)) {
            throw new IllegalArgumentException(ABOUT_INPUT_AMOUNT_MESSAGE + NOT_DIGIT_EXCEPTION_MESSAGE);
        }
    }
}
