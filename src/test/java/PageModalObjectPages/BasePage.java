package PageModalObjectPages;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BasePage {
    public static AuthorizationPage authorizationPage;
    public static MainPage mainPage;
    public static WebDriver driver;

    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        authorizationPage = new AuthorizationPage(driver);
        mainPage = new MainPage(driver);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
