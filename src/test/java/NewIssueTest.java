import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class NewIssueTest {

    private String issueKey = "";
    private String issueURL = "";

    @BeforeTest
    public void setup(){
        Configuration.browser = "chrome";

        LoginPage loginPage = new LoginPage();
        loginPage.navigate();
        loginPage.atRequiredPage();
        loginPage.enterLogin("Vadim_Lizogub");
        loginPage.enterPassword("klAwD21420");
        loginPage.clickSubmitButton();
    }

    @Test (enabled = false, priority = 1)
    public void createNewIssueTest(){

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.navigate();
        dashboardPage.atRequiredPage();
        dashboardPage.clickCreateIssueButton();

        NewIssuePage newIssuePage = new NewIssuePage();
        newIssuePage.atRequiredPage();
        newIssuePage.enterSummary("[Test Automation] QAAUTO6-T1_testing_issue (eagles)");
        newIssuePage.enterDescription("Testing issue created according to http://jira.hillel.it:8080/browse/QAAUT6-131 task");
        newIssuePage.clickAssignToMe();
        newIssuePage.clickCreateButton();

        NotificationDialog dialog = new NotificationDialog();
        issueKey = dialog.getCreatedIssueKey();
        issueURL = dialog.getCreatedIssueLink();

        System.out.println("Issue " + issueKey + " created \nURL: " + issueURL + "\n");

        Assert.assertTrue(dialog.isSuccessDialogDisplayed());
    }

    @Test
    public void moveCreatedIssue(){
        open("http://jira.hillel.it:8080/secure/RapidBoard.jspa?rapidView=302&projectKey=QAAUT6");

        issueKey = "QAAUT6-165";
        issueURL = "http://jira.hillel.it:8080/browse/QAAUT6-165";

        Actions actions = new Actions(WebDriverRunner.getWebDriver());

        actions.dragAndDrop($(byXpath("//div[@class='ghx-key']/a[contains(text()," + issueKey + ")]")),$(byXpath("//li[@data-column-id='860']"))).perform();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test (enabled = false, priority = 10, dependsOnMethods = {"createNewIssueTest"})
    public void deleteCreatedIssue(){

        IssuePage issuePage = new IssuePage();
        issuePage.navigateTo(issueURL);
        issuePage.atRequiredPage();
        issuePage.clickMenuMoreButton();
        issuePage.clickDeleteIssueButton();
        issuePage.confirmDeleteIssue();

        NotificationDialog dialog = new NotificationDialog();
        Assert.assertTrue(dialog.isSuccessDialogDisplayed());
    }
}
