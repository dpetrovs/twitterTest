package twitterTests.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends Page{

    private WebDriver driver;

    @FindBy(id = "global-new-tweet-button")
    private WebElement tweetButton;

    @FindBy(id = "Tweetstorm-dialog-dialog")
    private WebElement newTweetDialog;

    @FindBy(xpath = "//div[@class='modal-body']/descendant::div[contains (@class, 'rich-editor is-showPlaceholder')]")
    private WebElement modalTextBox;

    @FindBy(xpath = "//div[@class='modal-body']/descendant::span[@class='buttons']/button[@type='button']")
    private WebElement modalTweetButton;

    @FindBy(xpath = "//button[@class='new-tweets-bar js-new-tweets-bar']")
    private WebElement newTweetsBar;

    @FindBy(xpath = "//ol[@id='stream-items-id']/li[1]")
    private WebElement lastTweet;

    @FindBy(xpath = "//ul[@role='menu']/li[@class='js-actionDelete']")
    private WebElement deleteTweetMenuItem;

    @FindBy(xpath = "//li[@data-item-type='tweet']")
    private List<WebElement> visibleTweetsList;

    @FindBy(xpath = "//div[@id='message-drawer']")
    private WebElement warningMessage;

    @FindBy(xpath = "/html[@lang]")
    private WebElement htmlLanguageElement;

    @FindBy(xpath = "//div[@id='message-drawer']//span[@class='message-text']")
    private WebElement messageText;

    private String tweetId;

    private void tweetButtonClick(){
        waiter(driver).until(ExpectedConditions.elementToBeClickable(tweetButton)).click();
    }

    private void modalTweetButtonClick(){
        waiter(driver).until(ExpectedConditions.elementToBeClickable(modalTweetButton)).click();
    }

    public void postNewTweet(String tweetText){
        tweetButtonClick();
        sendTweetKeys(tweetText);
        modalTweetButtonClick();
        newTweetsBarClick();
        waiter(driver).until(ExpectedConditions.visibilityOfAllElements(lastTweet));
        this.tweetId = driver.findElement(By.xpath("//ol[@id='stream-items-id']/li[1]/div[contains(@class, 'tweet')]")).getAttribute("data-tweet-id");
    }

    public void postDuplicatedTweet(String tweetText){
        tweetButtonClick();
        sendTweetKeys(tweetText);
        modalTweetButtonClick();
    }

    private void newTweetsBarClick(){
        try{
            waiter(driver).until(ExpectedConditions.elementToBeClickable(newTweetsBar)).click();
        } catch (TimeoutException e){
            driver.navigate().refresh();
        }
    }

    private void sendTweetKeys(String tweetText){
        waiter(driver).until(ExpectedConditions.elementToBeClickable(modalTextBox));
        modalTextBox.sendKeys(tweetText);
    }

    public String getLastTweetText() {
        waiter(driver).until(ExpectedConditions.visibilityOfAllElements(lastTweet));
        return lastTweet.findElement(By.xpath("/descendant::p[contains(@class, 'js-tweet-text')]")).getAttribute("innerText");
    }

    public String getLastTweetRetweetCount(){
        return lastTweet.findElement(By.xpath("/descendant::button[@class='ProfileTweet-actionButton  js-actionButton js-actionRetweet']/descendant::span[@class='ProfileTweet-actionCountForPresentation']")).getText();
    }

    public String getTweetId() {
        return tweetId;
    }

    private void clickLastTweetDropDown(){
        waiter(driver).until(ExpectedConditions.visibilityOfAllElements(lastTweet));
        lastTweet.findElement(By.xpath("/descendant::div[@class='dropdown']")).click();
    }

    public void deleteLastTweet(){
        clickLastTweetDropDown();
        waiter(driver).until(ExpectedConditions.elementToBeClickable(deleteTweetMenuItem)).click();
    }

    private List<String> getElementsAttributes(List<WebElement> elementsList, String attribute) {
        List<String> result = new ArrayList<>();
        for (WebElement e : elementsList) {
            result.add(e.getAttribute(attribute));
        }
        return result;
    }

    public boolean isTweetPresentById(String tweetId){
        List<String> tweetIds = getElementsAttributes(visibleTweetsList, "data-item-id");
        for (String s: tweetIds) {
            if (s.equals(tweetId)){
                return true;
            }
        }
        return false;
    }

    public String warningMessageContent(){
        String result = "";
        if (htmlLanguageElement.getAttribute("lang").equals("ru")){
            try {
                waiter(driver).until(ExpectedConditions.textToBePresentInElement(messageText, "Вы уже отправили этот твит."));
                result = messageText.getText();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void init(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements( driver, this);
    }
}
