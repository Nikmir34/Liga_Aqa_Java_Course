import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class DzTenTwo {
    WebDriver driver;

    private int countNote() {
        List<WebElement> notes = driver.findElements(By.xpath("//div[contains(@id,'note-container')]"));
        return notes.size();
    }

    @BeforeEach
    public void openStendInitDriver() {
        //Объявление драйвера браузера
        driver = new ChromeDriver();
        // Ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Ввод логина
        driver.findElement(By.id("login-input")).sendKeys("nikmir");
        //Ввод пароля
        driver.findElement(By.id("password-input")).sendKeys("Qwerty$4xdd");
        //Клик по кнопке Войти
        driver.findElement(By.id("form_auth_button")).click();
    }

    @Test
    @DisplayName(value = "Домашнее задание - Урок 10-2: Java:Циклы. Операторы continue и break")
    public void createOrEditNoteTest() {
        // Количество уже созданных заметок
        int count=countNote();
        // Цикл создания заметок
        for (int j = count; j < count+5; j++) {
            //Вложенный цикл редактирования заметок
            for (int i = 1; i <= count; i++) {
                //Прервать цикл если заметок нет
                if (count == 0) break;
                //Объявляем WebDriverWait
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                //Получаем список всех заголовков заметок
                List<WebElement> noteList = driver.findElements(By.xpath("//*[contains(@id,'note-title-')]"));
                //Получаем текст заголовка  заметки
                String titleText = noteList.get(i-1).getText();
                //Выводим в консоль текст заголовка заметки до редактирования
                System.out.printf("Итерация:%d\tДО:    Заголовок:%s\n",i, titleText);
                //Получаем список всех кнопок редактирования заметок
                List<WebElement> elementList = driver.findElements(By.xpath("//*[contains(@id,'note-edit-btn')]"));
                //Клик на редактирование заметки
                elementList.get(i-1).click();
                //Очищение заголовка заметки
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'note-modal-title')]"))).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'note-modal-title')]"))).clear();
                //Ввод нового заголовка заметки
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'note-modal-title')]"))).sendKeys("Редактированная заметка номер " + i);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'note-modal-save-btn')]"))).click();
                //Получение текста нового заголовка заметки
                titleText = noteList.get(i-1).getText();
                //Вывод в консоль текста заголовка заметки после редактирования
                System.out.printf("            ПОСЛЕ: Заголовок:%s\n\n", titleText);
            }
            //Прервать если заметки есть
            if (count != 0) break;
            //Объявляем WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            //Клик по кнопке Создание заметки
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'Card_containerNew')]"))).click();
            //Заполнение Заголовка заметки
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'note-modal-title')]"))).sendKeys("Новая заметка номер " + (j+1));
            //Заполнение Содержания заметки
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'note-modal-content')]"))).sendKeys("Содержание к заметке номер " + (j+1));
            //Нажатие на палитру
            wait.until(ExpectedConditions.elementToBeClickable(By.id("palette-btn-new_empty"))).click();
            //Выбор цвета заметки
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'#fdcfe8')]"))).click();
            //Нажатие кнопки Ок
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-modal-save-btn-new_empty"))).click();
            //Рефреш страницы
            driver.navigate().refresh();
            //Получаем ид последнего контейнера заметок
            String lastId = driver.findElement(By.xpath("//*[contains(@id,'note-container')][last()]")).getAttribute("id");
            //Обрезаем ид заметки
            lastId = lastId.substring(15);
            //Поиск заголовка последней заметки
            String titleText = driver.findElement(By.id("note-title-" + lastId)).getText();
            //Вывод в консоль названия заголовка заметки
            System.out.printf("Итерация:%d Название:%s\n",j+1, titleText);
        }
    }
    @AfterEach
    public void closeDriver() {
        //Закрытие браузера
        driver.quit();
    }
}
