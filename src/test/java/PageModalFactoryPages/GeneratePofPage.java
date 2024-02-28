package PageModalFactoryPages;

import org.openqa.selenium.WebDriver;

public class GeneratePofPage {
    private WebDriver driver;

    // Login Генератор
    public Integer numberGenerator = 1 + (int) (Math.random() * 50000);
    public String userpof = "autotest" + numberGenerator;
    public GeneratePofPage(WebDriver driver) {
        this.driver = driver;
    }

    public GeneratePofPage() {
    }

    public String userLogin() {
        return userpof;
    }
}
