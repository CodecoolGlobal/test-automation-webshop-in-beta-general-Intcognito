package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Util {
    private static final String GRID_URL = "http://localhost:4444/";

    public WebDriver setChromeCapability() throws MalformedURLException, URISyntaxException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "126.0");
        chromeOptions.setCapability("platformName", "linux");

        URL url = new URL(GRID_URL);
        System.out.println("chromedriver done");
        return new RemoteWebDriver(url, chromeOptions);
    }

    public WebDriver setFirefoxCapability() throws MalformedURLException, URISyntaxException {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("browserVersion", "latest");
        firefoxOptions.setCapability("platformName", "linux");
        URL url = new URI(GRID_URL).toURL();
        System.out.println("ffdriver done");
        return new RemoteWebDriver(url, firefoxOptions);
    }

    public WebDriver setEdgeCapability() throws MalformedURLException, URISyntaxException {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("browserVersion", "latest");
        edgeOptions.setCapability("platformName", "linux");
        URL url = new URI(GRID_URL).toURL();
        System.out.println("edgedriver done");
        return new RemoteWebDriver(url, edgeOptions);
    }
}
