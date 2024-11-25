package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.*;
import utils.BaseTest;
import utils.User;
import utils.UserHelper;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NavigationTests extends BaseTest {

    private final String browser;
    private User testUser; // Тестовый пользователь

    public NavigationTests(String browser) {
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
        driver = utils.WebDriverFactory.createDriver(browser);
        driver.manage().window().maximize();

        // Создание пользователя через API для тестов с авторизацией
        testUser = UserHelper.createUser();
    }

    @After
    public void tearDown() {
        // Удаление пользователя через API
        if (testUser != null) {
            UserHelper.deleteUser(testUser);
        }

        if (driver != null) {
            driver.quit();
        }
    }

    // --- Тесты без авторизации ---
    @Test
    @Description("Тест: вход по кнопке «Войти в аккаунт» на главной странице.")
    public void testLoginFromMainPage() {
        openMainPage();
        clickLoginButtonOnMainPage();
        verifyLoginPageOpened();
    }

    @Test
    @Description("Тест: вход через кнопку «Личный кабинет».")
    public void testLoginFromPersonalAccountButton() {
        openMainPage();
        clickPersonalAccountButtonOnMainPage();
        verifyLoginPageOpened();
    }

    @Test
    @Description("Тест: вход через кнопку в форме регистрации.")
    public void testLoginFromRegistrationForm() {
        openMainPage();
        navigateToRegistrationPage();
        clickLoginButtonOnRegistrationPage();
        verifyLoginPageOpened();
    }

    @Test
    @Description("Тест: вход через кнопку в форме восстановления пароля.")
    public void testLoginFromForgotPasswordForm() {
        openMainPage();
        navigateToForgotPasswordPage();
        clickLoginButtonOnForgotPasswordPage();
        verifyLoginPageOpened();
    }

    // --- Тесты с авторизацией ---
    @Test
    @Description("Тест: переход в личный кабинет.")
    public void testNavigationToPersonalAccount() {
        performLoginWithUser(testUser);
        navigateToPersonalAccount();
        verifyPersonalAccountPageOpened();
    }

    @Test
    @Description("Тест: переход из личного кабинета в конструктор.")
    public void testNavigationFromPersonalAccountToConstructor() {
        performLoginWithUser(testUser);
        navigateToPersonalAccount();
        clickConstructorButtonFromPersonalAccount();
        verifyMainPageOpened();
    }

    @Test
    @Description("Тест: переход из личного кабинета по клику на логотип.")
    public void testNavigationFromPersonalAccountToLogo() {
        performLoginWithUser(testUser);
        navigateToPersonalAccount();
        clickLogoButtonSafelyFromPersonalAccount();
        verifyMainPageOpened();
    }

    @Test
    @Description("Тест: выход по кнопке «Выйти» в личном кабинете.")
    public void testLogoutFromPersonalAccount() {
        performLoginWithUser(testUser);

        // Переход в личный кабинет
        MainPage mainPage = new MainPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getPersonalAccountButtonLocator())).click();

        // Убедимся, что мы в личном кабинете
        verifyPersonalAccountPageOpened();

        // Нажимаем на кнопку «Выйти»
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountPage.getLogoutButtonLocator())).click();

        // Проверяем, что мы вернулись на страницу логина
        verifyLoginPageOpened();
    }

    // --- Методы для выполнения шагов теста ---
    @Step("Открытие главной страницы")
    private void openMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Нажатие на кнопку «Войти в аккаунт» на главной странице")
    private void clickLoginButtonOnMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
    }

    @Step("Нажатие на кнопку «Личный кабинет» на главной странице")
    private void clickPersonalAccountButtonOnMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
    }

    @Step("Переход на страницу регистрации")
    private void navigateToRegistrationPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();
    }

    @Step("Нажатие на кнопку входа на странице регистрации")
    private void clickLoginButtonOnRegistrationPage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginButton();
    }

    @Step("Переход на страницу восстановления пароля")
    private void navigateToForgotPasswordPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordButton();
    }

    @Step("Нажатие на кнопку входа на странице восстановления пароля")
    private void clickLoginButtonOnForgotPasswordPage() {
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        passwordRecoveryPage.clickLoginButton();
    }

    @Step("Вход в систему с использованием данных пользователя")
    private void performLoginWithUser(User user) {
        openMainPage();
        clickPersonalAccountButtonOnMainPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();
    }

    @Step("Переход в личный кабинет")
    private void navigateToPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
    }

    @Step("Клик по кнопке «Конструктор» из личного кабинета")
    private void clickConstructorButtonFromPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickConstructorButton();
    }

    @Step("Клик по логотипу Stellar Burgers в личном кабинете")
    private void clickLogoButtonSafelyFromPersonalAccount() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        if (!personalAccountPage.isLogoButtonVisible()) {
            Assert.fail("Логотип Stellar Burgers недоступен для клика.");
        }
        personalAccountPage.clickLogoButtonSafely();
    }

    @Step("Проверка перехода на главную страницу")
    private void verifyMainPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
        Assert.assertEquals("Не удалось перейти на главную страницу.", "https://stellarburgers.nomoreparties.site/", driver.getCurrentUrl());
    }

    @Step("Проверка, что открыта страница личного кабинета")
    private void verifyPersonalAccountPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/account/profile"));
        Assert.assertTrue("Не удалось перейти в личный кабинет.", driver.getCurrentUrl().contains("/account/profile"));
    }

    @Step("Проверка, что открыта страница логина")
    private void verifyLoginPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue("Не удалось перейти на страницу логина.", driver.getCurrentUrl().contains("/login"));
    }
}
