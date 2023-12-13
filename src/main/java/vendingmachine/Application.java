package vendingmachine;

import vendingmachine.controller.MainController;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Application {

    public static void main(String[] args) {
        ApplicationConfiguration configuration = new ApplicationConfiguration();
        InputView inputView = configuration.inputView();
        OutputView outputView = configuration.outputView();

        MainController mainController = new MainController(inputView, outputView);
        mainController.run();
    }
}
