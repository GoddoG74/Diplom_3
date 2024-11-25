package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private final WebDriver driver;

    private final By nameField = By.xpath("//*[@id='root']/div/main/div/form/fieldset[1]/div/div/input");
    private final By emailField = By.xpath("//*[@id='root']/div/main/div/form/fieldset[2]/div/div/input");
    private final By passwordField = By.xpath("//*[@id='root']/div/main/div/form/fieldset[3]/div/div/input");
    private final By registerButton = By.xpath("//*[@id='root']/div/main/div/form/button");
    private final By errorMessage = By.xpath("//*[@id='root']/div/main/div/form/fieldset[3]/div/p");
    private final By loginButton = By.xpath("//*[@id='root']/div/main/div/div/p/a");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
