package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;

public class WebDriverFactory {

    // Читаем путь к Yandex Browser из файла application.properties
    private static final String YANDEX_BROWSER_PATH = ConfigReader.getProperty("yandex.browser.path");

    public static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return createChromeDriver();

            case "yandex":
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
        if (YANDEX_BROWSER_PATH == null || YANDEX_BROWSER_PATH.isEmpty()) {
            throw new IllegalArgumentException("Yandex Browser path is not provided in application.properties.");
        }

        File yandexBinary = new File(YANDEX_BROWSER_PATH);
        if (!yandexBinary.exists()) {
            throw new IllegalArgumentException("Yandex Browser binary not found at: " + YANDEX_BROWSER_PATH);
        }


        WebDriverManager.chromedriver().driverVersion("128.0").setup();

        ChromeOptions yandexOptions = new ChromeOptions();
        yandexOptions.setBinary(yandexBinary);
        yandexOptions.addArguments("--remote-allow-origins=*");

        return new ChromeDriver(yandexOptions);
    }
}
