package PageModalFactoryPages;

import PageModalObjectPages.GeneratorPage;
import org.openqa.selenium.WebDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbPofPage extends GeneratePofPage {
    private WebDriver driver;

    public DbPofPage(WebDriver driver) {
        this.driver = driver;
    }

    // id Пользака
    private Integer id = 789;
    // id Заметки
    protected Integer noteId = 66667;
    // id Прав пользователя
    private Integer rightsId = 888;

    public DbPofPage() {
    }

    // Создаем пользователя в БД

    public void createSqlUser() {
        executeQuery("INSERT INTO nfaut.users (id,login,password) VALUES( " + id + " ,'" + userpof + "', '$2a$10$xO8gyOgHOG8Im4tVudUQFOK1moV.KmvWDV.Z0OUVni2xS4AApDDWS')");
    }

    // Даем права пользователю в БД

    public void getSqlRights() {
        executeQuery("INSERT INTO nfaut.users_roles (id,user_id,role_id) VALUES( " + rightsId + " ,'" + id + "', 2)");
    }

    // Создаем заметку в БД

    public void createSqlNote() {
        executeQuery("INSERT INTO nfaut.notes (id,user_id,name,color, content, priority) VALUES( " + noteId + " ,'" + id + "', 'SQL Заголовок', '#ccff90', 'SQL Содержание', 1)");
    }


    // Удаляем заметку
    public void deleteNote() {
        executeQuery(" DELETE FROM nfaut.notes WHERE id = ' " + noteId + "' ");
    }

    // Удаляем права пользователя
    public void deleteRights() {
        executeQuery(" DELETE FROM nfaut.users_roles WHERE id = ' " + rightsId + "' ");
    }

    // Удаляем пользователя
    public void deleteUser() {
        executeQuery(" DELETE FROM nfaut.users WHERE id = ' " + id + "' ");
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