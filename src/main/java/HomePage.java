import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    static final By plusButton = By.xpath(".//a[@id='create-entry']");
    static final By textArea = By.xpath(".//div[@id='editable']");
    static final By homeButton = By.xpath(".//a[@id='back-to-overview']");
    static final By deleteEntriesButton = By.xpath(".//a[@id='delete-entries']");
    static final By deleteEntryButton = By.xpath(".//a[@id='delete-entry']");
    static final By entryBody = By.xpath(".//a[@class='entry']/div[@class='body ']");
    static final By checkboxes = By.xpath(".//div[@class='entries']//input");
    static final By searchInput = By.xpath(".//input[@id='appendedInputButton']");
    static final By searchButton = By.xpath(".//button[@title='Search']");
    static final By logoutButton = By.xpath(".//div[@ng-show='loggedIn']/button");

    static {
        url = "https://my.monkkee.com/#/entries";
    }

    HomePage(WebDriver driver) {
        super(driver);
    }

}
