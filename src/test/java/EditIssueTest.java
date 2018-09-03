import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.IssuePage;
import steps.LoginSteps;

public class EditIssueTest {

    private WebDriver driver;

    @BeforeTest
    @Parameters({"browser","username","password"})
    public void setup(String browser, String username, String password){

        switch(browser){
            case "chrome":  driver = new ChromeDriver();
                            break;
            default:
                Assert.fail("Cannot find specified driver for browser " + browser);
        }

        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.openLoginPage();
        loginSteps.signIn(username,password);
    }

    @Test
    @Parameters({"editIssueURL", "editIssueCommentText"})
    public void addCommentTest(String issueURL, String commentText){
        String textComment = "Sample comment 1";

        IssuePage issuePage = new IssuePage(driver);
        issuePage.navigateTo(issueURL);
        issuePage.clickAddCommentButton()
                .selectTextMode()
                .enterCommentText(textComment)
                .submitForm();

        Assert.assertEquals(issuePage.getLastComment(), textComment);
    }

    @AfterTest
    public void closeWebDriver(){
        driver.quit();
    }
}
