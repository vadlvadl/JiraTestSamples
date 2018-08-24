package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginSteps {

    private final WebDriver driver;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage(){
        driver.get("http://jira.hillel.it:8080/login.jsp");
    }

    public void signIn(String username, String password){
        driver.findElement(By.name("os_username")).sendKeys(username);
        driver.findElement(By.name("os_password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }
}
