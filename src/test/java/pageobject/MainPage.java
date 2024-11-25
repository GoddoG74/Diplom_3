package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;

    // Локаторы
    private final By personalAccountButton = By.xpath("//*[@id='root']/div/header/nav/a"); // Кнопка "Личный кабинет"
    private final By loginButton = By.xpath("//*[@id='root']/div/main/section[2]/div/button"); // Кнопка "Войти в аккаунт"
    private final By constructorButton = By.xpath("//*[@id='root']/div/header/nav/ul/li[1]/a/p"); // Кнопка "Конструктор"
    private final By bunsButton = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[1]/span"); // Кнопка "Булки"
    private final By saucesButton = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[2]/span"); // Кнопка "Соусы"
    private final By fillingsButton = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[3]/span"); // Кнопка "Начинки"
    private final By logoButton = By.xpath("//*[@id='root']/div/header/nav/div/a/svg"); // Логотип

    // Локаторы для активных разделов
    private final By bunsSection = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[1]");
    private final By saucesSection = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[2]");
    private final By fillingsSection = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[3]");

    // Конструктор
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы для взаимодействия с кнопками
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    public void clickSaucesButton() {
        driver.findElement(saucesButton).click();
    }

    public void clickFillingsButton() {
        driver.findElement(fillingsButton).click();
    }

    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

    // Методы для получения локаторов для активных разделов
    public By getSectionLocator(String sectionName) {
        switch (sectionName.toLowerCase()) {
            case "начинки":
                return fillingsSection;
            case "соусы":
                return saucesSection;
            case "булки":
                return bunsSection;
            default:
                throw new IllegalArgumentException("Неправильный раздел: " + sectionName);
        }
    }
    public By getPersonalAccountButtonLocator() {
        return personalAccountButton;
    }


    // Получение класса активного раздела
    public String getSectionClass(String sectionName) {
        switch (sectionName.toLowerCase()) {
            case "начинки":
                return driver.findElement(fillingsSection).getAttribute("class");
            case "соусы":
                return driver.findElement(saucesSection).getAttribute("class");
            case "булки":
                return driver.findElement(bunsSection).getAttribute("class");
            default:
                throw new IllegalArgumentException("Неправильный раздел: " + sectionName);
        }
    }
}
