package vendingmachine.view;

public class OutputView {
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] %s\n";

    public void printErrorMessage(String errorMessage) {
        System.out.printf(ERROR_MESSAGE_FORMAT, errorMessage);
    }
}
