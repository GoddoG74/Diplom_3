package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.UrlConstants;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;

    // Локаторы
    private final By nameField = By.xpath("//input[@name='name']");
    private final By emailField = By.xpath("//form//fieldset[2]//input");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By errorMessage = By.xpath("//p[contains(@class, 'input__error')]");
    private final By loginButton = By.xpath("//a[text()='Войти']");

    // Конструктор
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Степы из теста
    @Step("Регистрация нового пользователя: имя - {name}, email - {email}")
    public void registerNewUser(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    @Step("Проверка ошибки для короткого пароля")
    public void verifyPasswordError() {
        String errorMessage = getErrorMessage();
        Assert.assertTrue("Ошибка не отображена или неверная.",
                errorMessage.contains("Некорректный пароль"));
    }

    @Step("Открытие страницы регистрации")
    public void openRegistrationPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(UrlConstants.REGISTRATION_URL);
    }
}
