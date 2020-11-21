package ru.stqa.maven;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AppTest 
{
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    @Test
    public void myFirstTest() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> links = driver.findElements(By.xpath("//li[@id='app-']"));
        for (int i=0; i<links.size(); i++) {
            //scroll page in order for element to be clickable
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("scroll(0, 250)");

            links.get(i).click();
            

            //update
            links = driver.findElements(By.xpath("//li[@id='app-']"));

            List<WebElement> subLinks = (links.get(i)).findElements(By.cssSelector("li"));
            for (int j=0; j< subLinks.size(); j++) {
                subLinks.get(j).click();
                driver.findElement(By.xpath("//h1"));

                //update
                links = driver.findElements(By.xpath("//li[@id='app-']"));
                subLinks = (links.get(i)).findElements(By.cssSelector("li"));
            }

        }
    }
}
