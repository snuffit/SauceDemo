package dto;

import com.github.javafaker.Faker;

public class CheckoutFactory {

    public static Checkout getCheckout() {
        Faker faker = new Faker();
        return new Checkout(faker.name().firstName(), faker.name().lastName(), faker.address().zipCode());
    }
}
