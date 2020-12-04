package ru.stqa.cucumber;

import io.cucumber.java8.En;
import org.junit.After;
import org.junit.Before;
import ru.stqa.cucumber.app.Application;
import org.junit.Assert;

public class Task20 implements En {
    public Application app;
    @Before
    public void openBrowser(){
        app.open();
    }
    {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {app.quit(); app = null;}));
    }
    public Task20() {
        When("we open shop", () -> {
            app = new Application();
        });
        And("add {int} items in basket", (Integer nItems) -> {
            for (int i = 0; i < nItems; i++) {
                app.add();
            }
        });
        And("checkout basket", () -> {
            app.checkout();
        });
        Then("we empty basket",() -> {
            app.emptyBasket();
        });
    }
}
