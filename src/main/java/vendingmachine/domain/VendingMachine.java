package vendingmachine.domain;

import vendingmachine.Coin;
import vendingmachine.domain.drink.Drink;

import java.util.Map;

public class VendingMachine {
    private final CoinRepository coinRepository;
    private final Drinks drinks;
    private final InputAmount inputAmount;

    public VendingMachine(CoinRepository coinRepository, Drinks drinks, InputAmount inputAmount) {
        this.coinRepository = coinRepository;
        this.drinks = drinks;
        this.inputAmount = inputAmount;
    }

    public void buyDrink(String drinkName) {
        validateDrinkName(drinkName);

        Drink drink = drinks.findDrinkFrom(drinkName);
        sell(drink);
    }

    private void validateDrinkName(String drinkName) {
        if (isNotInVendingMachine(drinkName)) {
            throw new IllegalArgumentException("구매하려는 상품명은 자판기에 없는 상품입니다.");
        }
    }

    private boolean isNotInVendingMachine(String drinkName) {
        return drinks.contain(drinkName);
    }

    private void sell(Drink drink) {
        inputAmount.subtractBy(drink.getPrice());
        drink.decreaseQuantity();
    }

    public boolean canBuyDrink() {
        int minimumPrice = drinks.findMinimumPrice();

        return !(inputAmount.isLessThan(minimumPrice) || drinks.isSoldOut());
    }

    public Map<Coin, Integer> giveChange() {
        return coinRepository.giveChangeOf(inputAmount);
    }

    public int getInputAmount() {
        return inputAmount.get();
    }
}
