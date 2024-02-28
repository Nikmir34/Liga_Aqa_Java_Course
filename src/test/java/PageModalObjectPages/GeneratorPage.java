package PageModalObjectPages;

import org.openqa.selenium.WebDriver;

public class GeneratorPage {

    private WebDriver driver;

    // Login Генератор
    public Integer numberGenerator = 1 + (int) (Math.random() * 50000);
    public String user = "autotest" + numberGenerator;
    public GeneratorPage(WebDriver driver) {
        this.driver = driver;
    }

    public GeneratorPage() {
    }

    public String userLogin() {
        return user;
    }
}
