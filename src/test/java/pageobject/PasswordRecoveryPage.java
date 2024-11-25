package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    private final WebDriver driver;

    // Локаторы
    private final By emailField = By.xpath("//*[@id='root']/div/main/div/form/fieldset/div/div/input"); // Поле для email
    private final By recoverButton = By.xpath("//*[@id='root']/div/main/div/form/button"); // Кнопка "Восстановить"
    private final By loginButton = By.xpath("//*[@id='root']/div/main/div/div/p/a"); // Кнопка "Войти"
    private final By personalAccountButton = By.xpath("//*[@id='root']/div/header/nav/a/p"); // Кнопка "Личный кабинет"
    private final By constructorButton = By.xpath("//*[@id='root']/div/header/nav/ul/li[1]/a/p"); // Кнопка "Конструктор"
    private final By logoButton = By.xpath("//*[@id='root']/div/header/nav/div/a/svg"); // Кнопка "Логотип"

    // Конструктор
    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickRecoverButton() {
        driver.findElement(recoverButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
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
}
