package productTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StandardUserProductTest {
    private LoginPage loginPage;
    private MainPage mainPage;

    public StandardUserProductTest() throws MalformedURLException {
    }

    @ParameterizedTest
    @MethodSource("utils.Util#driverProvider")
    public void testIfProductDetailsAreShown(WebDriver driver) {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);

        loginPage.loginSuccessful();
        mainPage.clickOnFirstItem();

        boolean isDetailedPageShown = mainPage.checkIfDetailsAreShown();
        assertTrue(isDetailedPageShown);
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("utils.Util#driverProvider")
    public void testIfProductDetailsNameIsCorrect(WebDriver driver) {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);

        String inventoryProductName = mainPage.getFirstProductName();
        mainPage.clickOnFirstItem();
        String detailedProductName = mainPage.getProductDetailsName();

        assertEquals(inventoryProductName, detailedProductName);
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("utils.Util#driverProvider")
    public void testIfProductDetailsPriceIsCorrect(WebDriver driver) {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);

        String inventoryProductPrice = mainPage.getFirstProductPrice();
        mainPage.clickOnFirstItem();
        String detailedProductPrice = mainPage.getProductDetailsPrice();

        assertEquals(inventoryProductPrice, detailedProductPrice);
        driver.quit();
    }
}
