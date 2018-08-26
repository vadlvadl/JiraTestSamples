package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class IssuePage {

    private final WebDriver driver;

    public IssuePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo(String url){
        driver.get(url);
    }

    public boolean atRequiredPage(){
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("issue-content"))).isDisplayed();
    }

    public AddCommentForm clickAddCommentButton(){

        driver.findElement(By.id("footer-comment-button")).click();
        return new AddCommentForm(driver);
    }

    public void clickMenuMoreButton(){
        driver.findElement(By.id("opsbar-operations_more")).click();
    }

    public void clickDeleteIssueButton(){
        driver.findElement(By.id("delete-issue")).click();
    }

    public void confirmDeleteIssue(){
        driver.findElement(By.id("delete-issue-submit")).click();
    }

    public String getLastComment(){
        List comments = driver.findElements(By.xpath("//*[@id='issue_actions_container']//folowing::[@class='action-body flooded']"));
        return comments.get(comments.size() - 1).toString();
    }

}