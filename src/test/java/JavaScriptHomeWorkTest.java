import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

@DisplayName(value = "Домашнее задание урок №14")
public class JavaScriptHomeWorkTest {
    WebDriver driver;

    @BeforeAll
    public static void startTesting() {
        System.out.println("Начало тестирования");
    }

    @Test
    @DisplayName(value = "JS Тест")
    public void javaScript() throws InterruptedException {
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Создание объекта интерфейса JavaScriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Ввести значение в поле Логин
        driver.findElement(By.id("login-input")).sendKeys("nikmir");
        //Ввести значение в поле Пароль
        driver.findElement(By.id("password-input")).sendKeys("Qwerty$4xdd");
        //Значение кнопки Войти
        WebElement loginButton = driver.findElement(By.cssSelector(".form_auth_button.btn.btn-primary:first-child"));
        //Нажимаем на кнопку Войти через JS
        js.executeScript("arguments[0].click();", loginButton);
        //Получем аттрибут href у кнопки Swagger
        String swagger =  driver.findElement(By.cssSelector(".Header_linkText__CbHIn:nth-child(1)")).getAttribute("href");
        // Открываем сайт лиги
        js.executeScript("window.location = 'https://www.digitalleague.ru/'");
        // Открываем в новой вкладке сайт из кнопки Swagger
        js.executeScript("window.open('"+swagger+"', '_blank');");
        // Переключаемся на 2 вкладку
        ArrayList tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window((String) tabs.get(1));
        // Скроллим страницу до конца вниз
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("model-UserResponseDto")));
        js.executeScript("window.scrollBy(0,3500)");
        Thread.sleep(1000);
    }


    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

    @AfterAll
    public static void endTesting() {
        System.out.println("Окончание тестирования");
    }
}
