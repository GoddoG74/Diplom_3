package utils;

import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver;

    // Настройка браузера
    public void setupBrowser(String browser) {
        driver = WebDriverFactory.createDriver(browser);
        driver.manage().window().maximize();
    }

    // Закрытие браузера
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
