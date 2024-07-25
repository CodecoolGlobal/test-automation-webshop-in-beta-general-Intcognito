package checkoutTests;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Util;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTests {
    private final Dotenv dotenv = Dotenv.load();
    private final WebDriver chromeDriver = Util.setChromeCapability();
    private final LoginPage loginPage = new LoginPage(chromeDriver);
    private final MainPage mainPage = new MainPage(chromeDriver);
    private final CartPage cartPage = new CartPage(chromeDriver);
    private final CheckoutDetailsPage checkoutDetailsPage = new CheckoutDetailsPage(chromeDriver);
    private final CheckoutPayPage checkoutPayPage = new CheckoutPayPage(chromeDriver);
    private final CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(chromeDriver);

    public CheckoutTests() throws MalformedURLException {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/checkoutPersonalDetails.csv", numLinesToSkip = 1)
    public void buySingleProductAndCheckoutTest(String firstname, String lastname, String zipcode) {
        int numberOfProductsToBuy = 1;
        loginPage.login(dotenv.get("STANDARD_USER"), dotenv.get("PASSWORD"));

        mainPage.addProductsToCart(numberOfProductsToBuy);
        mainPage.openCart();
        cartPage.goToCheckout();
        checkoutDetailsPage.submitPersonalDetails(firstname, lastname, zipcode);
        checkoutPayPage.finishPurchase();

        boolean isPurchaseSuccessful = checkoutCompletePage.checkIfPurchaseIsSuccessful();
        assertTrue(isPurchaseSuccessful);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/userCredentials.csv", numLinesToSkip = 1)
    public void buySingleProductWithDifferentUsersTest(String username, String password) {
        int numberOfProductsToBuy = 1;
        loginPage.login(username, password);

        mainPage.addProductsToCart(numberOfProductsToBuy);
        mainPage.openCart();
        cartPage.goToCheckout();
        checkoutDetailsPage.submitPersonalDetails(
                dotenv.get("FIRST_NAME"),
                dotenv.get("LAST_NAME"),
                dotenv.get("ZIP_CODE")
        );
        checkoutPayPage.finishPurchase();

        boolean isPurchaseSuccessful = checkoutCompletePage.checkIfPurchaseIsSuccessful();
        assertTrue(isPurchaseSuccessful);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/checkoutPersonalDetails.csv", numLinesToSkip = 1)
    public void buySingleProductWithQuestionableDetailsTest(String firstname, String lastname, String zipcode) {
        int numberOfProductsToBuy = 1;
        loginPage.login(dotenv.get("STANDARD_USER"), dotenv.get("PASSWORD"));

        mainPage.addProductsToCart(numberOfProductsToBuy);
        mainPage.openCart();
        cartPage.goToCheckout();
        checkoutDetailsPage.submitPersonalDetails(firstname, lastname, zipcode);
        checkoutPayPage.finishPurchase();

        boolean isPurchaseSuccessful = checkoutCompletePage.checkIfPurchaseIsSuccessful();
        assertTrue(isPurchaseSuccessful);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/checkoutIncompleteDetails.csv", numLinesToSkip = 1)
    public void buySingleItemWithMissingPersonalDetailsTest(String firstname, String lastname, String zipcode) {
        int numberOfProductsToBuy = 1;
        loginPage.login(dotenv.get("STANDARD_USER"), dotenv.get("PASSWORD"));

        mainPage.addProductsToCart(numberOfProductsToBuy);
        mainPage.openCart();
        cartPage.goToCheckout();
        checkoutDetailsPage.submitPersonalDetails(firstname, lastname, zipcode);

        boolean isErrorMessageShown = checkoutDetailsPage.checkIfErrorMessageIsShown();
        assertTrue(isErrorMessageShown);
    }

    @AfterEach
    public void tearDown() {
        chromeDriver.quit();
    }
}
