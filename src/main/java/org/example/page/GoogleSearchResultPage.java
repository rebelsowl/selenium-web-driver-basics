package org.example.page;

import org.openqa.selenium.WebDriver;

public class GoogleSearchResultPage extends AbstractPage {



    public GoogleSearchResultPage(WebDriver driver) {
        super(driver);
    }



    @Override
    protected AbstractPage openPage() {
        throw new RuntimeException("No direct open page to search result");
    }
}
