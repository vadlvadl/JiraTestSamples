package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NotificationDialog {

    private final WebDriver driver;

    public NotificationDialog(WebDriver driver) {
        this.driver = driver;
    }

    public String getCreatedIssueKey(){
        WebElement dialog = (new WebDriverWait(driver, 10))
                                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='aui-flag-container']//a[@data-issue-key]")));

        return dialog.getAttribute("data-issue-key");
    }

    public String getCreatedIssueLink(){
        WebElement dialog = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='aui-flag-container']//a[@data-issue-key]")));

        return dialog.getAttribute("href");
    }

    public boolean isSuccessDialogDisplayed(){
        return driver.findElement(By.xpath("//div[@id='aui-flag-container']//div[contains(@class,'success')]")).isDisplayed();
    }
}