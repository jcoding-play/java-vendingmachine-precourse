package vendingmachine.domain;

import vendingmachine.domain.drink.Drink;

public class VendingMachine {
    private final InputAmount inputAmount;
    private final Drinks drinks;

    public VendingMachine(InputAmount inputAmount, Drinks drinks) {
        this.inputAmount = inputAmount;
        this.drinks = drinks;
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
}
