package loginTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.LoginPage;
import utils.Util;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests {
    private LoginPage loginPage;
    private MainPage mainPage;

    @ParameterizedTest
    @MethodSource("utils.Util#driverProvider")
    public void testLoginWithValidUser(WebDriver driver) {
        loginPage = new LoginPage(driver);

        loginPage.login(System.getenv("STANDARD_USER"), System.getenv("PASSWORD"));

        boolean actual = loginPage.checkIfLoginIsSuccessful();
        assertTrue(actual);

        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("utils.Util#driverProvider")
    public void testLoginWithInvalidUser(WebDriver driver) {
        loginPage = new LoginPage(driver);

        loginPage.login(System.getenv("LOCKED_OUT_USER"), System.getenv("PASSWORD"));

        boolean actual = loginPage.checkIfLoginIsUnsuccessful();
        assertTrue(actual);

        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("utils.Util#driverProvider")
    public void testLogoutFunction(WebDriver driver) {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);

        loginPage.login(System.getenv("STANDARD_USER"), System.getenv("PASSWORD"));
        mainPage.logout();

        boolean actual = loginPage.checkIfLogoutIsSuccessful();
        assertTrue(actual);

        driver.quit();
    }

    @Test
    public void testEdgeLogin() throws MalformedURLException {
        WebDriver driver = Util.setEdgeCapability();
        loginPage = new LoginPage(driver);

        loginPage.login(System.getenv("STANDARD_USER"), System.getenv("PASSWORD"));

        boolean actual = loginPage.checkIfLoginIsSuccessful();
        assertTrue(actual);

        driver.quit();
    }
}
