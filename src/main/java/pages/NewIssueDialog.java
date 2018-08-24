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
    }

    public NewIssueDialog waitDialogIsDisplayed() {
        dialog = (new WebDriverWait(driver, 15))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("create-issue-dialog")));

        return this;
    }

    public  NewIssueDialog enterSummary(String summary){
        dialog.findElement(By.id("summary")).sendKeys(summary);
        return this;
    }
    public  NewIssueDialog enterDescription(String description){
        dialog.findElement(By.xpath("//li[@data-mode='source']/a")).click(); // Switch to text mode
        dialog.findElement(By.id("description")).sendKeys(description);
        return this;
    }

    public NewIssueDialog clickAssignToMe(){
        dialog.findElement(By.id("assign-to-me-trigger")).click();
        return this;
    }

    public void submitDialog(){
        dialog.findElement(By.id("create-issue-submit")).click();
    }
}