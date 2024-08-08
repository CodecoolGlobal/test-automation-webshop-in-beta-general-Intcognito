package cartTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.LoginPage;
import pages.MainPage;
import utils.Util;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddToCartTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private CartPage cartPage;

    public AddToCartTest() throws MalformedURLException {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/allUsersWithDrivers.csv", numLinesToSkip = 1)
    public void addOneProductToCartTest(
            String username,
            String password,
            String browser) throws MalformedURLException {

        driver = Util.driverSelector(browser).get();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        int numberOfProducts = 1;
        loginPage.login(username, password);

        mainPage.addProductsToCart(numberOfProducts);
        mainPage.openCart();

        int actualCartItems = cartPage.countNumberOfProductsInCart();
        assertEquals(numberOfProducts, actualCartItems);
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/allUsersWithDrivers.csv", numLinesToSkip = 1)
    public void addThreeProductsToCartTest(
            String username,
            String password,
            String browser) throws MalformedURLException {

        driver = Util.driverSelector(browser).get();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        int numberOfProducts = 3;
        loginPage.login(username, password);

        mainPage.addProductsToCart(numberOfProducts);
        mainPage.openCart();

        int actualCartItems = cartPage.countNumberOfProductsInCart();
        assertEquals(numberOfProducts, actualCartItems);
        driver.quit();
    }
}
