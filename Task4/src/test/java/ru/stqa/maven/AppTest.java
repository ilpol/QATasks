package ru.stqa.maven;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AppTest 
{
    public WebDriver driver;
    public WebDriver ieDriver;
    public WebDriver firefoxDriver;
    public WebDriverWait wait;
    public WebDriverWait waitFirefox;
    public WebDriverWait waitIE;

    @Before
    public void start() {
        driver = new ChromeDriver();
        ieDriver = new InternetExplorerDriver();
        firefoxDriver = new FirefoxDriver();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
        ieDriver.quit();
        ieDriver = null;
        firefoxDriver.quit();
        firefoxDriver = null;
    }

    @Test
    public void myFirstTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));


        /*firefoxDriver.get("http://localhost/litecart/admin/");
        firefoxDriver.findElement(By.name("username")).sendKeys("admin");
        firefoxDriver.findElement(By.name("password")).sendKeys("admin");
        firefoxDriver.findElement(By.name("login")).click();
        waitFirefox.until(titleIs("My Store"));


        ieDriver.get("http://localhost/litecart/admin/");
        ieDriver.findElement(By.name("username")).sendKeys("admin");
        ieDriver.findElement(By.name("password")).sendKeys("admin");
        ieDriver.findElement(By.name("login")).click();
        waitIE.until(titleIs("My Store"));*/
    }
}
