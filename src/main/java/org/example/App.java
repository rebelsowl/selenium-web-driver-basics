package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

/**
 * Hello world!
 *
 */
public class App {
    private static final String XPATH_EXPRESSION = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[2]"; // "//input[@value='Kendimi Şanslı Hissediyorum']";

    public static void main( String[] args ) {

        WebDriver driver = new ChromeDriver(); // creating driver

        // core api basics
        driver.get("http://google.com");
        WebElement searchInput = driver.findElement(By.id("APjFqb")); //
        // searchInput = driver.findElement(By.id("asdasdfasdfdfasdf")); // NoSuchElementException
        searchInput.sendKeys("some google search");
        WebElement searchButton = driver.findElements(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[1]")).get(0); //
        searchButton.click();

        // findElement returns 1 object, first found element throws exception(NoSuchElementException) if not found
        // findElements returns list, empty if none found



        //     SYNCHRONIZATION
        //   never use Thread.sleep()

        // TIMEOUTS
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10)); // time out for page load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //  implicit waits are risky, has side effects, default is 0
        //      slows down(always wait maximum value),
        //      if implicit wait is longer than explicit wait(example below), explicit wait doesn't work
        //      -> use waits instead




        // WAITS
        // Explicit wait
        driver.get("http://google.com");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(XPATH_EXPRESSION)));
        // if expected conditions are fulfilled before duration it continues execution, if not fulfilled after duration throws runtime exception


        // Fluent wait
        driver.get("http://google.com");
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class)
                .withMessage("Timeout has been exceeded");

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(By.xpath(XPATH_EXPRESSION));
            }
        });
        foo.click();


        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.quit();
    }
}
