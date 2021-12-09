import helper.RetryAnalyzer;
import helper.TestListener;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Listeners(TestListener.class)
public class HomePageTest extends BaseTest{
    LoginPage loginPage;
    HomePage homePage;

    @BeforeClass
    public void setUp(){
        homePage = new HomePage(getDriver());
        loginPage = new LoginPage(getDriver());
        open(LoginPage.url);
        loginPage.waitForPageLoad(LoginPage.footer.links);
        login();
    }


    @Test (retryAnalyzer = RetryAnalyzer.class)
    public void createNewEntry() throws InterruptedException {

        String entryText = createEntry();
        List<WebElement> entriesList = homePage.findList(HomePage.entryBody);

        boolean flag = false;
        for (WebElement entry : entriesList) {
            if(entry.getText().equals(entryText))
                flag = true;
        }

        Assert.assertTrue(flag);
    }

    @Test (retryAnalyzer = RetryAnalyzer.class)
    public void deleteEntry() throws InterruptedException {
        Random random = new Random();
        String entryText = createEntry();
        List<WebElement> checkboxes = homePage.findList(HomePage.checkboxes);
        List<WebElement> entriesBeforeDeleting = homePage.findList(HomePage.entryBody);
        List<String> entriesBeforeDeletingText = new ArrayList<>();
        entriesBeforeDeleting.forEach(entry -> {
            entriesBeforeDeletingText.add(entry.getText());
        });

        int indexForRemove = random.nextInt(checkboxes.size() - 1);
        checkboxes.get(indexForRemove).click();
        String homePageHandle = getHandles().get(0);
        homePage.find(HomePage.deleteEntriesButton).click();

        Alert alert = getDriver().switchTo().alert();
        alert.accept();
        switchTo(homePageHandle);
        homePage.waitForPageLoad(HomePage.entryBody);
        Thread.sleep(2000);
        List<WebElement> entriesAfterDeleting = homePage.findList(HomePage.entryBody);
        List<String> entriesAfterDeletingText = new ArrayList<>();
        entriesAfterDeleting.forEach(entry -> {
            System.out.println(entry.getText());
            entriesAfterDeletingText.add(entry.getText());
        });
        Allure.addAttachment("Удаленная запись", entriesBeforeDeletingText.get(indexForRemove));
        entriesBeforeDeletingText.remove(indexForRemove);


        Assert.assertTrue(compareLists(entriesBeforeDeletingText, entriesAfterDeletingText));
    }

    @Test (retryAnalyzer = RetryAnalyzer.class)
    public void search() throws InterruptedException {
        String text = createEntry();
        runSearch(text);
        List<WebElement> entriesList = homePage.findList(HomePage.entryBody);
        entriesList.forEach(entry -> {
            Assert.assertTrue(entry.getText().contains(text));
        });
    }

    @AfterClass
    public void tearDown() {
        logout();
        quit();
    }

    @Step("Создание новой записи")
    private String createEntry() throws InterruptedException {
        homePage.find(HomePage.plusButton).click();
        homePage.waitForPageLoad(HomePage.textArea);
        String text = UUID.randomUUID().toString();



        homePage.waitForPageLoad(By.xpath(".//span[@class='cke_button_icon']"));
        homePage.find(HomePage.textArea).sendKeys(text);
        homePage.find(By.xpath(".//span[@class='cke_button_icon']")).click();

        homePage.find(HomePage.homeButton).click();
        homePage.waitForPageLoad(HomePage.plusButton);
        Allure.addAttachment("Введенное значение", text);
        return text;
    }

    @Step("Сравнение наборов записей")
    private Boolean compareLists(List<String> before, List<String> after){

        return before.containsAll(after) && before.size() == after.size();

    }

    @Step("Запустить поиск")
    private void runSearch(String text) throws InterruptedException {
        Allure.addAttachment("Текст поиска", text);
        homePage.find(HomePage.searchInput).sendKeys(text);
        homePage.find(HomePage.searchButton).click();

        Thread.sleep(1000);

    }

    @Step("Авторизация")
    private void login() {
        loginPage.find(LoginPage.userInput).sendKeys(HomePage.username);
        Allure.addAttachment("Логин", HomePage.username);
        loginPage.find(LoginPage.passwordInput).sendKeys(HomePage.password);
        Allure.addAttachment("Пароль", HomePage.password);
        loginPage.find(LoginPage.loginButton).click();
        homePage.waitForPageLoad(HomePage.plusButton);
    }
    @Step("Выход")
    private void logout() {

        homePage.find(HomePage.logoutButton).click();
    }


}
