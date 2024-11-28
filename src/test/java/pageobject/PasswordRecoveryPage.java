package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    private final WebDriver driver;

    // Локаторы
    private final By loginButton = By.xpath("//div/p/a[text()='Войти']"); // Кнопка "Войти"


    // Конструктор
    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

}
