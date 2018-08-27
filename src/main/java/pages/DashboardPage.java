package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class DashboardPage {

    private String dashboardPageURL = "http://jira.hillel.it:8080/secure/Dashboard.jspa";
    private final WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate(){
        driver.get(dashboardPageURL);
    }

    public void checkAtDashboardPage(){
        Assert.assertEquals(driver.getCurrentUrl(),dashboardPageURL);
    }

    public void clickCreateIssueButton() {
        driver.findElement(By.id("create_link")).click();
    }

}