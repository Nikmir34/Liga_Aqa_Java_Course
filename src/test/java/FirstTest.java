import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class FirstTest {

    @Test
    public void testGoogle() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://www.google.ru/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q"))).sendKeys("Selenium Test");
        Thread.sleep(300);
        driver.findElement(By.name("btnK")).click();
        driver.quit();
    }

}
