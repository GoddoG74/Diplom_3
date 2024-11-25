package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.MainPage;
import utils.BaseTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ConstructorSectionTests extends BaseTest {

    private final String browser;

    public ConstructorSectionTests(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Collection<Object[]> browsers() {
        return Arrays.asList(new Object[][]{
                {"chrome"}, // Запуск на Google Chrome
                {"yandex"}  // Запуск на Яндекс.Браузере
        });
    }

    @Before
    public void setUp() {
        setupBrowser(browser);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

    @Test
    @Description("Проверка перехода в раздел 'Булки'")
    public void checkBunsSection() {
        openMainPage();
        checkAndSwitchToSection("Булки");
    }

    @Test
    @Description("Проверка перехода в раздел 'Соусы'")
    public void checkSaucesSection() {
        openMainPage();
        checkAndSwitchToSection("Соусы");
    }

    @Test
    @Description("Проверка перехода в раздел 'Начинки'")
    public void checkFillingsSection() {
        openMainPage();
        checkAndSwitchToSection("Начинки");
    }

    @Step("Открытие главной страницы")
    private void openMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
           }

    @Step("Проверка и переход к разделу: {sectionName}")
    private void checkAndSwitchToSection(String sectionName) {
        MainPage mainPage = new MainPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Определяем локатор активного раздела
        By sectionLocator = mainPage.getSectionLocator(sectionName);

        // Проверяем, что элемент не активен
        if (isSectionActive(sectionLocator)) {
           switchToAnotherSection(sectionName, mainPage);
        }

        // Переход к тестируемому разделу
        clickOnSection(sectionName, mainPage);

        // Проверяем активацию раздела
        wait.until(ExpectedConditions.attributeContains(sectionLocator, "class", "tab_tab_type_current__2BEPc"));
        Assert.assertTrue("Раздел '" + sectionName + "' не активен", isSectionActive(sectionLocator));
    }

    @Step("Проверка, активен ли раздел")
    private boolean isSectionActive(By sectionLocator) {
        return driver.findElement(sectionLocator).getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    @Step("Переключение на другой раздел, если текущий уже активен")
    private void switchToAnotherSection(String currentSectionName, MainPage mainPage) {
        switch (currentSectionName.toLowerCase()) {
            case "начинки":
                mainPage.clickSaucesButton();
                break;
            case "соусы":
                mainPage.clickBunsButton();
                break;
            case "булки":
                mainPage.clickFillingsButton();
                break;
            default:
                throw new IllegalArgumentException("Неправильный раздел: " + currentSectionName);
        }
    }

    @Step("Клик по разделу: {sectionName}")
    private void clickOnSection(String sectionName, MainPage mainPage) {
        switch (sectionName.toLowerCase()) {
            case "начинки":
                mainPage.clickFillingsButton();
                break;
            case "соусы":
                mainPage.clickSaucesButton();
                break;
            case "булки":
                mainPage.clickBunsButton();
                break;
            default:
                throw new IllegalArgumentException("Неправильный раздел: " + sectionName);
        }
    }
}
