package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    // Локаторы
    private final By emailField = By.xpath("//*[@id='root']/div/main/div/form/fieldset[1]/div/div/input"); // Поле для email
    private final By passwordField = By.xpath("//*[@id='root']/div/main/div/form/fieldset[2]/div/div/input"); // Поле для пароля
    private final By loginButton = By.xpath("//*[@id='root']/div/main/div/form/button"); // Кнопка "Войти"
    private final By registerButton = By.xpath("//*[@id='root']/div/main/div/div/p[1]/a"); // Кнопка "Регистрация"
    private final By forgotPasswordButton = By.xpath("//*[@id='root']/div/main/div/div/p[2]/a"); // Кнопка "Восстановить пароль"
    private final By personalAccountButton = By.xpath("//*[@id='root']/div/header/nav/a/p"); // Кнопка "Личный кабинет"
    private final By constructorButton = By.xpath("//*[@id='root']/div/header/nav/ul/li[1]/a/p"); // Кнопка "Конструктор"
    private final By logoButton = By.xpath("//*[@id='root']/div/header/nav/div/a/svg"); // Кнопка "Логотип"

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

    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

    // Методы для получения локаторов
    public By getEmailFieldLocator() {
        return emailField;
    }

    public By getPasswordFieldLocator() {
        return passwordField;
    }

    public By getLoginButtonLocator() {
        return loginButton;
    }

    public By getRegisterButtonLocator() {
        return registerButton;
    }

    public By getForgotPasswordButtonLocator() {
        return forgotPasswordButton;
    }

    public By getPersonalAccountButtonLocator() {
        return personalAccountButton;
    }

    public By getConstructorButtonLocator() {
        return constructorButton;
    }

    public By getLogoButtonLocator() {
        return logoButton;
    }
}
