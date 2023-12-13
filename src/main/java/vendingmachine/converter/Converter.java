package vendingmachine.converter;

import vendingmachine.domain.Drinks;
import vendingmachine.domain.drink.Drink;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Converter {

    public static Drinks toDrinks(String readDrinks) {
        return Arrays.stream(readDrinks.split(";"))
                .map(Converter::substring)
                .map(Converter::generateDrinkFrom)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Drinks::new));
    }

    private static String substring(String readDrink) {
        return readDrink.substring(1, readDrink.length() - 1);
    }

    private static Drink generateDrinkFrom(String readDrink) {
        String[] input = readDrink.split(",");
        String name = input[0];
        int price = Integer.parseInt(input[1]);
        int quantity = Integer.parseInt(input[2]);

        return new Drink(name, price, quantity);
    }
}
