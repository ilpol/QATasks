package ru.stqa.maven;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    public void Task14() {
        driver.get("http://localhost/litecart/admin/");
        WebElement login = driver.findElement(By.name("username"));
        login.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        driver.findElement(By.xpath("//a[@class='button']")).click();
        String mainWindow = driver.getWindowHandle();
        List<WebElement> links = driver.findElements(By.xpath("//form//tr//a[not(contains(@id,'address-format-hint'))]"));
        for (WebElement link : links) {
                Set<String> oldWindows = driver.getWindowHandles();
                link.click();
                Set<String> existingWindows = driver.getWindowHandles();

                //wait for new window to open
                long start = System.currentTimeMillis();
                int timeout = 300000;
                long end = start + timeout;
                boolean isOpen = true;
                while(oldWindows.size()==existingWindows.size()) {
                  existingWindows = driver.getWindowHandles();
                  if(System.currentTimeMillis() > end) {
                      isOpen = false;
                      break;
                  }
                }

                if (!isOpen) {
                    System.out.println("can not open window");
                    assert false;
                }

                existingWindows.remove(mainWindow);
                String newWindow = existingWindows.iterator().next();
                driver.switchTo().window(newWindow);
                //wait for page to load
                new WebDriverWait(driver, 20).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
                driver.close();
                driver.switchTo().window(mainWindow);
        }
    }
}

