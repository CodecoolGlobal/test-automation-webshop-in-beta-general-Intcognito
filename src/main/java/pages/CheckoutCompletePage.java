package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutCompletePage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath = "//h2[@class = 'complete-header']")
    WebElement purchaseCompleteMessage;
    @FindBy(id = "back-to-products")
    WebElement homeButton;


    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getenv("WAIT_DURATION"))));
        PageFactory.initElements(driver, this);
    }

    public boolean checkIfPurchaseIsSuccessful() {
        wait.until(ExpectedConditions.visibilityOf(purchaseCompleteMessage));
        return purchaseCompleteMessage.isDisplayed();
    }
}
