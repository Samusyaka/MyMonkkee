import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewsPage extends BasePage{
    public final By header = By.xpath(".//h1");

    NewsPage(WebDriver driver) {
        super(driver);
    }
}
