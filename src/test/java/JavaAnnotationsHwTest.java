import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@DisplayName(value = "Домашнее задание №3")
public class JavaAnnotationsHwTest {
    WebDriver driver;

    @BeforeAll
    public static void startTesting() {
        System.out.println("Начало тестирования ");
    }
    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
    }

    @Test
    @DisplayName(value = "Тест регистрации")
    public void registrationTest(){
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Кликаем на нопку Зарегистрироваться
        driver.findElement(By.id("form_register_button")).click();
        //Ввести значение в поле Логин
        driver.findElement(By.xpath("//input[@placeholder='Логин']")).sendKeys("nikmir");
        //Ввести значение в поле Пароль
        driver.findElement(By.xpath("//input[@placeholder='Пароль']")).sendKeys("Qwerty$4xdd");
        //Кликаем по кнопке Создать
        driver.findElement(By.xpath("//button[text()='Создать']")).click();
    }

    @Test
    @DisplayName(value = "Тест авторизации")
    public void authorizationTest(){
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Ввести значение в поле Логин
        driver.findElement(By.id("login-input")).sendKeys("nikmir");
        //Ввести значение в поле Пароль
        driver.findElement(By.id("password-input")).sendKeys("Qwerty$4xdd");
        //Кликаем на нопку Войти
        driver.findElement(By.cssSelector(".form_auth_button.btn.btn-primary:first-child")).click();
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
