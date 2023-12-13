package vendingmachine.controller;

import vendingmachine.Coin;
import vendingmachine.domain.*;
import vendingmachine.domain.drink.Drink;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int readTotalAmount = inputView.readTotalAmount();
        TotalAmount totalAmount = new TotalAmount(readTotalAmount);

        CoinGenerator coinGenerator = new RandomCoinGenerator();
        Map<Coin, Integer> coins = coinGenerator.generate(totalAmount);
        outputView.printCoins(coins);
        CoinRepository coinRepository = new CoinRepository(coins);

        String readDrinks = inputView.readDrinks();
        Drinks drinks = toDrinks(readDrinks);

        int readInputAmount = inputView.readInputAmount();
        InputAmount inputAmount = new InputAmount(readInputAmount);

        VendingMachine vendingMachine = new VendingMachine(coinRepository, drinks, inputAmount);

        while (vendingMachine.canBuyDrink()) {
            outputView.printInputAmount(vendingMachine.getInputAmount());
            String drinkName = inputView.readDrinkName();
            vendingMachine.buyDrink(drinkName);
        }

        outputView.printInputAmount(vendingMachine.getInputAmount());
        Map<Coin, Integer> changes = vendingMachine.giveChange();
        outputView.printChanges(changes);
    }

    private Drinks toDrinks(String readDrinks) {
        return Arrays.stream(readDrinks.split(";"))
                .map(this::substring)
                .map(this::generateDrinkFrom)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Drinks::new));
    }

    private String substring(String readDrink) {
        return readDrink.substring(1, readDrink.length() - 1);
    }

    private Drink generateDrinkFrom(String readDrink) {
        String[] input = readDrink.split(",");
        String name = input[0];
        int price = Integer.parseInt(input[1]);
        int quantity = Integer.parseInt(input[2]);

        return new Drink(name, price, quantity);
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
