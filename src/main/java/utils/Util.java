package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Util {
    private static final String GRID_URL = "http://localhost:4444/";

    public WebDriver setChromeCapability() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "126.0");
        chromeOptions.setCapability("platformName", "linux");

        URL url = new URL(GRID_URL);
        System.out.println("chromeDriver done");
        return new RemoteWebDriver(url, chromeOptions);
    }

    public WebDriver setFirefoxCapability() throws MalformedURLException {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("browserVersion", "127.0");
        firefoxOptions.setCapability("platformName", "linux");

        URL url = new URL(GRID_URL);
        System.out.println("ffDriver done");
        return new RemoteWebDriver(url, firefoxOptions);
    }

    public WebDriver setEdgeCapability() throws MalformedURLException {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("browserVersion", "126.0");
        edgeOptions.setCapability("platformName", "linux");

        URL url = new URL(GRID_URL);
        System.out.println("edgeDriver done");
        return new RemoteWebDriver(url, edgeOptions);
    }
}
