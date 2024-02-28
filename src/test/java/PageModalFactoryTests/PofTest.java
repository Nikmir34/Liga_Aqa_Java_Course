package PageModalFactoryTests;

import PageModalFactoryPages.BasePofPage;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

@DisplayName(value = "Домашнее задание №16")
public class PofTest extends BasePofTest {
    @BeforeEach
    public void bdCreate() {
        dbPofPage.createSqlUser();
        dbPofPage.getSqlRights();
        dbPofPage.createSqlNote();
    }

    @DisplayName(value = "Проверка наличия Заметки POM")
    @Test
    public void checkNote() {
        authorizationPofPage.goToAuthorizationPage();
        authorizationPofPage.fillInLogin(dbPofPage.userpof);
        authorizationPofPage.fillInPassword("Qwerty$4xdd");
        authorizationPofPage.clickLoginButton();
        assertEquals(true, mainPofPage.notesIsCreated());

    }

    @AfterEach
    public void bdDelete() {
        dbPofPage.deleteNote();
        dbPofPage.deleteRights();
        dbPofPage.deleteUser();
    }
}
