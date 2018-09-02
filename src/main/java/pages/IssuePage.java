package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class IssuePage {

    private final WebDriver driver;
    private WebDriverWait wait;

    public IssuePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void navigateTo(String url){
        driver.get(url);
    }

    public boolean atRequiredPage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("issue-content"))).isDisplayed();
    }

    public AddCommentForm clickAddCommentButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("footer-comment-button"))).click();

        return new AddCommentForm(driver);
    }

    public IssuePage clickMenuMoreButton(){
        driver.findElement(By.id("opsbar-operations_more")).click();
        return this;
    }

    public IssuePage clickDeleteIssueButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-issue"))).click();
        return this;
    }

    public void confirmDeleteIssue(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-issue-submit"))).click();
    }

    public WebElement getMenuDeleteButton(){
        return driver.findElement(By.id("delete-issue"));
    }

    public String getLastComment(){
        WebElement lastComment = driver.findElement(By.xpath("//*[@id='issue_actions_container']//following::div[@class='action-body flooded'][last()]"));

        return lastComment.getText();
    }

}