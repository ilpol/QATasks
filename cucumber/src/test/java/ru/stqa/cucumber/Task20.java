package ru.stqa.cucumber;

import io.cucumber.java8.En;
import ru.stqa.cucumber.app.Application;
import org.junit.Assert;

public class Task20 implements En {
    public Application app;
    public Task20() {
        When("we open shop", () -> {
            app = new Application();
            app.open();
        });
        And("add items in basket", () -> {
            for (int i = 0; i < 3; i++) {
                app.add();
            }
        });
        And("checkout basket", () -> {
            app.checkout();
        });
        Then("we empty basket",() -> {
            app.emptyBasket();
            app.quit();
        });
    }
}
