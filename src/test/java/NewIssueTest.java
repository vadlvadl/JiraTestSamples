
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;
import steps.LoginSteps;


public class NewIssueTest {

    private WebDriver driver;
    private String issueKey = "";
    private String issueURL = "";

    @BeforeTest
    public void setup(){
        driver = new ChromeDriver();

        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.openLoginPage();
        loginSteps.signIn("Vadim_Lizogub","t@stPsSwd");
    }

    @Test (priority = 1)
    public void createNewIssueTest(){

        String textSummary = "[Test Automation] QAAUTO6-T1_testing_issue (eagles)";
        String textDescription = "Testing issue created according to http://jira.hillel.it:8080/browse/QAAUT6-131 task";

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigate();
        dashboardPage.checkAtDashboardPage();
        dashboardPage.clickCreateIssueButton();

        NewIssueDialog newIssueDialog = new NewIssueDialog(driver);
        newIssueDialog.waitDialogIsDisplayed()
                .enterSummary(textSummary)
                .enterDescription(textDescription)
                .clickAssignToMe()
                .submitDialog();

        NotificationDialog dialog = new NotificationDialog(driver);
        issueKey = dialog.getCreatedIssueKey();
        issueURL = dialog.getCreatedIssueLink();

        System.out.println("Issue " + issueKey + " created \nURL: " + issueURL + "\n");

        Assert.assertTrue(dialog.isSuccessDialogDisplayed());
    }

    @Test (priority = 2, dependsOnMethods = {"createNewIssueTest"})
    public void addCommentTest(){

        String textComment = "Sample comment 1";

        IssuePage issuePage = new IssuePage(driver);
        issuePage.navigateTo(issueURL);

    }

//    @Test
//    public void moveCreatedIssue(){
////        open("http://jira.hillel.it:8080/secure/RapidBoard.jspa?rapidView=302&projectKey=QAAUT6");
//
////        issueKey = "QAAUT6-165";
////        issueURL = "http://jira.hillel.it:8080/browse/QAAUT6-165";
//
//        Actions actions = new Actions(WebDriverRunner.getWebDriver());
//
//        actions.dragAndDrop($(byXpath("//div[@class='ghx-key']/a[contains(text()," + issueKey + ")]")),$(byXpath("//li[@data-column-id='860']"))).perform();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    @Test (priority = 10, dependsOnMethods = {"createNewIssueTest"})
    public void deleteCreatedIssue(){

        IssuePage issuePage = new IssuePage(driver);
        issuePage.navigateTo(issueURL);
        issuePage.atRequiredPage();
        issuePage.clickMenuMoreButton();
        issuePage.clickDeleteIssueButton();
        issuePage.confirmDeleteIssue();

        NotificationDialog dialog = new NotificationDialog(driver);

        Assert.assertTrue(dialog.isSuccessDialogDisplayed());
    }
}
