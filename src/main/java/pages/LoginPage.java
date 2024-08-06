package pages;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final Dotenv dotenv = Dotenv.load();
    private final String BASE_URL = dotenv.get("BASE_URL");
    private final String HOME_URL = dotenv.get("HOME_URL");
    @FindBy(id = "user-name")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "login-button")
    private WebElement loginButton;
    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;
    @FindBy(xpath = "//div[@class = 'error-message-container error']")
    private WebElement loginErrorMessage;

    public LoginPage(WebDriver driver) {
        Dotenv dotenv = Dotenv.load();
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(dotenv.get("WAIT_DURATION"))));
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        driver.get(BASE_URL);
        System.out.println("Navigated to login page");
        fillUsername(username);
        fillPassword(password);
        submitCredentials();
    }

    public void loginSuccessful() {
        String username = dotenv.get("STANDARD_USER");
        String password = dotenv.get("PASSWORD");
        driver.get(BASE_URL);
        fillUsername(username);
        fillPassword(password);
        submitCredentials();

        wait.until(ExpectedConditions.urlToBe(HOME_URL));
        System.out.println("Successfully logged in");
    }

    public boolean checkIfLoginIsSuccessful() {
        wait.until(ExpectedConditions.visibilityOf(menuButton));
        return menuButton.isDisplayed();
    }

    public boolean checkIfLoginIsUnsuccessful() {
        wait.until(ExpectedConditions.visibilityOf(loginErrorMessage));
        return (checkIfLoginPageIsShown() && checkIfErrorMessageIsShown());
    }

    public boolean checkIfLogoutIsSuccessful() {
        return checkIfLoginPageIsShown();
    }

    private boolean checkIfLoginPageIsShown() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        return loginButton.isDisplayed();
    }

    private boolean checkIfErrorMessageIsShown() {
        wait.until(ExpectedConditions.visibilityOf(loginErrorMessage));
        return loginErrorMessage.isDisplayed();
    }

    private void fillUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.sendKeys(username);
    }

    private void fillPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
    }

    private void submitCredentials() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }
}
