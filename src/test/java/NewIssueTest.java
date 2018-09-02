
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import steps.LoginSteps;


public class NewIssueTest {

    private WebDriver driver;
    private String issueKey = "";
    private String issueURL = "";


    @DataProvider
    public static Object[][] credentials(){
        return new Object[][]{{"Vadim_Lizogub","t@stPsSwd"},{"webinar5","webinar5"}};
    }

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver","chromedriver_win_x86_2.41.exe");

        driver = new ChromeDriver();

//        LoginSteps loginSteps = new LoginSteps(driver);
//        loginSteps.openLoginPage();
//        loginSteps.signIn("Vadim_Lizogub","t@stPsSwd");
    }

    @Test (dataProvider = "credentials", priority = 1)
    public void createNewIssueTest(String username, String password){

        String textSummary = "[Test Automation] QAAUTO6-T1_testing_issue (eagles)";
        String textDescription = "Testing issue created according to http://jira.hillel.it:8080/browse/QAAUT6-131 task";

        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.openLoginPage();
        loginSteps.signIn(username,password);

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigate();
        dashboardPage.checkAtDashboardPage();
        dashboardPage.clickCreateIssueButton();

        NewIssueDialog newIssueDialog = new NewIssueDialog(driver);
        newIssueDialog.enterSummary(textSummary)
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
        issuePage.clickAddCommentButton()
                .selectTextMode()
                .enterCommentText(textComment)
                .submitForm();

        Assert.assertEquals(issuePage.getLastComment(), textComment);
    }

    @Test (priority = 10, dependsOnMethods = {"createNewIssueTest"})
    public void deleteCreatedIssue(){

        IssuePage issuePage = new IssuePage(driver);
        issuePage.navigateTo(issueURL);
        issuePage.atRequiredPage();
        issuePage.clickMenuMoreButton()
                .clickDeleteIssueButton()
                .confirmDeleteIssue();

        NotificationDialog dialog = new NotificationDialog(driver);

        Assert.assertTrue(dialog.isSuccessDialogDisplayed());
    }

    @AfterTest
    public void teardown(){
        driver.quit();
    }
}
