package PageModalObjectPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuthorizationPage extends DbPage {
    private String urlPage = "http://172.24.120.5:8081/login";
    private By loginTextField = By.id("login-input");
    private By passwordTextField = By.id("password-input");
    private By loginButton = By.cssSelector(".form_auth_button.btn.btn-primary:first-child");
    private WebDriver driver;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToAuthorizationPage() {
        driver.get(urlPage);
    }

    public void fillInLogin(String loginText) {
        driver.findElement(loginTextField).sendKeys(loginText);
    }

    public void fillInPassword(String passwordText) {
        driver.findElement(passwordTextField).sendKeys(passwordText);
        ;
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
