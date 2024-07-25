package pages;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPayPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = "finish")
    WebElement finishButton;

    public CheckoutPayPage(WebDriver driver) {
        Dotenv dotenv = Dotenv.load();
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(dotenv.get("WAIT_DURATION"))));
        PageFactory.initElements(driver, this);
    }

    public void finishPurchase() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        finishButton.click();
    }
}
