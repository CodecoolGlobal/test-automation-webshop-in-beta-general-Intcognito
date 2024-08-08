package mainPageTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import utils.Util;

import java.net.MalformedURLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowseProductTest {
    private LoginPage loginPage;
    private MainPage mainPage;

    public BrowseProductTest() throws MalformedURLException {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/itemInfos.csv", numLinesToSkip = 1)
    public void testProductsSortedInReverseOrder(
            String info,
            String option,
            String browser) throws MalformedURLException {
        WebDriver driver = Util.driverSelector(browser).get();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        loginPage.loginSuccessful();

        mainPage.clickSortProducts(option);

        List<String> sortedItemNames = mainPage.getProductsSortedInReverseOrder(info);

        assertEquals(sortedItemNames, mainPage.getProductInfo(info));
        driver.quit();
    }
}
