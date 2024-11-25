package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobject.*;
import utils.WebDriverFactory;
import utils.BaseTest;
import utils.TestDataGenerator;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RegistrationTest extends BaseTest {

    private final String browser;
    private String name;
    private String email;
    private String password;

    public RegistrationTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Collection<Object[]> browsers() {
        return Arrays.asList(new Object[][]{
                {"chrome"}, // Запуск на Google Chrome
                {"yandex"}  // Запуск на Яндекс.Браузере
        });
    }

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver(browser);
        driver.manage().window().maximize();

        name = TestDataGenerator.generateName();
        email = TestDataGenerator.generateEmail();
        password = TestDataGenerator.generateValidPassword();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Description("Тест успешной регистрации нового пользователя")
    public void successfulRegistrationTest() {
        openMainPage();
        openRegistrationPage();
        registerNewUser(name, email, password);
        verifyLoginPageAfterRegistration();
        performLogin(email, password);
        verifyPersonalAccountPage(name, email);
    }

    @Test
    @Description("Тест ошибки при регистрации пользователя с коротким паролем")
    public void registrationWithShortPasswordTest() {
        openMainPage();
        openRegistrationPage();
        String shortPassword = TestDataGenerator.generateShortPassword();
        registerNewUser(name, email, shortPassword);
        verifyPasswordError();
    }

    @Step("Открытие главной страницы")
    private void openMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Открытие страницы регистрации")
    private void openRegistrationPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();
    }

    @Step("Регистрация нового пользователя: имя - {name}, email - {email}")
    private void registerNewUser(String name, String email, String password) {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterName(name);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.clickRegisterButton();
    }

    @Step("Проверка перехода на страницу логина после регистрации")
    private void verifyLoginPageAfterRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue("Не удалось перейти на страницу логина после регистрации.",
                driver.getCurrentUrl().contains("/login"));
    }

    @Step("Проверка ошибки для короткого пароля")
    private void verifyPasswordError() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        String errorMessage = registrationPage.getErrorMessage();
        Assert.assertTrue("Ошибка не отображена или неверная.",
                errorMessage.contains("Некорректный пароль"));
    }

    @Step("Вход в систему с email: {email}")
    private void performLogin(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
    }

    @Step("Проверка данных на странице личного кабинета")
    private void verifyPersonalAccountPage(String expectedName, String expectedEmail) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/account/profile"));

        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        Assert.assertEquals("Имя пользователя не совпадает.", expectedName, personalAccountPage.getName());
        Assert.assertEquals("Email пользователя не совпадает.", expectedEmail, personalAccountPage.getLogin());
    }
}
