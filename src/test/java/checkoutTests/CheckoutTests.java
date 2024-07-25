package checkoutTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Util;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTests {
    WebDriver chromeDriver = Util.setChromeCapability();
    LoginPage loginPage = new LoginPage(chromeDriver);
    MainPage mainPage = new MainPage(chromeDriver);
    CartPage cartPage = new CartPage(chromeDriver);
    CheckoutDetailsPage checkoutDetailsPage = new CheckoutDetailsPage(chromeDriver);
    CheckoutPayPage checkoutPayPage = new CheckoutPayPage(chromeDriver);
    CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(chromeDriver);

    public CheckoutTests() throws MalformedURLException {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/checkoutPersonalDetails.csv", numLinesToSkip = 1)
    public void buySingleProductAndCheckoutTest(String firstname, String lastname, String zipcode) {
        int numberOfProductsToBuy = 1;
        loginPage.login(System.getenv("STANDARD_USER"), System.getenv("PASSWORD"));

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
                System.getenv("FIRST_NAME"),
                System.getenv("LAST_NAME"),
                System.getenv("ZIP_CODE")
        );
        checkoutPayPage.finishPurchase();

        boolean isPurchaseSuccessful = checkoutCompletePage.checkIfPurchaseIsSuccessful();
        assertTrue(isPurchaseSuccessful);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/checkoutPersonalDetails.csv", numLinesToSkip = 1)
    public void buySingleProductWithQuestionableDetailsTest(String firstname, String lastname, String zipcode) {
        int numberOfProductsToBuy = 1;
        loginPage.login(System.getenv("STANDARD_USER"), System.getenv("PASSWORD"));

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
        loginPage.login(System.getenv("STANDARD_USER"), System.getenv("PASSWORD"));

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
