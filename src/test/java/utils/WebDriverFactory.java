package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;

public class WebDriverFactory {

    public static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                // Настройка WebDriverManager для Chrome
                WebDriverManager.chromedriver().setup();
                return createChromeDriver();

            case "yandex":
                // Настройка WebDriverManager для Chrome, который совместим с Yandex Browser
                WebDriverManager.chromedriver().driverVersion("128.0").setup();
                return createYandexDriver();

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(chromeOptions);
    }

    private static WebDriver createYandexDriver() {
        ChromeOptions yandexOptions = new ChromeOptions();

        // Указываем путь к Yandex Browser
        String yandexBrowserPath = System.getProperty("user.home")
                + "/AppData/Local/Yandex/YandexBrowser/Application/browser.exe";
        yandexOptions.setBinary(new File(yandexBrowserPath));

        // Добавляем нужные аргументы для работы
        yandexOptions.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(yandexOptions);
    }
}
