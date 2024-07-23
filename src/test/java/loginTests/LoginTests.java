package loginTests;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.Util;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests {
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
    }

    @ParameterizedTest
    @MethodSource("utils.Util#driverProvider")
    public void testLogin(WebDriver driver) throws MalformedURLException {
        loginPage = new LoginPage(driver);

        loginPage.login(System.getenv("STANDARD_USER"), System.getenv("PASSWORD"));

        boolean actual = loginPage.checkIfLoginSuccessful();

        assertTrue(actual);

        driver.quit();
    }

    @Test
    public void testEdgeLogin() throws MalformedURLException {
        loginPage = new LoginPage(Util.setEdgeCapability());

        loginPage.login(System.getenv("STANDARD_USER"), System.getenv("PASSWORD"));

        boolean actual = loginPage.checkIfLoginSuccessful();

        assertTrue(actual);
    }

    @AfterEach
    public void tearDown() {
    }
}
