package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCommentForm {

    private String formXpath = "//*[@id='addComment']//form";
    private final WebDriver driver;

    AddCommentForm(WebDriver driver) {
        this.driver = driver;
    }

    public AddCommentForm waitUntilLoaded(){
        WebElement form = (new WebDriverWait(driver,10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(formXpath)));
        return this;
    }

    public AddCommentForm selectTextMode(){
        driver.findElement(By.xpath(formXpath + "//li[@data-mode='source']")).click();
        return this;
    }

    public AddCommentForm enterCommentText(String text){
        driver.findElement(By.xpath(formXpath + "//textarea[@name='comment']")).sendKeys(text);
        return this;
    }

    public void submitForm(){
        driver.findElement(By.xpath(formXpath + "//input[@name='add']")).click();
    }
}
