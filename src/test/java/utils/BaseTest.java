package utils;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pojos.User;

import java.util.List;

@RunWith(Parameterized.class)
public abstract class BaseTest {

    protected WebDriver driver;
    protected User testUser;

    @Parameterized.Parameter
    public String browser;

    @Parameterized.Parameters(name = "Browser: {0}")
    public static List<String> getBrowsers() {
        return ConfigReader.getBrowserList();
    }

    @Before
    public void setup() {
        // Настройка драйвера для текущего браузера
        System.out.println("Setting up tests on browser: " + browser);
        driver = WebDriverFactory.createDriver(browser);
        driver.manage().window().maximize();

        // Настройка данных для теста
        setupTestData();

        // Создание тестового пользователя, если требуется
        if (needsTestUser()) {
            createTestUser();
        }
    }

    @After
    public void tearDown() {
        // Удаление тестового пользователя, если он был создан
        if (needsTestUser()) {
            deleteTestUser();
        }

        // Закрытие драйвера
        if (driver != null) {
            driver.quit();
        }
    }

    protected void setupTestData() {
        // По умолчанию ничего не делаем
    }

    /**
     * Определяет, нужен ли тестовый пользователь для текущего теста.
     */
    protected boolean needsTestUser() {
        return false; // По умолчанию пользователь не нужен
    }

    /**
     * Создание тестового пользователя.
     */
    protected void createTestUser() {
        testUser = UserHelper.createUser();
    }

    /**
     * Удаление тестового пользователя.
     */
    protected void deleteTestUser() {
        if (testUser != null) {
            UserHelper.deleteUser(testUser);
            testUser = null;
        }
    }
}
