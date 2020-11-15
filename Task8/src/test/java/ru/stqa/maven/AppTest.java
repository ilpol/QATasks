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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppTest 
{
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 1000);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    @Test
    public void myFirstTest() throws InterruptedException {
        driver.get("http://localhost/litecart/");
        
        List<WebElement> carts = driver.findElements(By.xpath("//li[contains(@class,'product')]"));
        System.out.println("links.size()=  " + carts.size());
        for (int i=0; i<carts.size() ; i++) {
            List<WebElement> stickers = carts.get(i).findElements(By.xpath(".//div[contains(@class,'sticker')]"));
            if (stickers.isEmpty()){
                System.out.println("no sticker");
            }
        }
    }
}
