package ru.stqa.maven;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    public void Task17() {
        driver.get("http://localhost/litecart/admin/");
        WebElement login = driver.findElement(By.name("username"));
        login.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        List<WebElement> pages = driver.findElements(By.xpath("//form[@name='catalog_form']//tr//td[3]//a"));
        List<String> links = new ArrayList();
        for (WebElement page : pages) {
            links.add(page.getAttribute(("href")));
        }
        for (String link : links){
            driver.navigate().to(link);
            List <LogEntry> logs = driver.manage().logs().get("browser").getAll();
            if (logs.size() != 0){
                System.out.println("Логи на " + driver.getCurrentUrl());
                assert false;
            }
        }
    }
}

