package ru.stqa.maven;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class AppTest 
{
    public WebDriver driver;

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
    public void Task9_1() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> countries = driver.findElements(By.xpath("//tr[@class='row']"));
        List<WebElement> countriesNamesWeb = driver.findElements(By.xpath("//tr[@class='row']/td[5]"));
        List<String> countriesNames = new ArrayList<>();
        for (WebElement country : countriesNamesWeb) {
            countriesNames.add(country.getAttribute("textContent"));
        }
        List<String> sortedCountriesNames = new ArrayList<>(countriesNames);

        Collections.sort(sortedCountriesNames);
        assertEquals("Список стран не в алфавитном порядке", sortedCountriesNames, countriesNames);

        List<WebElement> countriesWithTimeZones = new ArrayList<>();
        int curNumberZones;
        for (WebElement country : countries) {
            curNumberZones =  Integer.parseInt(country.findElement(By.xpath(".//td[6]")).getAttribute("textContent"));
            if (curNumberZones > 0) {
                countriesWithTimeZones.add(country);
            }
        }
        List<WebElement> timeZones;
        List<String> timeZonesText;
        List<String> linksToZones = new ArrayList<>();
        for (WebElement country : countriesWithTimeZones) {
            linksToZones.add(country.findElement(By.xpath(".//td[5]/a")).getAttribute("href"));
        }

        for (String linkToZones : linksToZones) {
            driver.get(linkToZones);
            timeZonesText = new ArrayList<>();
            timeZones = driver.findElements(By.xpath("//table[@class='dataTable']//tr[not(@class='header')]/td[3]"));
            for (WebElement timeZone : timeZones) {
                timeZonesText.add(timeZone.getAttribute("textContent"));
            }
            List<String> sortedtimeZonesText = new ArrayList<>(timeZonesText);
            Collections.sort(sortedCountriesNames);
            assertEquals("Список временных зон не в алфавитном порядке", sortedtimeZonesText, timeZonesText);
        }
    }

    @Test
    public void Task9_2() {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> countriesNamesWeb = driver.findElements(By.xpath("//tr[@class='row']/td[3]"));
        List<String> linksToZones = new ArrayList<>();

        for (WebElement country : countriesNamesWeb) {
            linksToZones.add(country.findElement(By.xpath(".//a")).getAttribute("href"));
        }

        List<WebElement> timeZones;
        List<String> timeZonesText;
        for (String linkToZones : linksToZones) {
            driver.get(linkToZones);
            timeZones = driver.findElements(By.xpath("//table[@class='dataTable']//tr[not(@class='header')]/td[3]"));
            timeZonesText = new ArrayList<>();
            for (WebElement timeZone : timeZones) {
                timeZonesText.add(timeZone.getAttribute("textContent"));
            }
            List<String> sortedtimeZonesText = new ArrayList<>(timeZonesText);
            Collections.sort(sortedtimeZonesText);
            assertEquals("Список временных зон не в алфавитном порядке", sortedtimeZonesText, timeZonesText);
        }
    }
}

