package PageModalObjectTests;

import PageModalObjectPages.AuthorizationPage;
import PageModalObjectPages.DbPage;
import PageModalObjectPages.GeneratorPage;
import PageModalObjectPages.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    public static AuthorizationPage authorizationPage;
    public static MainPage mainPage;
    public static DbPage dbPage;
    public static GeneratorPage generatorPage;
    public static WebDriver driver;

    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        authorizationPage = new AuthorizationPage(driver);
        dbPage = new DbPage(driver);
        generatorPage = new GeneratorPage(driver);
        mainPage = new MainPage(driver);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
