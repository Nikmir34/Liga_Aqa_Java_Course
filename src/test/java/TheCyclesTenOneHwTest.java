import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@DisplayName(value = "Домашнее задание урок №10-1")
public class TheCyclesTenOneHwTest {
    WebDriver driver;

    @BeforeAll
    public static void startTesting() {
        System.out.println("Начало тестирования");
    }

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
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
    @DisplayName(value = "Новая + Старая заметки")
    public void createNotes() {
        for (int i = 0; i < 3; i++) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            // Кликаем на нопку Создать заголовок
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("Card_containerNew__adAai"))).click();
            //Заполяем Заголовок
            driver.findElement(By.cssSelector(".ModalCard_cardBodyInput__ghZU0.modal-title")).sendKeys("Заметка № " + (i + 1));
            //Заполняем Текст
            driver.findElement(By.id("note-modal-content-new_empty")).sendKeys("Текст заметки № " + (i + 1));
            // Кликаем на кнопку Ок
            driver.findElement(By.id("note-modal-save-btn-new_empty")).click();
            // Рефреш страницы
            driver.navigate().refresh();
            // Ожидание
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            // Получаем id Заголовка заметки
            String getNotes = driver.findElement(By.xpath("//div[contains(@id,'note-container')][last()]//p")).getAttribute("id");
            // Обрезаем символы
            getNotes = getNotes.substring(11);
            // Переводим в int
            int title = Integer.parseInt(getNotes.trim());
            // Id заголовка
            String oldTitles = driver.findElement(By.id("note-title-" + title)).getText();
            // Получаем id Текста заметки
            String getText = driver.findElement(By.xpath("//div[contains(@id,'note-container')][last()]//div")).getAttribute("id");
            // Обрезаем символы
            getText = getText.substring(13);
            // Переводим в int
            int text = Integer.parseInt(getText.trim());
            String oldText = driver.findElement(By.cssSelector("#note-content-" + text + ":nth-child(2)")).getText();
            // Выводим данные старых заметок в консоль
            System.out.printf("Старая заметка №" + (i + 1) + ": \nЗаголовок: %s\t\nТекст: %s\n\n", oldTitles, oldText);
            // Рефреш страницы
            driver.navigate().refresh();
            // Клик кнопки редактрование
            driver.findElement(By.id("note-edit-btn-" + title)).click();
            // Клик поля Заголовок заметки
            driver.findElement(By.id("note-modal-title-" + title)).click();
            // Чистка поля Заголовок заметки
            driver.findElement(By.id("note-modal-title-" + title)).clear();
            // Заполняем поле Заголовок заметки
            driver.findElement(By.id("note-modal-title-" + title)).sendKeys("Новая заметка №" + (i + 1));
            // Клик на поле Тело заметки
            driver.findElement(By.id("note-modal-content-" + title)).click();
            // Чистка поля Тело заметки
            driver.findElement(By.id("note-modal-content-" + title)).clear();
            // Заполняем поле Тело заметки
            driver.findElement(By.id("note-modal-content-" + title)).sendKeys("Текст новой заметки №" + (i + 1));
            // Клик на кнопку Сохранить
            driver.findElement(By.id("note-modal-save-btn-" + title)).click();
            String newTitles = driver.findElement(By.id("note-title-" + title)).getText();
            String newText = driver.findElement(By.cssSelector("#note-content-" + text + ":nth-child(2)")).getText();
            // Выводим данные новых заметок в консоль
            System.out.printf("Новая заметка №" + (i + 1) + ": \nЗаголовок: %s\t\nТекст: %s\n\n", newTitles, newText);
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
