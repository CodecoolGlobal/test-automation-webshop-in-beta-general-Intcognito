package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

public class Util {
    private static final String GRID_URL = "http://localhost:4444/";
    public static Stream<WebDriver> driverProvider() throws MalformedURLException {
        return Stream.of(
                setChromeCapability(),
                setFirefoxCapability(),
                setEdgeCapability()
        );
    }

    public static WebDriver setChromeCapability() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "126.0");
        chromeOptions.setCapability("platformName", "linux");

        URL url = new URL(GRID_URL);
        System.out.println("chromeDriver done");
        return new RemoteWebDriver(url, chromeOptions);
    }

    public static WebDriver setFirefoxCapability() throws MalformedURLException {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("browserVersion", "127.0");
        firefoxOptions.setCapability("platformName", "linux");

        URL url = new URL(GRID_URL);
        System.out.println("ffDriver done");
        return new RemoteWebDriver(url, firefoxOptions);
    }

    public static WebDriver setEdgeCapability() throws MalformedURLException {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("browserVersion", "126.0");
        edgeOptions.setCapability("platformName", "linux");

        URL url = new URL(GRID_URL);
        System.out.println("edgeDriver done");
        return new RemoteWebDriver(url, edgeOptions);
    }
}
