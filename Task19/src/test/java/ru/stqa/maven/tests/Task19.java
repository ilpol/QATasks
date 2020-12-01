package ru.stqa.maven.tests;
import org.junit.Test;

public class Task19 extends TestBase {

    @Test
    public void BasketTest() throws InterruptedException {
        app.open();
        for (int i = 0; i < 3; i++){
            app.add();
        }
        app.checkout();
        app.emptyBasket();
        app.quit();
    }
}
