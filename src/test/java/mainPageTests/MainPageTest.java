package mainPageTests;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import utils.Util;

import java.net.MalformedURLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTest {
    private final Dotenv dotenv = Dotenv.load();
    private final WebDriver chromeDriver = Util.setChromeCapability();
    private final LoginPage loginPage = new LoginPage(chromeDriver);
    private final MainPage mainPage = new MainPage(chromeDriver);

    public MainPageTest() throws MalformedURLException {
    }

    @BeforeEach
    public void setUp() {
        loginPage.login(dotenv.get("STANDARD_USER"), dotenv.get("PASSWORD"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/itemInfos.csv", numLinesToSkip = 1)
    public void testProductsSortedInReverseOrder(String info, String option){
        mainPage.clickSortProducts(option);

        List<String> sortedItemNames = mainPage.getProductsSortedInReverseOrder(info);

        assertEquals(sortedItemNames, mainPage.getProductInfo(info));
    }

    @AfterEach
    public void tearDown(){chromeDriver.quit();}

}