package vendingmachine.controller;

import vendingmachine.Coin;
import vendingmachine.converter.Converter;
import vendingmachine.domain.*;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

import java.util.Map;
import java.util.function.Supplier;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Map<Coin, Integer> coins = repeatTemplate(this::generateCoins);
        outputView.printCoins(coins);

        VendingMachine vendingMachine = initializeVendingMachine(coins);
        buyDrinks(vendingMachine);
        showChanges(vendingMachine);
    }

    private Map<Coin, Integer> generateCoins() {
        TotalAmount totalAmount = readTotalAmount();

        CoinGenerator coinGenerator = new RandomCoinGenerator();
        return coinGenerator.generate(totalAmount);
    }

    private TotalAmount readTotalAmount() {
        int readTotalAmount = inputView.readTotalAmount();
        return new TotalAmount(readTotalAmount);
    }

    private VendingMachine initializeVendingMachine(Map<Coin, Integer> coins) {
        CoinRepository coinRepository = new CoinRepository(coins);
        Drinks drinks = repeatTemplate(this::readDrinks);
        InputAmount inputAmount = repeatTemplate(this::readInputAmount);

        return new VendingMachine(coinRepository, drinks, inputAmount);
    }

    private Drinks readDrinks() {
        String readDrinks = inputView.readDrinks();
        return Converter.toDrinks(readDrinks);
    }

    private InputAmount readInputAmount() {
        int readInputAmount = inputView.readInputAmount();
        return new InputAmount(readInputAmount);
    }

    private void buyDrinks(VendingMachine vendingMachine) {
        while (vendingMachine.canBuyDrink()) {
            showInputAmount(vendingMachine);
            buyDrink(vendingMachine);
        }
    }

    private void showInputAmount(VendingMachine vendingMachine) {
        outputView.printInputAmount(vendingMachine.getInputAmount());
    }

    private void buyDrink(VendingMachine vendingMachine) {
        try {
            vendingMachine.buyDrink(inputView.readDrinkName());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            buyDrink(vendingMachine);
        }
    }

    private void showChanges(VendingMachine vendingMachine) {
        showInputAmount(vendingMachine);

        Map<Coin, Integer> changes = vendingMachine.giveChange();
        outputView.printChanges(changes);
    }

    private <T> T repeatTemplate(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return repeatTemplate(inputReader);
        }
    }
}
