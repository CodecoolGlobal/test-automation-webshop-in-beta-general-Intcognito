package productTests;

import io.github.cdimascio.dotenv.Dotenv;
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
public class ProblemUserProductTest {  // duplicated class, should be deleted
    private final Dotenv dotenv = Dotenv.load();
    private final WebDriver chromeDriver = Util.setChromeCapability();
    private final LoginPage loginPage = new LoginPage(chromeDriver);
    private final MainPage mainPage = new MainPage(chromeDriver);

    public ProblemUserProductTest() throws MalformedURLException {
    }

    @BeforeEach
    public void setUp() {
        loginPage.login(dotenv.get("STANDARD_USER"), dotenv.get("PASSWORD"));
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
