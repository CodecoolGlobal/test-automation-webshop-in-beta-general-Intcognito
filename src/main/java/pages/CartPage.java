package pages;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//button[text() = 'Remove']")
    private WebElement removeButton;
    @FindBy(id = "checkout")
    private WebElement checkoutButton;
    @FindBy(xpath = "//div[@class = 'cart_item']")
    private List<WebElement> cartItems;


    public CartPage(WebDriver driver) {
        Dotenv dotenv = Dotenv.load();
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getenv("WAIT_DURATION"))));
        PageFactory.initElements(driver, this);
    }

    public void goToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
    }

    public int countNumberOfProductsInCart() {
        wait.until(ExpectedConditions.visibilityOf(checkoutButton));
        return cartItems.size();
    }

    public void removeProductsFromCart(int quantityToRemove) {
        for (int i = 0; i < quantityToRemove; i++) {
            wait.until(ExpectedConditions.visibilityOf(removeButton));
            removeButton.click();
        }
    }

}
