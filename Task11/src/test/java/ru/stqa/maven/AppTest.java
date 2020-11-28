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

import java.util.*;
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


    public String emailUnique() {
        Random nexChar = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder eMail = new StringBuilder();
        for (int i = 0; i <= 5; i++) {
            eMail.append(alphabet.charAt(nexChar.nextInt(alphabet.length())));
        }
        return eMail.toString() + "@bk.ru";
    }

    public String firstName;
    public String lastName;
    public String address;
    public String postcode;
    public String city;
    public String email = emailUnique();
    public String phone;
    public String password;

    @Test
    public void Task11() {

        firstName = "Имя";
        lastName = "Фамилия";
        address = "Адрес";
        postcode = "12345";
        city = "Город";
        email = emailUnique();
        phone = "3333333333";
        password = "123";

        //Registration
        driver.get("http://localhost/litecart/en/");
        WebElement loginLink = driver.findElement(By.xpath("//form[@name='login_form']//tr[5]"));
        loginLink.click();

        //wait for form to appear
        WebDriverWait wait = new WebDriverWait(driver,30);
        String firstNameXPath = "//input[@name='firstname']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(firstNameXPath)));

        driver.findElement(By.xpath(firstNameXPath)).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(address);
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys(postcode);
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
        driver.findElement(By.xpath("//select/option[@value='US']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(phone);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(password);
        driver.findElement((By.xpath("//button[@name='create_account']"))).click();

        // Leave
        driver.findElement(By.xpath("//aside[@id='navigation']//li[4]/a")).click();

        // Enter
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@name='login']")).click();

        // Leave
        driver.findElement(By.xpath("//aside[@id='navigation']//li[4]/a")).click();
    }
}

