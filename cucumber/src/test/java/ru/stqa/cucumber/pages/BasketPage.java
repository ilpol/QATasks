package ru.stqa.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class BasketPage extends Page {
    public BasketPage(WebDriver driver){
        super(driver);
    }

    public void removeItem() throws InterruptedException {
        String removeButtonXPath = "//button[@value='Remove']";
        List<WebElement> carts = driver.findElements(By.xpath("//ul[@class='shortcuts']//a"));
        if (!carts.isEmpty()) {
            carts.get(0).click();
        }
        for (int i = 1; i <= carts.size(); i++){
            if (driver.findElements(By.xpath(removeButtonXPath)).size() > 0) {
                WebElement removeButton = driver.findElement(By.xpath(removeButtonXPath));
                wait.until(ExpectedConditions.elementToBeClickable(removeButton));
                WebElement table = driver.findElement(By.xpath("//div[@id='box-checkout-summary']"));
                Thread.sleep(1000);
                removeButton.click();
                wait.until(ExpectedConditions.stalenessOf(table));
            }
            else {
                break;
            }
        }
    }
}
