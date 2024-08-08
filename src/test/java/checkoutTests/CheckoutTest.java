package checkoutTests;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Util;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTest {
    private final Dotenv dotenv = Dotenv.load();
    private LoginPage loginPage;
    private MainPage mainPage;
    private CartPage cartPage;
    private CheckoutDetailsPage checkoutDetailsPage;
    private CheckoutPayPage checkoutPayPage;
    private CheckoutCompletePage checkoutCompletePage;

    public CheckoutTest() throws MalformedURLException {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/checkoutPersonalDetails.csv", numLinesToSkip = 1)
    public void buySingleProductAndCheckoutTest(
            String firstname,
            String lastname,
            String zipcode,
            String browser) throws MalformedURLException {

        WebDriver driver = Util.driverSelector(browser).get();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        checkoutDetailsPage = new CheckoutDetailsPage(driver);
        checkoutPayPage = new CheckoutPayPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);

        int numberOfProductsToBuy = 1;
        loginPage.loginSuccessful();

        mainPage.addProductsToCart(numberOfProductsToBuy);
        mainPage.openCart();
        cartPage.goToCheckout();
        checkoutDetailsPage.submitPersonalDetails(firstname, lastname, zipcode);
        checkoutPayPage.finishPurchase();

        boolean isPurchaseSuccessful = checkoutCompletePage.checkIfPurchaseIsSuccessful();
        assertTrue(isPurchaseSuccessful);
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/allUsersWithDrivers.csv", numLinesToSkip = 1)
    public void buySingleProductWithDifferentUsersTest(
            String username,
            String password,
            String browser) throws MalformedURLException {

        WebDriver driver = Util.driverSelector(browser).get();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        checkoutDetailsPage = new CheckoutDetailsPage(driver);
        checkoutPayPage = new CheckoutPayPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);

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
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/checkoutPersonalDetails.csv", numLinesToSkip = 1)
    public void buySingleProductWithQuestionableDetailsTest(
            String firstname,
            String lastname,
            String zipcode,
            String browser) throws MalformedURLException {

        WebDriver driver = Util.driverSelector(browser).get();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        checkoutDetailsPage = new CheckoutDetailsPage(driver);
        checkoutPayPage = new CheckoutPayPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        int numberOfProductsToBuy = 1;
        loginPage.loginSuccessful();

        mainPage.addProductsToCart(numberOfProductsToBuy);
        mainPage.openCart();
        cartPage.goToCheckout();
        checkoutDetailsPage.submitPersonalDetails(firstname, lastname, zipcode);
        checkoutPayPage.finishPurchase();

        boolean isPurchaseSuccessful = checkoutCompletePage.checkIfPurchaseIsSuccessful();
        assertTrue(isPurchaseSuccessful);
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/checkoutIncompleteDetails.csv", numLinesToSkip = 1)
    public void buySingleItemWithMissingPersonalDetailsTest(
            String firstname,
            String lastname,
            String zipcode,
            String browser) throws MalformedURLException {

        WebDriver driver = Util.driverSelector(browser).get();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        checkoutDetailsPage = new CheckoutDetailsPage(driver);
        checkoutPayPage = new CheckoutPayPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        int numberOfProductsToBuy = 1;
        loginPage.loginSuccessful();

        mainPage.addProductsToCart(numberOfProductsToBuy);
        mainPage.openCart();
        cartPage.goToCheckout();
        checkoutDetailsPage.submitPersonalDetails(firstname, lastname, zipcode);

        boolean isErrorMessageShown = checkoutDetailsPage.checkIfErrorMessageIsShown();
        assertTrue(isErrorMessageShown);
        driver.quit();
    }
}
