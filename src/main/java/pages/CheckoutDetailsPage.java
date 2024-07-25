package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutDetailsPage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(id = "first-name")
    WebElement firstnameField;
    @FindBy(id = "last-name")
    WebElement lastnameField;
    @FindBy(id = "postal-code")
    WebElement zipcodeField;
    @FindBy(id = "continue")
    WebElement continueButton;
    @FindBy(xpath = "//div[@class = 'error-message-container error']")
    WebElement errorMessage;

    public CheckoutDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getenv("WAIT_DURATION"))));
        PageFactory.initElements(driver, this);
    }

    public void submitPersonalDetails(String firstname, String lastname, String zipcode) {
        wait.until(ExpectedConditions.visibilityOf(firstnameField));
        firstnameField.sendKeys(firstname != null ? firstname : "");
        lastnameField.sendKeys(lastname != null ? lastname : "");
        zipcodeField.sendKeys(zipcode != null ? zipcode : "");
        continueButton.click();
    }

    public boolean checkIfErrorMessageIsShown() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.isDisplayed();
    }
}
