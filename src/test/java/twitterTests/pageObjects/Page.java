package twitterTests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import twitterTests.configuration.WebDriverConfiguration;

public abstract class Page extends WebDriverConfiguration{
    public WebDriverWait waiter(WebDriver webDriver) {
        return new WebDriverWait(webDriver, 15);
    }
    void init (WebDriver driver) {
    }
}
