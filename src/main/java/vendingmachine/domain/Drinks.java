package vendingmachine.domain;

import vendingmachine.domain.drink.Drink;

import java.util.List;
import java.util.Objects;

public class Drinks {
    private static final int MINIMUM_DRINKS_SIZE = 1;

    private final List<Drink> drinks;

    public Drinks(List<Drink> drinks) {
        validateDrinks(drinks);
        this.drinks = drinks;
    }

    private void validateDrinks(List<Drink> drinks) {
        if (hasDuplicatedDrink(drinks)) {
            throw new IllegalArgumentException("음료의 이름이 중복될 수 없습니다.");
        }
        if (drinks.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("판매하는 음료는 최소 %d개 이상이어야 합니다.", MINIMUM_DRINKS_SIZE));
        }
    }

    private boolean hasDuplicatedDrink(List<Drink> drinks) {
        return drinks.stream()
                .map(Drink::getName)
                .distinct()
                .count() != drinks.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drinks drinks1 = (Drinks) o;
        return Objects.equals(drinks, drinks1.drinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drinks);
    }
}