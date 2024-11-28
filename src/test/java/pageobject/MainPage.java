package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.UrlConstants;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    // Локаторы
    private final By personalAccountButton = By.xpath("//nav/a"); // Кнопка "Личный кабинет"
    private final By loginButton = By.xpath("//section[2]/div/button"); // Кнопка "Войти в аккаунт"
    private final By constructorButton = By.xpath("//nav/ul/li[1]/a/p"); // Кнопка "Конструктор"
    private final By bunsButton = By.xpath("//section[1]/div[1]/div[1]/span"); // Кнопка "Булки"
    private final By saucesButton = By.xpath("//section[1]/div[1]/div[2]/span"); // Кнопка "Соусы"
    private final By fillingsButton = By.xpath("//section[1]/div[1]/div[3]/span"); // Кнопка "Начинки"
    private final By logoButton = By.xpath("//nav/div/a/svg"); // Логотип

    private final By bunsSection = By.xpath("//section[1]/div[1]/div[1]"); // Раздел "Булки"
    private final By saucesSection = By.xpath("//section[1]/div[1]/div[2]"); // Раздел "Соусы"
    private final By fillingsSection = By.xpath("//section[1]/div[1]/div[3]"); // Раздел "Начинки"

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Открытие главной страницы
    @Step("Открытие главной страницы")
    public void openMainPage() {
        driver.get(UrlConstants.BASE_URL);
    }

    // Нажатие на кнопку "Личный кабинет"
    @Step("Нажатие на кнопку «Личный кабинет»")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    // Нажатие на кнопку "Войти в аккаунт"
    @Step("Нажатие на кнопку «Войти в аккаунт»")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Нажатие на кнопку "Конструктор"
    @Step("Нажатие на кнопку «Конструктор»")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    // Нажатие на кнопку "Булки"
    @Step("Нажатие на кнопку «Булки»")
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    // Нажатие на кнопку "Соусы"
    @Step("Нажатие на кнопку «Соусы»")
    public void clickSaucesButton() {
        driver.findElement(saucesButton).click();
    }

    // Нажатие на кнопку "Начинки"
    @Step("Нажатие на кнопку «Начинки»")
    public void clickFillingsButton() {
        driver.findElement(fillingsButton).click();
    }


    // Переход к указанному разделу конструктора
    @Step("Переход к разделу: {sectionName}")
    public void switchToSection(String sectionName) {
        By sectionLocator = getSectionLocator(sectionName);
        if (isSectionActive(sectionLocator)) {
            switchToAnotherSection(sectionName);
        }
        clickOnSection(sectionName);
    }

    // Проверка, что раздел активен
    @Step("Проверка, что раздел активен: {sectionName}")
    public void verifySectionActive(String sectionName) {
        By sectionLocator = getSectionLocator(sectionName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.attributeContains(sectionLocator, "class", "tab_tab_type_current__2BEPc"));
        assert isSectionActive(sectionLocator) : "Раздел '" + sectionName + "' не активен";
    }

    @Step("Проверка перехода на главную страницу")
    public void verifyMainPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(UrlConstants.BASE_URL));
        Assert.assertEquals("Не удалось перейти на главную страницу.", UrlConstants.BASE_URL, driver.getCurrentUrl());
    }


    // Получение локатора для раздела конструктора
    public By getSectionLocator(String sectionName) {
        switch (sectionName.toLowerCase()) {
            case "булки":
                return bunsSection;
            case "соусы":
                return saucesSection;
            case "начинки":
                return fillingsSection;
            default:
                throw new IllegalArgumentException("Неверное имя секции: " + sectionName);
        }
    }

    // Проверка, активен ли раздел
    private boolean isSectionActive(By sectionLocator) {
        return driver.findElement(sectionLocator).getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    // Переход к другому разделу, если текущий уже активен
    private void switchToAnotherSection(String currentSectionName) {
        switch (currentSectionName.toLowerCase()) {
            case "начинки":
                clickSaucesButton();
                break;
            case "соусы":
                clickBunsButton();
                break;
            case "булки":
                clickFillingsButton();
                break;
            default:
                throw new IllegalArgumentException("Неправильный раздел: " + currentSectionName);
        }
    }

    // Клик по указанному разделу
    private void clickOnSection(String sectionName) {
        switch (sectionName.toLowerCase()) {
            case "начинки":
                clickFillingsButton();
                break;
            case "соусы":
                clickSaucesButton();
                break;
            case "булки":
                clickBunsButton();
                break;
            default:
                throw new IllegalArgumentException("Неправильный раздел: " + sectionName);
        }
    }


}
