package ru.stqa.maven;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AppTest 
{
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
    public void Task10() {

        //Каталог

        driver.get("http://localhost/litecart/en/");

        WebElement compaignsFirst = driver.findElement(By.xpath("//div[@id='box-campaigns']//a"));
        WebElement regularPriceCatalog = compaignsFirst.findElement(By.xpath(".//s[@class='regular-price']"));
        String regularPriceCatalogText = regularPriceCatalog.getAttribute("textContent");
        String regularPriceFontSize = regularPriceCatalog.getCssValue("font-size");
        float regularPriceFontSizeFloat = Float.parseFloat(regularPriceFontSize.replaceAll("[^\\d.]", ""));
        String regularPriceTextDecoration  = regularPriceCatalog.getCssValue("text-decoration-line");

        assertEquals("Основная цена в каталоге не зачеркнута", regularPriceTextDecoration, "line-through");

        String regularPriceColor  = regularPriceCatalog.getCssValue("color");
        String regularPriceColorPreParsed = regularPriceColor.replaceAll("[^\\d., ]", "");
        List<String> regularPriceColorParsed = Arrays.asList((regularPriceColorPreParsed.split("[\\s,]+")));
                boolean isRegularPriceColorGray = regularPriceColorParsed.get(0).equals(regularPriceColorParsed.get(1))
                      && regularPriceColorParsed.get(1).equals(regularPriceColorParsed.get(2)) ? true : false;

        assertTrue("Основная цена не серая в каталоге",isRegularPriceColorGray);

        WebElement compaignPriceCatalog = compaignsFirst.findElement(By.xpath(".//strong[@class='campaign-price']"));
        String compaignPriceCatalogText = compaignPriceCatalog.getAttribute("textContent");
        String compaignPriceColor  = compaignPriceCatalog.getCssValue("color");
        String compaignPriceColorPreParsed = compaignPriceColor.replaceAll("[^\\d., ]", "");
        List<String> compaignPriceColorParsed = Arrays.asList((compaignPriceColorPreParsed.split("[\\s,]+")));
        boolean isCompaignPriceColorRed = Integer.parseInt(compaignPriceColorParsed.get(1)) == 0
                && Integer.parseInt(compaignPriceColorParsed.get(2)) == 0 ? true : false;

        assertTrue("Акционная цена не красная в каталоге",isCompaignPriceColorRed);

        String compaignPriceFontSize = compaignPriceCatalog.getCssValue("font-size");
        float compaignPriceFontSizeFloat = Float.parseFloat(compaignPriceFontSize.replaceAll("[^\\d.]", ""));
        boolean isCompaignBigger = compaignPriceFontSizeFloat > regularPriceFontSizeFloat ? true : false;

        assertTrue("Акционная цена не крупнее обычной в каталоге",isCompaignBigger);

        String compaignPriceFontWeight = compaignPriceCatalog.getCssValue("font-weight");
        boolean iscompaignPriceBold = Integer.parseInt(compaignPriceFontWeight) > 600 ? true : false;
        assertTrue("Акционная цена в каталоге нежирная",iscompaignPriceBold);


        WebElement namePriceCatalog = compaignsFirst.findElement(By.xpath(".//div[@class='name']"));
        String namePriceCatalogText = namePriceCatalog.getAttribute("textContent");

        //Карточка

        compaignsFirst.click();

        WebElement regularPriceCart = driver.findElement(By.xpath("//s[@class='regular-price']"));
        String regularPriceCartText = regularPriceCart.getAttribute("textContent");
        String regularPriceColorCart  = regularPriceCart.getCssValue("color");
        String regularPriceColorCartPreParsed = regularPriceColorCart.replaceAll("[^\\d., ]", "");
        List<String> regularPriceColorCartParsed = Arrays.asList((regularPriceColorCartPreParsed.split("[\\s,]+")));
        boolean isRegularPriceCartColorGray = regularPriceColorCartParsed.get(0).equals(regularPriceColorCartParsed.get(1))
                && regularPriceColorCartParsed.get(1).equals(regularPriceColorCartParsed.get(2)) ? true : false;

        assertTrue("Основная цена не серая в карточке",isRegularPriceCartColorGray);

        String regularPriceFontSizeCart = regularPriceCart.getCssValue("font-size");
        float regularPriceFontSizeCartFloat = Float.parseFloat(regularPriceFontSizeCart.replaceAll("[^\\d.]", ""));
        String regularPriceTextDecorationCart  = regularPriceCart.getCssValue("text-decoration-line");

        assertEquals("Основная цена в карточке не зачеркнута", regularPriceTextDecorationCart, "line-through");

        WebElement compaignPriceCart = driver.findElement(By.xpath("//strong[@class='campaign-price']"));
        String compaignPriceCartText = compaignPriceCart.getAttribute("textContent");
        String compaignPriceColorCart  = compaignPriceCart.getCssValue("color");
        String compaignPriceColorCartPreParsed = compaignPriceColorCart.replaceAll("[^\\d., ]", "");
        List<String> compaignPriceColorCartParsed = Arrays.asList((compaignPriceColorCartPreParsed.split("[\\s,]+")));
        boolean isCompaignPriceColorCartRed = Integer.parseInt(compaignPriceColorCartParsed.get(1)) == 0
                && Integer.parseInt(compaignPriceColorCartParsed.get(2)) == 0 ? true : false;

        assertTrue("Акционная цена не красная в карточке",isCompaignPriceColorCartRed);

        String compaignPriceFontSizeCart = compaignPriceCart.getCssValue("font-size");
        float compaignPriceFontSizeCartFloat = Float.parseFloat(compaignPriceFontSizeCart.replaceAll("[^\\d.]", ""));
        boolean isCompaignBiggerCart = compaignPriceFontSizeCartFloat > regularPriceFontSizeCartFloat ? true : false;

        assertTrue("Акционная цена не крупнее обычной в карточке",isCompaignBiggerCart);

        String compaignPriceFontWeightCart = compaignPriceCart.getCssValue("font-weight");
        boolean iscompaignPriceBoldCart = Integer.parseInt(compaignPriceFontWeightCart) > 600 ? true : false;

        assertTrue("Акционная цена в карточке нежирная",iscompaignPriceBoldCart);

        WebElement namePriceCart = driver.findElement(By.xpath("//h1[@itemprop='name']"));
        String namePriceCartText = namePriceCart.getAttribute("textContent");

        assertEquals("Заголовки в каталоге и карточке неравны", namePriceCatalogText, namePriceCartText);
        assertEquals("Обычные цены в каталоге и карточке неравны", regularPriceCatalogText, regularPriceCartText);
        assertEquals("Акционные цены в каталоге и карточке неравны", compaignPriceCatalogText, compaignPriceCartText);
    }
}

