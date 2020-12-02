package ru.stqa.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends Page {
    public MainPage(WebDriver driver){
        super(driver);
    }

    public void open(){
        driver.get("http://localhost/litecart/en/");
    }
    public void toBasket(){
        String checkoutXPath = "//div[@id='cart']//a[@class='link']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(checkoutXPath)));
        driver.findElement(By.xpath(checkoutXPath)).click();
    }
}
