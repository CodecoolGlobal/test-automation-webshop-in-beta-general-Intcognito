package utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Stream;

public class Util {
    private final static Dotenv dotenv = Dotenv.load();
    private final static String GRID_URL = dotenv.get("GRID_URL");

    public static Stream<WebDriver> driverProvider() throws MalformedURLException {
        return Stream.of(
                setChromeCapability(),
                setFirefoxCapability(),
                setEdgeCapability()
        );
    }

    public static Optional<WebDriver> driverSelector(String driverName) throws MalformedURLException {
        return switch (driverName) {
            case "chrome" -> Optional.of(setChromeCapability());
            case "firefox" -> Optional.of(setFirefoxCapability());
            case "edge" -> Optional.of(setEdgeCapability());
            default -> {
                System.out.println("Browser name not recognized!");
                yield Optional.empty();
            }
        };
    }

    private static WebDriver setChromeCapability() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "126.0");
        chromeOptions.setCapability("platformName", "linux");

        URL url = new URL(GRID_URL);
        System.out.println("Created chrome driver");
        return new RemoteWebDriver(url, chromeOptions);
    }

    private static WebDriver setFirefoxCapability() throws MalformedURLException {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("browserVersion", "127.0");
        firefoxOptions.setCapability("platformName", "linux");

        URL url = new URL(GRID_URL);
        System.out.println("Created Firefox driver");
        return new RemoteWebDriver(url, firefoxOptions);
    }

    private static WebDriver setEdgeCapability() throws MalformedURLException {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("browserVersion", "126.0");
        edgeOptions.setCapability("platformName", "linux");

        URL url = new URL(GRID_URL);
        System.out.println("Created Edge driver");
        return new RemoteWebDriver(url, edgeOptions);
    }
}
