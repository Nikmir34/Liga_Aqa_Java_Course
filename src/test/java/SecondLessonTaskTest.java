import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SecondLessonTaskTest {

    @Test
    public void testHomeWork() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().setSize(new Dimension(1980, 1080));
        driver.get("http://172.24.120.5:8081/login");
        String authorizationLabelText = driver.findElement(By.className("form_auth_block_head_text")).getText();
        driver.findElement(By.id("login-input")).sendKeys(authorizationLabelText);
        String loginLabelText = driver.findElement(By.id("login-input")).getAttribute("value");
        driver.findElement(By.id("password-input")).sendKeys(loginLabelText);
        driver.findElement(By.id("password-input")).clear();
        String authText = driver.findElement(By.cssSelector(".form_auth_button.btn.btn-primary:first-child")).getCssValue("cursor");
        driver.findElement(By.id("password-input")).sendKeys(authText);
        driver.quit();
    }

}
