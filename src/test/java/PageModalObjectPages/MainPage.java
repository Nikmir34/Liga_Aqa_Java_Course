package PageModalObjectPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends DbPage {
    private By logoutButton = By.id("logout-btn");
    private By note = By.id("note-container-" + noteId);
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean notesIsCreated() {
        if (driver.findElement(note).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
}
