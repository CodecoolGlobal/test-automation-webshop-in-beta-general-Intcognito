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

public class RemoveFromCartTests {
    WebDriver edgeDriver = Util.setEdgeCapability();
    LoginPage loginPage = new LoginPage(edgeDriver);
    MainPage mainPage = new MainPage(edgeDriver);
    CartPage cartPage = new CartPage(edgeDriver);

    public RemoveFromCartTests() throws MalformedURLException {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/userCredentials.csv", numLinesToSkip = 1)
    public void removeSingleProductFromCartTest(String username, String password) {
        int productsToAdd = 4;
        int productsToRemove = 1;
        loginPage.login(username, password);
        mainPage.addProductsToCart(productsToAdd);
        mainPage.openCart();

        int productsBeforeRemoval = cartPage.countNumberOfProductsInCart();
        cartPage.removeProductsFromCart(productsToRemove);

        int productsAfterRemoval = cartPage.countNumberOfProductsInCart();
        assertEquals(productsBeforeRemoval, (productsAfterRemoval + productsToRemove));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/userCredentials.csv", numLinesToSkip = 1)
    public void removeAllProductsFromCart(String username, String password) {
        int productsToAdd = 4;
        loginPage.login(username, password);
        mainPage.addProductsToCart(productsToAdd);
        mainPage.openCart();

        int productsBeforeRemoval = cartPage.countNumberOfProductsInCart();
        cartPage.removeProductsFromCart(productsBeforeRemoval);

        int productsAfterRemoval = cartPage.countNumberOfProductsInCart();
        assertEquals(0, productsAfterRemoval);
    }

    @AfterEach
    public void tearDown() {
        edgeDriver.quit();
    }
}
