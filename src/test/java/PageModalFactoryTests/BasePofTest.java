package PageModalFactoryTests;

import PageModalFactoryPages.AuthorizationPofPage;
import PageModalFactoryPages.DbPofPage;
import PageModalFactoryPages.GeneratePofPage;
import PageModalFactoryPages.MainPofPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BasePofTest {
    public static AuthorizationPofPage authorizationPofPage;
    public static MainPofPage mainPofPage;
    public static DbPofPage dbPofPage;
    public static GeneratePofPage generatePofPage;
    public static WebDriver driver;

    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        authorizationPofPage = new AuthorizationPofPage(driver);
        dbPofPage = new DbPofPage(driver);
        generatePofPage = new GeneratePofPage(driver);
        mainPofPage = new MainPofPage(driver);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
