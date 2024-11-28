package tests;

import io.qameta.allure.Description;
import org.junit.Test;
import pageobject.MainPage;
import utils.BaseTest;

public class ConstructorSectionTests extends BaseTest {

    private MainPage mainPage;

    @Test
    @Description("Проверка перехода в раздел 'Булки'")
    public void checkBunsSection() {
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.switchToSection("Булки");
        mainPage.verifySectionActive("Булки");
    }

    @Test
    @Description("Проверка перехода в раздел 'Соусы'")
    public void checkSaucesSection() {
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.switchToSection("Соусы");
        mainPage.verifySectionActive("Соусы");
    }

    @Test
    @Description("Проверка перехода в раздел 'Начинки'")
    public void checkFillingsSection() {
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.switchToSection("Начинки");
        mainPage.verifySectionActive("Начинки");
    }
}
