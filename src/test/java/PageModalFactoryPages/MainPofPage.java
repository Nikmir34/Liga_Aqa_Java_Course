package PageModalFactoryPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPofPage extends DbPofPage {
    private WebDriver driver;
    @FindBy(id = "logout-btn")
    private WebElement logoutButton;
    @FindBy(id = "note-container-66667")
    private WebElement note;


    public MainPofPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean notesIsCreated() {
        if (note.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
}
