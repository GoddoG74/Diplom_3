package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.UrlConstants;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    // Локаторы
    private final By emailField = By.xpath("//form//fieldset[1]//input");
    private final By passwordField = By.xpath("//label[text()='Пароль']/following-sibling::input[@type='password' and @name='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerButton = By.xpath("//a[text()='Зарегистрироваться']");
    private final By forgotPasswordButton = By.xpath("//a[text()='Восстановить пароль']");


    // Конструктор
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы для ввода данных
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void clickForgotPasswordButton() {
        driver.findElement(forgotPasswordButton).click();
    }



    @Step("Вход с email: {email} и паролем: {password}")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public void verifyLoginPageAfterRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(UrlConstants.LOGIN_URL)); // Используем константу LOGIN_URL
        Assert.assertTrue("Не удалось перейти на страницу логина после регистрации.",
                driver.getCurrentUrl().equals(UrlConstants.LOGIN_URL)); // Используем константу LOGIN_URL
    }
    @Step("Переход на страницу регистрации")
    public void navigateToRegistrationPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        clickRegisterButton();
    }


    @Step("Проверка, что открыта страница логина")
    public void verifyLoginPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(UrlConstants.LOGIN_URL));
        Assert.assertTrue("Не удалось перейти на страницу логина.", driver.getCurrentUrl().equals(UrlConstants.LOGIN_URL));
    }


}
