package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.view.validator.InputValidator;

public class InputView {
    private static final String READ_TOTAL_AMOUNT_MESSAGE = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String READ_DRINKS_MESSAGE = "상품명과 가격, 수량을 입력해 주세요.";
    private static final String READ_INPUT_AMOUNT_MESSAGE = "투입 금액을 입력해 주세요.";
    private static final String READ_DRINK_NAME_MESSAGE = "구매할 상품명을 입력해 주세요.";

    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public int readTotalAmount() {
        System.out.println(READ_TOTAL_AMOUNT_MESSAGE);
        String input = Console.readLine();

        inputValidator.validateTotalAmount(input);
        return Integer.parseInt(input);
    }

    public String readDrinks() {
        System.out.println(READ_DRINKS_MESSAGE);
        String input = Console.readLine();

        inputValidator.validateDrinks(input);
        return input;
    }

    public int readInputAmount() {
        System.out.println(READ_INPUT_AMOUNT_MESSAGE);
        String input = Console.readLine();

        inputValidator.validateInputAmount(input);
        return Integer.parseInt(input);
    }

    public String readDrinkName() {
        System.out.println(READ_DRINK_NAME_MESSAGE);
        String input = Console.readLine();

        inputValidator.validateInput(input);
        return input;
    }
}
