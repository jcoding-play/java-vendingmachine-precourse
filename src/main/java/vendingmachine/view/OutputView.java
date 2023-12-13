package vendingmachine.view;

import vendingmachine.Coin;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEWLINE = System.lineSeparator();

    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] %s" + NEWLINE;
    private static final String PREFIX_MESSAGE_OF_COIN = NEWLINE + "자판기가 보유한 동전";
    private static final String PREFIX_MESSAGE_OF_CHANGE = "잔돈";
    private static final String COIN_MESSAGE_FORMAT = "%d원 - %d개";
    private static final String INPUT_AMOUNT_MESSAGE_FORMAT = NEWLINE + "투입 금액: %d원" + NEWLINE;
    private static final int FIRST_INDEX = 0;
    private static final int NOTHING_CHANGE_COUNT = 0;

    public void printErrorMessage(String errorMessage) {
        System.out.printf(ERROR_MESSAGE_FORMAT, errorMessage);
    }

    public void printCoins(Map<Coin, Integer> coins) {
        List<String> coinMessages = generateCoinMessages(coins);
        coinMessages.add(FIRST_INDEX, PREFIX_MESSAGE_OF_COIN);

        System.out.println(String.join(NEWLINE, coinMessages));
    }

    private List<String> generateCoinMessages(Map<Coin, Integer> coins) {
        return coins.keySet()
                .stream()
                .map(coin -> generateCoinMessage(coin, coins.get(coin)))
                .collect(Collectors.toList());
    }

    private String generateCoinMessage(Coin coin, int quantity) {
        return String.format(COIN_MESSAGE_FORMAT, coin.getAmount(), quantity);
    }

    public void printInputAmount(int inputAmount) {
        System.out.printf(INPUT_AMOUNT_MESSAGE_FORMAT, inputAmount);
    }

    public void printChanges(Map<Coin, Integer> changes) {
        List<String> changeMessages = generateChangeMessages(changes);
        changeMessages.add(FIRST_INDEX, PREFIX_MESSAGE_OF_CHANGE);

        System.out.println(String.join(NEWLINE, changeMessages));
    }

    private List<String> generateChangeMessages(Map<Coin, Integer> changes) {
        return changes.keySet()
                .stream()
                .filter(coin -> changes.get(coin) != NOTHING_CHANGE_COUNT)
                .map(coin -> generateCoinMessage(coin, changes.get(coin)))
                .collect(Collectors.toList());
    }
}
