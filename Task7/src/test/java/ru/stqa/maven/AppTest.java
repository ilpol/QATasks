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

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

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
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> links = driver.findElements(By.xpath("//li[@id='app-']"));
        System.out.println("links.size()=  " + links.size());
        for (int i=links.size() - 1; i>=0; i--) {
            List<WebElement> linksUpdate = driver.findElements(By.xpath("//li[@id='app-']"));
            linksUpdate.get(i).click();
            /*List<WebElement> subLinks = linksUpdate.get(i).findElements(By.xpath(".//li"));
            for (int j=0; j< subLinks.size(); j++) {
                System.out.println("subLinks.size() = " + subLinks.size());
                System.out.println("before j = " + j);
                subLinks.get(j).click();
                System.out.println("after j = " + j);
            }*/
        }
        //wait.until(titleIs("My Store"));
    }
}
