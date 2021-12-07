import helper.FileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

public abstract class BasePage {
    static Footer footer;
    private final WebDriver driver;
    private static final int TIMEOUT = 10;
    protected static String username;
    protected static String password;
    protected static String url;

    static {
        footer = new Footer();
        try {
            username = FileReader.getValue("username");
            password = FileReader.getValue("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void click(WebElement element){
        element.click();
    }

    public WebElement find(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> findList(By locator){
        return driver.findElements(locator);
    }

    @SuppressWarnings("deprecation")
    public void waitForPageLoad(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
