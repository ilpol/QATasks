package ru.stqa.cucumber.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.cucumber.pages.BasketPage;
import ru.stqa.cucumber.pages.MainPage;
import ru.stqa.cucumber.pages.ItemPage;

public class Application {
    private WebDriver driver;
    private MainPage mainPage;
    private ItemPage productPage;
    private BasketPage basketPage;

    public Application(){
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ItemPage(driver);
        basketPage = new BasketPage(driver);
    }

    public void quit(){
        driver.quit();
    }

    public void open(){
        mainPage.open();
    }

    public void add() {
        mainPage.open();
        productPage.openItem();
        productPage.add();
    }

    public void checkout(){
        mainPage.toBasket();
    }
    public void emptyBasket() throws InterruptedException {
        basketPage.removeItem();
    }

}
