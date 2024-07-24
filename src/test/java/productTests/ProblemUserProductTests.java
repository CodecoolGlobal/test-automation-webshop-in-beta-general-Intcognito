package productTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import utils.Util;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class ProblemUserProductTests {
    WebDriver chromeDriver = Util.setChromeCapability();
    LoginPage loginPage = new LoginPage(chromeDriver);
    MainPage mainPage = new MainPage(chromeDriver);

    public ProblemUserProductTests() throws MalformedURLException {
    }

    @BeforeEach
    public void setUp() {
        loginPage.login(System.getenv("STANDARD_USER"), System.getenv("PASSWORD"));
    }

    @Test
    public void testIfProductDetailsAreShown() {
        mainPage.clickOnFirstItem();

        boolean isDetailedPageShown = mainPage.checkIfDetailsAreShown();
        assertTrue(isDetailedPageShown);
    }

    @Test
    public void testIfProductDetailsNameIsCorrect() {
        String inventoryProductName = mainPage.getFirstProductName();
        mainPage.clickOnFirstItem();
        String detailedProductName = mainPage.getProductDetailsName();

        assertEquals(inventoryProductName, detailedProductName);
    }

    @Test
    public void testIfProductDetailsPriceIsCorrect() {
        String inventoryProductPrice = mainPage.getFirstProductPrice();
        mainPage.clickOnFirstItem();
        String detailedProductPrice = mainPage.getProductDetailsPrice();

        assertEquals(inventoryProductPrice, detailedProductPrice);
    }

    @AfterEach
    public void tearDown() {
        chromeDriver.quit();
    }
}
