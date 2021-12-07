import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.ArrayList;

public class BaseTest {
    private WebDriver driver;

    BaseTest() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void open(String url) {
        driver.get(url);
    }
    public void quit(){
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeTab() {
        driver.close();
    }

    public void switchTo(String handle) {
        driver.switchTo().window(handle);
    }

    public ArrayList<String> getHandles() {
        return new ArrayList<>(driver.getWindowHandles());
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

}
