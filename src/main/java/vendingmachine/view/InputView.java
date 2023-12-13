package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.view.validator.InputValidator;

public class InputView {
    private static final String READ_TOTAL_AMOUNT_MESSAGE = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String READ_DRINKS_MESSAGE = "상품명과 가격, 수량을 입력해 주세요.";

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
}
