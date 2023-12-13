package vendingmachine.converter;

import vendingmachine.domain.Drinks;
import vendingmachine.domain.drink.Drink;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Converter {
    private static final String DRINKS_DELIMITER = ";";
    private static final String DRINK_DELIMITER = ",";
    private static final int BEGIN_INDEX = 1;
    private static final int TO_MAKE_END_INDEX_VALUE = 1;
    private static final int NAME_INDEX = 0;
    private static final int PRICE_INDEX = 1;
    private static final int QUANTITY_INDEX = 2;

    public static Drinks toDrinks(String readDrinks) {
        return Arrays.stream(readDrinks.split(DRINKS_DELIMITER))
                .map(Converter::substring)
                .map(Converter::generateDrinkFrom)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Drinks::new));
    }

    private static String substring(String readDrink) {
        return readDrink.substring(BEGIN_INDEX, readDrink.length() - TO_MAKE_END_INDEX_VALUE);
    }

    private static Drink generateDrinkFrom(String readDrink) {
        String[] input = readDrink.split(DRINK_DELIMITER);
        String name = input[NAME_INDEX];
        int price = Integer.parseInt(input[PRICE_INDEX]);
        int quantity = Integer.parseInt(input[QUANTITY_INDEX]);

        return new Drink(name, price, quantity);
    }
}
