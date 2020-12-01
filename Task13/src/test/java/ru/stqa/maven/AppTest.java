package ru.stqa.maven;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class AppTest {
    public WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        //driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    @Test
    public void Task13() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        WebDriverWait wait = new WebDriverWait(driver,10);

        for (int i = 1; i <= 3; i++){
            driver.findElement(By.xpath("//div[@id='box-most-popular']//img")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='add_cart_product']")));
            String oldCounter = driver.findElement(By.xpath("//span[@class='quantity']")).getText();
            driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
            wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//span[@class='quantity']"), oldCounter));
            driver.get("http://localhost/litecart/en/");
        }

        String checkoutXPath = "//div[@id='cart']//a[@class='link']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(checkoutXPath)));
        driver.findElement(By.xpath(checkoutXPath)).click();
        String removeButtonXPath = "//button[@value='Remove']";
        for (int i = 1; i <= 3; i++){
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

