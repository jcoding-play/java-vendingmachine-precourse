package vendingmachine;

import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;
import vendingmachine.view.validator.InputValidator;

public class ApplicationConfiguration {

    public InputView inputView() {
        return new InputView(inputValidator());
    }

    private InputValidator inputValidator() {
        return new InputValidator();
    }

    public OutputView outputView() {
        return new OutputView();
    }
}
