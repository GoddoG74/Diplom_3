package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private final WebDriver driver;

    // Локаторы
    private final By nameField = By.xpath("//*[@id='root']/div/main/div/div/div/ul/li[1]/div/div/input"); // Поле "Имя"
    private final By loginField = By.xpath("//*[@id='root']/div/main/div/div/div/ul/li[2]/div/div/input"); // Поле "Логин"
    private final By passwordField = By.xpath("//*[@id='root']/div/main/div/div/div/ul/li[3]/div/div/input"); // Поле "Пароль"
    private final By cancelButton = By.xpath("//*[@id='root']/div/main/div/div/div/div/button[1]"); // Кнопка "Отмена"
    private final By saveButton = By.xpath("//*[@id='root']/div/main/div/div/div/div/button[2]"); // Кнопка "Сохранить"
    private final By profileButton = By.xpath("//*[@id='root']/div/main/div/nav/ul/li[1]/a"); // Кнопка "Профиль"
    private final By logoutButton = By.xpath("//button[contains(text(), 'Выход')]"); // Кнопка "Выход"
    private final By constructorButton = By.xpath("//*[@id='root']/div/header/nav/ul/li[1]/a/p"); // Кнопка "Конструктор"
    private final By logoButton = By.xpath("//*[@id='root']/div/header/nav/div/a"); // Кнопка "Логотип"
    private final By orderHistoryButton = By.xpath("//*[@id='root']/div/main/div/nav/ul/li[2]/a");

    // Конструктор
    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы для получения текста полей
    public String getName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        return nameElement.getAttribute("value").trim(); // Получаем текст из атрибута "value"
    }

    public String getLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loginField));
        return loginElement.getAttribute("value").trim(); // Получаем текст из атрибута "value"
    }

    public String getPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        return passwordElement.getAttribute("value").trim(); // Получаем текст из атрибута "value"
    }

    // Методы для взаимодействия с кнопками
    public void clickCancelButton() {
        driver.findElement(cancelButton).click();
    }

    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    public void clickProfileButton() {
        driver.findElement(profileButton).click();
    }

    public void clickOrderHistoryButton() {
        driver.findElement(orderHistoryButton).click();
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }
    public WebElement getLogoutButton() {
        return driver.findElement(logoutButton);
    }


    // Новый метод для безопасного клика по логотипу
    public void clickLogoButtonSafely() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement logoElement = wait.until(ExpectedConditions.elementToBeClickable(logoButton));
        try {
            logoElement.click();
        } catch (Exception e) {
            // Если стандартный клик не сработал, используем альтернативный способ
            Actions actions = new Actions(driver);
            actions.moveToElement(logoElement).click().perform();
        }
    }

    // Метод для проверки видимости кнопки логотипа
    public boolean isLogoButtonVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(logoButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public By getLogoutButtonLocator() {
        return logoutButton;
    }


    // Методы для получения локаторов
    public By getNameFieldLocator() {
        return nameField;
    }

    public By getLoginFieldLocator() {
        return loginField;
    }

    public By getPasswordFieldLocator() {
        return passwordField;
    }
}
