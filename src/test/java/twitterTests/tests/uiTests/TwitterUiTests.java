package twitterTests.tests.uiTests;

import org.assertj.core.api.SoftAssertions;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import twitterTests.category.UiTest;
import twitterTests.configuration.WebDriverConfiguration;
import twitterTests.pageObjects.LoginPage;
import twitterTests.pageObjects.MainPage;
import twitterTests.tests.apiTests.ApiBase;

public class TwitterUiTests extends WebDriverConfiguration{
    private WebDriver driver;
    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    ApiBase apiBase = new ApiBase();

    @Test
    @Category(UiTest.class)
    public void createNewTweetTest(){
        loginPage.login();
        mainPage.postNewTweet("New Tweet 1");
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat("New Tweet 1".equals(mainPage.getLastTweetText()));
        softly.assertThat("".equals(mainPage.getLastTweetRetweetCount()));

        softly.assertAll();
    }

    @Test
    @Category(UiTest.class)
    public void deleteNewTweetTest(){
        loginPage.login();
        mainPage.postNewTweet("New Tweet 1");
        mainPage.deleteLastTweet();
        Assert.assertEquals(true, mainPage.isTweetPresentById(mainPage.getTweetId()));
    }

    @Test
    @Category(UiTest.class)
    public void tweetDuplicationTest(){
        loginPage.login();
        mainPage.postNewTweet("New Tweet 1");
        mainPage.postNewTweet("New Tweet 1");
        Assert.assertEquals(1, mainPage.repeatedTweetsCount("New Tweet 1"));
    }

    @Before
    public void before(){
        webDriverSelection();
        this.driver = getDriver();
        loginPage.init(driver);
        mainPage.init(driver);
        driverBaseSteps(driver);
    }

    @After
     public void after() {
        driver.quit();
        apiBase.deleteCreatedTweets(mainPage.getTweetId());
    }
}
