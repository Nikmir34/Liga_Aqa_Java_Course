package PageModalObjectTests;

import PageModalObjectPages.BasePage;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

@DisplayName(value = "Домашнее задание №16")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PomTest extends BasePage {
    // Login Генератор
    private Integer numberGenerator = 1 + (int) (Math.random() * 50000);
    private String userLogin = "autotest" + numberGenerator;
    // id Пользака
    private Integer id = 789;
    // id Заметки
    protected Integer noteId = 66667;
    // id Прав пользователя
    private Integer rightsId = 888;

    @BeforeEach
    @Order(1)
    // Создаем пользователя в БД
    public void createSqlUser() {
        executeQuery("INSERT INTO nfaut.users (id,login,password) VALUES( " + id + " ,'" + userLogin + "', '$2a$10$xO8gyOgHOG8Im4tVudUQFOK1moV.KmvWDV.Z0OUVni2xS4AApDDWS')");
    }

    @BeforeEach
    @Order(2)
    // Даем права пользователю в БД
    public void getSqlRights() {
        executeQuery("INSERT INTO nfaut.users_roles (id,user_id,role_id) VALUES( " + rightsId + " ,'" + id + "', 2)");
    }

    @BeforeEach
    @Order(3)
    // Создаем заметку в БД
    public void createSqlNote() {
        executeQuery("INSERT INTO nfaut.notes (id,user_id,name,color, content, priority) VALUES( " + noteId + " ,'" + id + "', 'SQL Заголовок', '#ccff90', 'SQL Содержание', 1)");
    }

    @DisplayName(value = "Проверка наличия Заметки POM")
    @Test
    @Order(4)
    public void checkNote() {
        authorizationPage.goToAuthorizationPage();
        authorizationPage.fillInLogin(userLogin);
        authorizationPage.fillInPassword("Qwerty$4xdd");
        authorizationPage.clickLoginButton();
        assertEquals(true, mainPage.notesIsCreated());

    }

    @AfterEach
    @Order(5)
    // Удаляем заметку
    public void deleteNote() {
        executeQuery(" DELETE FROM nfaut.notes WHERE id = ' " + noteId + "' ");
    }

    @AfterEach
    @Order(6)
    // Удаляем права пользователя
    public void deleteRights() {
        executeQuery(" DELETE FROM nfaut.users_roles WHERE id = ' " + rightsId + "' ");
    }

    @AfterEach
    @Order(7)
    // Удаляем пользователя
    public void deleteUser() {
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
