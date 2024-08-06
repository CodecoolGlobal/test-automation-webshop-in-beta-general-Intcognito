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

public class RemoveFromCartTest {
    private LoginPage loginPage;
    private MainPage mainPage;
    private CartPage cartPage;

    public RemoveFromCartTest() throws MalformedURLException {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/threeUsersWithDrivers.csv", numLinesToSkip = 1)
    public void removeSingleProductFromCartTest(String username, String password, String driverName) throws MalformedURLException {
        WebDriver driver = Util.driverSelector(driverName).get();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);

        int productsToAdd = 4;
        int productsToRemove = 1;
        loginPage.login(username, password);
        mainPage.addProductsToCart(productsToAdd);
        mainPage.openCart();

        int productsBeforeRemoval = cartPage.countNumberOfProductsInCart();
        cartPage.removeProductsFromCart(productsToRemove);

        int productsAfterRemoval = cartPage.countNumberOfProductsInCart();
        assertEquals(productsBeforeRemoval, (productsAfterRemoval + productsToRemove));
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/threeUsersWithDrivers.csv", numLinesToSkip = 1)
    public void removeAllProductsFromCart(String username, String password, String driverName) throws MalformedURLException {
        WebDriver driver = Util.driverSelector(driverName).get();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);

        int productsToAdd = 4;
        loginPage.login(username, password);
        mainPage.addProductsToCart(productsToAdd);
        mainPage.openCart();

        int productsBeforeRemoval = cartPage.countNumberOfProductsInCart();
        cartPage.removeProductsFromCart(productsBeforeRemoval);

        int productsAfterRemoval = cartPage.countNumberOfProductsInCart();
        assertEquals(0, productsAfterRemoval);
        driver.quit();
    }
}
