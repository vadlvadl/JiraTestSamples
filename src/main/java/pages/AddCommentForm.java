package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCommentForm {

    private String formXpath = "//*[@id='addcomment']//form";
    private final WebDriver driver;
    private WebElement form;

    AddCommentForm(WebDriver driver) {
        this.driver = driver;
        this.form = (new WebDriverWait(driver,10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(formXpath)));
    }

    public AddCommentForm selectTextMode(){
        form.findElement(By.xpath("//li[@data-mode='source']")).click();
        return this;
    }

    public AddCommentForm enterCommentText(String text){
        form.findElement(By.xpath("//textarea[@name='comment']")).sendKeys(text);
        return this;
    }

    public void submitForm(){
        form.findElement(By.id("issue-comment-add-submit")).click();
        (new WebDriverWait(driver, 15))
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(formXpath),0));
    }
}
