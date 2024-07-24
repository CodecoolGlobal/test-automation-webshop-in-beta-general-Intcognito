package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(className = "product_sort_container")
    private WebElement dropDown;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private List<WebElement> getAllOptions(){
        Select select = new Select(dropDown);
        return select.getOptions();
    }

    private void sortProductsZtoA(){
        getAllOptions().get(1).click(); //steam().findany + param?
    }

    public List<String> getSortedProductNamesZtoA(){
        List<String> names = new ArrayList<>();

        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        for (WebElement product : products){
            names.add(product.getText());
        }
        names.sort(Collections.reverseOrder());
        return names;
    }
    public List<String> getProductNames(){
        List<String> names = new ArrayList<>();

        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        for (WebElement product : products){
            names.add(product.getText());
        }

        return names;
    }
}
