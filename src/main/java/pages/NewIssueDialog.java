package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NewIssueDialog {

    private final WebDriver driver;
    private WebElement dialog;

    public NewIssueDialog(WebDriver driver) {
        this.driver = driver;

        (new WebDriverWait(driver, 15))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("create-issue-dialog")));
    }

    public  NewIssueDialog enterSummary(String summary){
        (new WebDriverWait(driver,10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("summary"))).sendKeys(summary);
        return this;
    }
    public  NewIssueDialog enterDescription(String description){
        (new WebDriverWait(driver,10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-mode='source']/a"))).click(); // Switch to text mode
        (new WebDriverWait(driver,10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("description"))).sendKeys(description);
        return this;
    }

    public NewIssueDialog clickAssignToMe(){
        (new WebDriverWait(driver,10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("assign-to-me-trigger"))).click();
        return this;
    }

    public void submitDialog(){
        dialog.findElement(By.id("create-issue-submit")).click();
    }
}