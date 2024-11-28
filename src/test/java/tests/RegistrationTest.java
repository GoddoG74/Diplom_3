package tests;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import pageobject.*;
import pojos.User;
import utils.BaseTest;
import utils.TestDataGenerator;
import utils.UserHelper;

public class RegistrationTest extends BaseTest {

    private String name;
    private String email;
    private String password;

    @Override
    protected void setupTestData() {
        // Генерация данных для регистрации
        name = TestDataGenerator.generateName();
        email = TestDataGenerator.generateEmail();
        password = TestDataGenerator.generateValidPassword();
    }

    @Test
    @Description("Тест успешной регистрации нового пользователя")
    public void successfulRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegistrationPage();
        registrationPage.registerNewUser(name, email, password);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginPageAfterRegistration();
        loginPage.login(email, password);

        mainPage.clickPersonalAccountButton();

        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.verifyPersonalAccountPage(name, email);

        String token = getTokenFromLocalStorage();
        Assert.assertNotNull("Токен не был найден в localStorage после авторизации UI", token);

        User user = new User(email, password, name, token);
        UserHelper.deleteUser(user);
    }

    @Test
    @Description("Тест ошибки при регистрации пользователя с коротким паролем")
    public void registrationWithShortPasswordTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegistrationPage();

        String shortPassword = TestDataGenerator.generateShortPassword();
        registrationPage.registerNewUser(name, email, shortPassword);

        registrationPage.verifyPasswordError();
    }

    private String getTokenFromLocalStorage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return localStorage.getItem('accessToken');");
    }
}
