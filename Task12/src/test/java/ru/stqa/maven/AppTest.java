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

import java.io.File;
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
    public void Task12() throws InterruptedException {

        driver.get("http://localhost/litecart/admin/");
        WebElement login = driver.findElement(By.name("username"));
        login.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();

        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='box-apps-menu-wrapper']//li[2][@id='app-']")).click();
        driver.findElement(By.xpath("//td[@id='content']//a[2]")).click();

        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='tab-general']//tr[1]//input[@value='1']")).click();
        String productName = "Новый продукт";
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys(productName);
        driver.findElement(By.xpath("//strong[text()='Product Groups']/following-sibling::div//tr[2]")).click();
        WebElement quantity = driver.findElement(By.xpath("//input[@name='quantity']"));
        quantity.clear();
        quantity.sendKeys("1");
        File productPic = new File("item_img.png");
        String productPicPath = productPic.getAbsolutePath();
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(productPicPath);
        driver.findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys("29-11-2020");
        driver.findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys("29-11-2021");

        driver.findElement(By.xpath("//div[@class='tabs']//li[2]")).click();
        driver.findElement(By.xpath("//div[@id='tab-information']//select[@name='manufacturer_id']//option[@value='1']")).click();
        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("ключевое слово");
        driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys("Описание");
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys("Описание");
        driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys("Заголовок");
        driver.findElement(By.xpath("//input[@name='meta_description[en]']")).sendKeys("Мета");

        driver.findElement(By.xpath("//div[@class='tabs']//li[4]")).click();
        WebElement pricePurchase = driver.findElement(By.xpath("//input[@name='purchase_price']"));
        pricePurchase.clear();
        pricePurchase.sendKeys("10");
        driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']//option[@value='USD']")).click();
        WebElement price = driver.findElement(By.xpath("//input[@name='gross_prices[USD]']"));
        price.clear();
        price.sendKeys("10");
        driver.findElement(By.xpath("//button[@name='save']")).click();

        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='" + productName + "']")));
    }
}

