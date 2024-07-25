package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(className = "product_sort_container")
    private WebElement dropDown;
    @FindBy(id = "react-burger-menu-btn")
    private WebElement sidebarMenuButton;
    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;
    @FindBy(xpath = "//div[@class = 'inventory_item_name ']")
    private WebElement firstProductName;
    @FindBy(xpath = "//div[@class = 'inventory_item_price']")
    private WebElement firstProductPrice;
    @FindBy(xpath = "//div[@class = 'inventory_details_container']")
    private WebElement productDetails;
    @FindBy(xpath = "//div[@class = 'inventory_details_name large_size']")
    private WebElement productDetailsName;
    @FindBy(xpath = "//div[@class = 'inventory_details_price']")
    private WebElement productDetailsPrice;
    @FindBy(xpath = "//button[text() = 'Add to cart']")
    private WebElement addToCartButton;
    @FindBy(xpath = "//a[@class = 'shopping_cart_link']")
    private WebElement cartLink;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink));
        cartLink.click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(sidebarMenuButton));
        sidebarMenuButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }

    public void clickOnFirstItem() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProductName));
        firstProductName.click();
    }

    public void addProductsToCart(int quantity) {
        for (int i = 0; i < quantity; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            addToCartButton.click();
        }
    }

    public boolean checkIfDetailsAreShown() {
        wait.until(ExpectedConditions.visibilityOf(productDetails));
        return productDetails.isDisplayed();
    }

    public String getProductDetailsPrice() {
        wait.until(ExpectedConditions.visibilityOf(productDetailsPrice));
        return productDetailsPrice.getText();
    }

    public String getProductDetailsName() {
        wait.until(ExpectedConditions.visibilityOf(productDetailsName));
        return productDetailsName.getText();
    }

    public String getFirstProductName() {
        wait.until(ExpectedConditions.visibilityOf(firstProductName));
        return firstProductName.getText();
    }

    public String getFirstProductPrice() {
        wait.until(ExpectedConditions.visibilityOf(firstProductPrice));
        return firstProductPrice.getText();
    }

    private List<WebElement> getAllOptions() {
        Select select = new Select(dropDown);
        return select.getOptions();
    }

    private void sortProductsZtoA() {
        getAllOptions().get(1).click(); //steam().findany + param?
    }

    public List<String> getSortedProductNamesZtoA() {
        List<String> names = new ArrayList<>();

        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        for (WebElement product : products) {
            names.add(product.getText());
        }
        names.sort(Collections.reverseOrder());
        return names;
    }

    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();

        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        for (WebElement product : products) {
            names.add(product.getText());
        }

        return names;
    }
}
