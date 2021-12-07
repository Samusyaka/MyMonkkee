import helper.FileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class LoginPage extends BasePage {
    static NewsSection news;
    static final By userInput = By.xpath(".//input[@id='login']");
    static final By passwordInput = By.xpath(".//input[@id='password']");
    static final By loginButton = By.xpath(".//button[@type='submit']");
    static final By loginErrorLabel = By.xpath(".//div[@class='alert alert-danger']");

    static {
        news = new NewsSection();
        try {
            url = FileReader.getValue("url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    LoginPage(WebDriver driver) {
        super(driver);
    }

}
