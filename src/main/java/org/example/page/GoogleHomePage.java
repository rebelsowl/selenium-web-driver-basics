package org.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class GoogleHomePage {

    private static final String HOMEPAGE_URL = "http://google.com";
    private WebDriver driver;

    @FindBy(id = "APjFqb")
    @CacheLookup // gets it from browsers cache use it for unchanging elements
    private WebElement searchInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[1]")
    private WebElement searchButton;


    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleHomePage openPage(){
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[2]")));
        return this;
    }


    public GoogleSearchResultPage clickSearchButton() {
        searchButton.click();
        return new GoogleSearchResultPage(driver);
    }



}
