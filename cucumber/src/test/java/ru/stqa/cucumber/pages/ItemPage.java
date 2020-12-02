package ru.stqa.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class ItemPage extends Page {
    public ItemPage(WebDriver driver){
        super(driver);
    }

    public void openItem(){
        driver.findElement(By.xpath("//div[@id='box-most-popular']//img")).click();
    }
    public void add(){

        List<WebElement> options = driver.findElements(By.xpath("//select[@name='options[Size]']//option"));

        if ( options.size() > 0) {
            options.get(1).click();

        }

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='add_cart_product']")));
        String oldCounter = driver.findElement(By.xpath("//span[@class='quantity']")).getText();
        driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//span[@class='quantity']"), oldCounter));
    }
}
