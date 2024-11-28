package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.UrlConstants;

import java.time.Duration;

public class PersonalAccountPage {
    private final WebDriver driver;

    // Локаторы
    private final By nameField = By.xpath("//ul/li[1]//input"); // Поле "Имя"
    private final By emailField = By.xpath("//ul/li[2]//input"); // Поле "Логин"
    private final By passwordField = By.xpath("//ul/li[3]//input"); // Поле "Пароль"
    private final By cancelButton = By.xpath("//button[text()='Отмена']"); // Кнопка "Отмена"
    private final By saveButton = By.xpath("//button[text()='Сохранить']"); // Кнопка "Сохранить"
    private final By profileButton = By.xpath("//nav/ul/li[1]/a"); // Кнопка "Профиль"
    private final By logoutButton = By.xpath("//button[text()='Выход']");   // Кнопка "Выход"
    private final By constructorButton = By.xpath("//nav/ul/li[1]/a/p"); // Кнопка "Конструктор"
    private final By logoButton = By.xpath("//header/nav/div/a"); // Кнопка "Логотип"
    private final By orderHistoryButton = By.xpath("//nav/ul/li[2]/a"); // Кнопка "История заказов"

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
        WebElement loginElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        return loginElement.getAttribute("value").trim(); // Получаем текст из атрибута "value"
    }

    public String getPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        return passwordElement.getAttribute("value").trim(); // Получаем текст из атрибута "value"
    }



    public void clickLogoutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)); // Ожидание, пока кнопка не станет кликабельной
        driver.findElement(logoutButton).click(); // Клик по кнопке
    }


    // Новый метод для безопасного клика по логотипу
    public void clickLogoButtonSafely() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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

    public void verifyPersonalAccountPage(String expectedName, String expectedEmail) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(UrlConstants.PERSONAL_ACCOUNT_URL)); // Используем константу PERSONAL_ACCOUNT_URL

        Assert.assertEquals("Имя пользователя не совпадает.", expectedName, getName());
        Assert.assertEquals("Email пользователя не совпадает.", expectedEmail, getLogin());
    }
    @Step("Клик по кнопке «Конструктор» из личного кабинета")
    public void clickConstructorButtonFromPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickConstructorButton();
    }

    @Step("Проверка, что открыта страница личного кабинета")
    public void verifyPersonalAccountPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(UrlConstants.PERSONAL_ACCOUNT_URL));
        Assert.assertTrue("Не удалось перейти в личный кабинет.", driver.getCurrentUrl().equals(UrlConstants.PERSONAL_ACCOUNT_URL));
    }


}
