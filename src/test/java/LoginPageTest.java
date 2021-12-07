import helper.RetryAnalyzer;
import helper.TestListener;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.sql.DriverAction;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Listeners(TestListener.class)
public class LoginPageTest extends BaseTest {
    LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        log.info("Открыта страница: {}", LoginPage.url);
        loginPage = new LoginPage(getDriver());
        open(LoginPage.url);
        loginPage.waitForPageLoad(LoginPage.footer.links);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void checkFooterLinks() {
        List<WebElement> footerLinksList = loginPage.findList(LoginPage.footer.links);
        String loginTabHandle = getHandles().get(0);

        footerLinksList.forEach(item -> {

            String expectedUrl = item.getAttribute("href");
            loginPage.click(item);
            ArrayList<String> tabs = getHandles();
            switchTo(tabs.get(tabs.size() - 1));
            log.info("Проверяем переходы по ссылкам: {}", getCurrentUrl());
            Assert.assertEquals(getCurrentUrl(), expectedUrl);
            closeTab();
            switchTo(loginTabHandle);
        });

    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void checkNewsSectionName() {
        log.info("Проверяем название раздела новостей");
        Assert.assertEquals(loginPage.find(LoginPage.news.sectionName).getText(), "News");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void checkNewsLinks() {
        List<WebElement> newsLinksList = loginPage.findList(LoginPage.news.links);
        String loginTabHandle = getHandles().get(0);

        newsLinksList.forEach(item -> {
            String expectedUrl = item.getAttribute("href");
            String newsName = item.getText();
            loginPage.click(item);
            ArrayList<String> tabs = getHandles();
            switchTo(tabs.get(tabs.size() - 1));
            log.info("Проверяем переходы по ссылкам: {}", getCurrentUrl());
            Assert.assertEquals(getCurrentUrl(), expectedUrl);
            Assert.assertEquals(new NewsPage(getDriver()).find(By.xpath(".//h1")).getText(), newsName);
            closeTab();
            switchTo(loginTabHandle);
        });
    }

    @Test//(retryAnalyzer = RetryAnalyzer.class)
    public void login() throws InterruptedException {
        log.info("Вход с логином - " + LoginPage.username);
        log.info("и паролем - " + LoginPage.password);
        loginPage.find(LoginPage.userInput).sendKeys(LoginPage.username);
        loginPage.find(LoginPage.passwordInput).sendKeys(LoginPage.password);
        loginPage.find(LoginPage.loginButton).click();

        Thread.sleep(1000);
        Assert.assertEquals(getCurrentUrl(), "https://my.monkkee.com/#/entries");
    }
}
