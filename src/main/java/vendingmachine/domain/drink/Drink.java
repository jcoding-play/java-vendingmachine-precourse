package vendingmachine.domain.drink;

import java.util.Objects;

public class Drink {
    private Name name;
    private Price price;
    private Quantity quantity;

    public Drink(String name, int price, int quantity) {
        this.name = new Name(name);
        this.price = new Price(price);
        this.quantity = new Quantity(quantity);
    }

    public Name getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return Objects.equals(name, drink.name) && Objects.equals(price, drink.price) && Objects.equals(quantity, drink.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity);
    }
}
