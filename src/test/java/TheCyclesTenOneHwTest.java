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
    public void createNotes(){
        for (int i=0; i<3; i++){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            //Кликаем на нопку Создать заголовок
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("Card_containerNew__adAai"))).click();
            //Заполяем Заголовок
            driver.findElement(By.cssSelector(".ModalCard_cardBodyInput__ghZU0.modal-title")).sendKeys("Заметка № " + (i + 1));
            //Заполняем Текст
            driver.findElement(By.id("note-modal-content-new_empty")).sendKeys("Текст заметки № " + (i + 1));
            //Кликаем на кнопку Ок
            driver.findElement(By.id("note-modal-save-btn-new_empty")).click();
            driver.navigate().refresh();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            // Получаем id Заголовка заметки
            String getNotes = driver.findElement(By.xpath("//div[contains(@id,'note-container')][last()]//p")).getAttribute("id");
            getNotes = getNotes.substring(11);
            int title = Integer.parseInt(getNotes.trim());
            int titles = title;
            String oldTitles = driver.findElement(By.id("note-title-" + titles)).getText();
            // Получаем id Текста заметки
            String getText = driver.findElement(By.xpath("//div[contains(@id,'note-container')][last()]//div")).getAttribute("id");
            getText = getText.substring(13);
            int text = Integer.parseInt(getText.trim());
            int textex = text;
            String oldText = driver.findElement(By.cssSelector("#note-content-" + textex + ":nth-child(2)")).getText();
            System.out.printf("Старая заметка №" + (i + 1) + ": \nЗаголовок: %s\t\nТекст: %s\n\n",oldTitles, oldText);

            driver.navigate().refresh();
            driver.findElement(By.id("note-edit-btn-" + titles)).click();

            driver.findElement(By.id("note-modal-title-" + titles)).click();
            driver.findElement(By.id("note-modal-title-" + titles)).clear();
            driver.findElement(By.id("note-modal-title-" + titles)).sendKeys("Новая заметка №" + (i + 1));
            driver.findElement(By.id("note-modal-content-" + titles)).click();
            driver.findElement(By.id("note-modal-content-" + titles)).clear();
            driver.findElement(By.id("note-modal-content-" + titles)).sendKeys("Текст новой заметки №" + (i + 1));

            driver.findElement(By.id("note-modal-save-btn-" + titles)).click();
            String newTitles = driver.findElement(By.id("note-title-" + titles)).getText();
            String newText = driver.findElement(By.cssSelector("#note-content-" + textex + ":nth-child(2)")).getText();
            System.out.printf("Новая заметка №" + (i + 1) + ": \nЗаголовок: %s\t\nТекст: %s\n\n",newTitles, newText);


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
