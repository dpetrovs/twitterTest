package twitterTests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import twitterTests.configuration.WebDriverConfiguration;

import javax.xml.xpath.XPath;

public class LoginPage extends Page {

    @FindBy(xpath = "//div[@class='StaticLoggedOutHomePage-buttons']/a[@href='/login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id='page-container']/descendant::input[@name='session[username_or_email]']")
    private WebElement loginField;

    @FindBy(xpath = "//div[@id='page-container']/descendant::input[@name='session[password]']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public void login(){
        loginButton.click();
        loginField.sendKeys(getLogin());
        passwordField.sendKeys(getPassword());
        submitButton.click();
    }

    @Override
    public void init(final WebDriver driver) {
        PageFactory.initElements( driver, this);
    }

}
