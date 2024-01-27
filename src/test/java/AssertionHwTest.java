import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import java.time.Duration;

@DisplayName(value = "Домашнее задание урок №5")
public class AssertionHwTest {
    WebDriver driver;

    @BeforeAll
    public static void startTesting() {
        System.out.println("Начало тестирования");
    }
    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
    }
    @Test
    @DisplayName(value = "Создание заметки, текста, выбор цвета, проверка заголовка")
    public void titleTest(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Ввести значение в поле Логин
        driver.findElement(By.id("login-input")).sendKeys("nikmir");
        //Ввести значение в поле Пароль
        driver.findElement(By.id("password-input")).sendKeys("Qwerty$4xdd");
        //Кликаем на нопку Войти
        driver.findElement(By.cssSelector(".form_auth_button.btn.btn-primary:first-child")).click();
        //Кликаем на нопку Создать заголовок
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("Card_containerNew__adAai"))).click();
        //Заполяем Заголовок
        driver.findElement(By.cssSelector(".ModalCard_cardBodyInput__ghZU0.modal-title")).sendKeys("Тут ничего нет xdd");
        //Заполняем Текст
        driver.findElement(By.id("note-modal-content-new_empty")).sendKeys("И тут тоже ничего нет ddx");
        //Кликаем на Палитру Цветов
        driver.findElement(By.id("palette-btn-new_empty")).click();
        //Выбираем Зеленый Цвет
        driver.findElement(By.cssSelector(".PalettePopover_colorOption__lno8b:nth-of-type(4)")).click();
        //Кликаем на кнопку Ок
        driver.findElement(By.id("note-modal-save-btn-new_empty")).click();
        //Получаем значение заголовка
        String titleText = driver.findElement(By.cssSelector(".Card_title__K3B5U")).getText();
        //Проверяем заголовок
        Assertions.assertEquals("Тут ничего нет xdd", titleText, "Неверный заголовок заметки");
    }

    @Test
    @DisplayName(value = "Проверка текста в заметке")
    public void textTest(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Ввести значение в поле Логин
        driver.findElement(By.id("login-input")).sendKeys("nikmir");
        //Ввести значение в поле Пароль
        driver.findElement(By.id("password-input")).sendKeys("Qwerty$4xdd");
        //Кликаем на нопку Войти
        driver.findElement(By.cssSelector(".form_auth_button.btn.btn-primary:first-child")).click();
        //Получаем значение текста
        String bodyText = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".Card_body__35O4X"))).getText();
        //Проверяем текст
        Assertions.assertEquals("И тут тоже ничего нет ddx", bodyText, "Неверный текст в заметке");
    }

    @Test
    @DisplayName(value = "Проверка цвета в заметке")
    public void colorTest(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Ввести значение в поле Логин
        driver.findElement(By.id("login-input")).sendKeys("nikmir");
        //Ввести значение в поле Пароль
        driver.findElement(By.id("password-input")).sendKeys("Qwerty$4xdd");
        //Кликаем на нопку Войти
        driver.findElement(By.cssSelector(".form_auth_button.btn.btn-primary:first-child")).click();
        //Получаем значение цвета
        String colorValue = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".Card_container__YjYSI"))).getCssValue("background-color");
        //Проверяем цвет
        Assertions.assertEquals("rgba(204, 255, 144, 1)", colorValue, "Неверный цвет в заметке");
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
