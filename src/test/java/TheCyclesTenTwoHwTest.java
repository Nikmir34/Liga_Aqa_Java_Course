import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TheCyclesTenTwoHwTest {
    WebDriver driver;

    private int countNote(){
        List<WebElement> notes = driver.findElements(By.xpath("//div[contains(@id,'note-container')]"));
        return notes.size();
    }

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
    @DisplayName(value = "Домашнее задание №10-2")
    public void createNotes() {
        for (int j = countNote(); j < countNote()+3; j++) {
            for (int i = 1; i <= countNote(); i++) {
                if (countNote() == 0) break;
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                // Получаем id Заголовка заметки
                List<WebElement> getNotes = driver.findElements(By.xpath("//div[contains(@id,'note-container-')]//p"));
                String oldTitle = getNotes.get(j - 1).getText();
                System.out.printf("Итерация:%d\nСтарый заголовок: %s\n", i - 1, oldTitle);
                // Получаем Values Кнопки редактирования
                List<WebElement> editButton = driver.findElements(By.xpath("//img[contains(@id,'note-edit-btn')]"));
                editButton.get(j - 1).click();
                driver.findElement(By.xpath("//div[contains(@id,'note-modal-title-')]")).click();
                driver.findElement(By.xpath("//div[contains(@id,'note-modal-title-')]")).clear();
                driver.findElement(By.xpath("//div[contains(@id,'note-modal-title-')]")).sendKeys("Новая заметка №" + i);
                driver.findElement(By.xpath("//button[contains(@id,'note-modal-save-btn-')]")).click();
                String newTitle = getNotes.get(i - 1).getText();
                System.out.printf("Новый заголовок: %s\n\n", newTitle);

            }
            if (countNote() != 0) break;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            //Кликаем на нопку Создать заголовок
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("Card_containerNew__adAai"))).click();
            //Заполяем Заголовок
            driver.findElement(By.cssSelector(".ModalCard_cardBodyInput__ghZU0.modal-title")).sendKeys("Заметка № " + (j + 1));
            //Кликаем на кнопку Ок
            driver.findElement(By.id("note-modal-save-btn-new_empty")).click();
            driver.navigate().refresh();
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

