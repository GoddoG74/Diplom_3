package tests;

import io.qameta.allure.Description;
import org.junit.Test;
import pageobject.*;
import utils.BaseTest;

public class NavigationTests extends BaseTest {

    @Override
    protected boolean needsTestUser() {
        // Указываем, что для тестов требуется тестовый пользователь
        return true;
    }

    @Test
    @Description("Тест: вход по кнопке «Войти в аккаунт» на главной странице.")
    public void testLoginFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginPageOpened();
    }

    @Test
    @Description("Тест: вход через кнопку «Личный кабинет».")
    public void testLoginFromPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginPageOpened();
    }

    @Test
    @Description("Тест: вход через кнопку в форме регистрации.")
    public void testLoginFromRegistrationForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginPageOpened();
        loginPage.navigateToRegistrationPage();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginButton();
        loginPage.verifyLoginPageOpened();
    }

    @Test
    @Description("Тест: вход через кнопку в форме восстановления пароля.")
    public void testLoginFromForgotPasswordForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginPageOpened();
        loginPage.clickForgotPasswordButton();
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        passwordRecoveryPage.clickLoginButton();
        loginPage.verifyLoginPageOpened();
    }

    @Test
    @Description("Тест: переход в личный кабинет.")
    public void testNavigationToPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.clickPersonalAccountButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.verifyPersonalAccountPageOpened();
    }

    @Test
    @Description("Тест: переход из личного кабинета в конструктор.")
    public void testNavigationFromPersonalAccountToConstructor() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.clickPersonalAccountButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickConstructorButtonFromPersonalAccount();
        mainPage.verifyMainPageOpened();
    }

    @Test
    @Description("Тест: переход из личного кабинета по клику на логотип.")
    public void testNavigationFromPersonalAccountToLogo() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.clickPersonalAccountButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickLogoButtonSafely();
        mainPage.verifyMainPageOpened();
    }

    @Test
    @Description("Тест: выход по кнопке «Выйти» в личном кабинете.")
    public void testLogoutFromPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.clickPersonalAccountButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickLogoutButton();
        loginPage.verifyLoginPageOpened();
    }
}
