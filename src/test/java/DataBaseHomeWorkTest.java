import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

@DisplayName(value = "Домашнее задание №15")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DataBaseHomeWorkTest {
    WebDriver driver;

    // Login Генератор
    private Integer numberGenerator = 1 + (int) (Math.random() * 50000);
    private String userLogin = "autotest" + numberGenerator;
    // id Пользака
    private Integer id = 789;
    // id Заметки
    private Integer noteId = 66667;
    // id Прав пользователя
    private Integer rightsId = 888;

    @BeforeEach
    @Order(1)
    // Создаем пользователя в БД
    public void createSqlUser(){
        executeQuery("INSERT INTO nfaut.users (id,login,password) VALUES( " + id + " ,'" + userLogin +  "', '$2a$10$xO8gyOgHOG8Im4tVudUQFOK1moV.KmvWDV.Z0OUVni2xS4AApDDWS')");
    }

    @BeforeEach
    @Order(2)
    // Даем права пользователю в БД
    public void getSqlRights(){
        executeQuery("INSERT INTO nfaut.users_roles (id,user_id,role_id) VALUES( " + rightsId + " ,'" + id +  "', 2)");
    }

    @BeforeEach
    @Order(3)
    // Создаем заметку в БД
    public void createSqlNote(){
        executeQuery("INSERT INTO nfaut.notes (id,user_id,name,color, content, priority) VALUES( " + noteId + " ,'" + id +  "', 'SQL Заголовок', '#ccff90', 'SQL Содержание', 1)");
    }

    @DisplayName(value = "Сравнение Заголовка, Содержания, проверка наличия Заметки")
    @Test
    @Order(4)
    public void testLogin(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Ввести значение в поле Логин
        driver.findElement(By.id("login-input")).sendKeys(userLogin);
        //Ввести значение в поле Пароль
        driver.findElement(By.id("password-input")).sendKeys("Qwerty$4xdd");
        //Кликаем на нопку Войти
        driver.findElement(By.cssSelector(".form_auth_button.btn.btn-primary:first-child")).click();
        // Проверка наличия заметки
        assertEquals(true, driver.findElement(By.id("note-container-" + noteId)).isDisplayed());
        //Проверяем заголовок
        String titleText = driver.findElement(By.id("note-title-" + noteId)).getText();
        Assertions.assertEquals("SQL Заголовок", titleText, "Неверный заголовок заметки");
        //Проверяем текст
        String bodyText = driver.findElement(By.cssSelector("#note-content-" + noteId + " > div")).getText();
        Assertions.assertEquals("SQL Содержание", bodyText, "Неверный текст в заметке");

    }


    @AfterEach
    @Order(5)
    // Удаляем заметку
    public void deleteNote(){
        executeQuery(" DELETE FROM nfaut.notes WHERE id = ' " + noteId + "' ");
    }

    @AfterEach
    @Order(6)
    // Удаляем права пользователя
    public void deleteRights(){
        executeQuery(" DELETE FROM nfaut.users_roles WHERE id = ' " + rightsId + "' ");
    }
    @AfterEach
    @Order(7)
    // Удаляем пользователя
    public void deleteUser(){
        executeQuery(" DELETE FROM nfaut.users WHERE id = ' " + id + "' ");
    }

    @AfterEach
    @Order(8)
    public void closeDriver() {
        driver.quit();
    }

    public void executeQuery(String sql) {
        try {
            String url = "jdbc:postgresql://172.24.120.5:5432/postgres";
            String login = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, login, password);
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
