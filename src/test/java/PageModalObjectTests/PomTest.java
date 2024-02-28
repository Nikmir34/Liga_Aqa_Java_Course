package PageModalObjectTests;



import org.junit.jupiter.api.*;

import static org.junit.Assert.assertEquals;

@DisplayName(value = "Домашнее задание №16")
public class PomTest extends BaseTest {

    @BeforeEach
    public void bdCreate() {
        dbPage.createSqlUser();
        dbPage.getSqlRights();
        dbPage.createSqlNote();
    }

    @DisplayName(value = "Проверка наличия Заметки POM")
    @Test
    public void checkNote() {
        authorizationPage.goToAuthorizationPage();
        authorizationPage.fillInLogin(dbPage.user);
        authorizationPage.fillInPassword("Qwerty$4xdd");
        authorizationPage.clickLoginButton();
        assertEquals(true, mainPage.notesIsCreated());

    }

    @AfterEach
    public void bdDelete() {
        dbPage.deleteNote();
        dbPage.deleteRights();
        dbPage.deleteUser();
    }
}
