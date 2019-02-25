package com.lk.automation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class LocatorSearch {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://www.katalon.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testLocatorSearch() throws Exception {
        driver.get("http://nvistaint30-test.volvocars.biz/Vista-Web/initialisation.do#!");
        driver.findElement(By.linkText("Miscellaneous")).click();
        driver.findElement(By.linkText("Change Identity")).click();
        driver.findElement(By.name("selectedDistributor")).click();
        new Select(driver.findElement(By.name("selectedDistributor"))).selectByVisibleText("3US7578 - Volvo Cars US");
        driver.findElement(By.name("selectedDistributor")).click();
        driver.findElement(By.name("selectedDealer")).click();
        driver.findElement(By.name("selectedDealer")).clear();
        driver.findElement(By.name("selectedDealer")).sendKeys("6US1100 - Northeast Region");
        driver.findElement(By.xpath(
            "(.//*[normalize-space(text()) and normalize-space(.)='Please enter minimum 4 chars to refine the search (Ex. 6XXX)'])[1]/following::nobr[1]")).click();
        driver.findElement(By.linkText("Locator")).click();
        driver.findElement(By.id("available124")).click();
        new Select(driver.findElement(By.id("available124"))).selectByVisibleText("2019a 2019a");
        driver.findElement(By.id("available124")).click();
        driver.findElement(By.id("available2")).click();
        new Select(driver.findElement(By.id("available2"))).selectByVisibleText("536 XC40");
        driver.findElement(By.id("available2")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='<<'])[5]/following::nobr[1]")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
