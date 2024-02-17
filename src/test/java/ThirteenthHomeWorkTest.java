import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@DisplayName(value = "Домашнее задание урок №13")
public class ThirteenthHomeWorkTest {
    WebDriver driver;

    @BeforeAll
    public static void startTesting() {
        System.out.println("Начало тестирования");
    }

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Ввести значение в поле Логин
        driver.findElement(By.id("login-input")).sendKeys("nikmir");
        //Ввести значение в поле Пароль
        driver.findElement(By.id("password-input")).sendKeys("Qwerty$4xdd");
        //Кликаем на нопку Войти
        driver.findElement(By.cssSelector(".form_auth_button.btn.btn-primary:first-child")).click();
    }

    @Test
    @DisplayName(value = "Проверяем поле заголовок")
    public void checktitleTest() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
        try {
            //Получаем значение заголовка
            String titleText = driver.findElement(By.cssSelector(".Card_title__K3B5U")).getText();
        } catch (NoSuchElementException d) {
            //Обработка исключения
            System.out.println("Поле заголовкок не найдено");
        }
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