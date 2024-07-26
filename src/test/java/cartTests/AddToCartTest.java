package cartTests;

import org.junit.jupiter.api.AfterEach;
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
    WebDriver firefoxDriver = Util.setFirefoxCapability();
    LoginPage loginPage = new LoginPage(firefoxDriver);
    MainPage mainPage = new MainPage(firefoxDriver);
    CartPage cartPage = new CartPage(firefoxDriver);

    public AddToCartTest() throws MalformedURLException {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/userCredentials.csv", numLinesToSkip = 1)
    public void addOneProductToCartTest(String username, String password) {
        int numberOfProducts = 1;
        loginPage.login(username, password);

        mainPage.addProductsToCart(numberOfProducts);
        mainPage.openCart();

        int actualCartItems = cartPage.countNumberOfProductsInCart();
        assertEquals(numberOfProducts, actualCartItems);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/userCredentials.csv", numLinesToSkip = 1)
    public void addThreeProductsToCartTest(String username, String password) {
        int numberOfProducts = 3;
        loginPage.login(username, password);

        mainPage.addProductsToCart(numberOfProducts);
        mainPage.openCart();

        int actualCartItems = cartPage.countNumberOfProductsInCart();
        assertEquals(numberOfProducts, actualCartItems);
    }

    @AfterEach
    public void tearDown() {
        firefoxDriver.quit();
    }
}
