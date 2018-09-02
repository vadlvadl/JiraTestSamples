import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import steps.LoginSteps;


public class NewIssueTest {

    private WebDriver driver;
    private String issueKey = "";
    private String issueURL = "";


    @DataProvider
    public static Object[][] issueDetailsProvider(){
        return new Object[][]{
                {"[Test Automation] QAAUTO6-T1_testing_issue (eagles)",
                 "Testing issue created according to http://jira.hillel.it:8080/browse/QAAUT6-131 task"},
        };
    }

    @BeforeTest
    @Parameters({"browser","username","password"})
    public void setup(String browser, String cUsername, String cPassword){

        switch(browser){
            case "chrome":  System.setProperty("webdriver.chrome.driver","chromedriver_win_x86_2.41.exe");
                driver = new ChromeDriver();
                break;
            default:
                Assert.fail("Cannot find specified driver for browser " + browser);
        }

        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.openLoginPage();
        loginSteps.signIn(cUsername,cPassword);
    }

    @Test (dataProvider = "issueDetailsProvider", priority = 1)
    public void createNewIssueTest(String textSummary, String textDescription){

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

        Assert.assertTrue(dialog.isSuccessDialogDisplayed());
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
    public void closeWebDriver(){

        System.out.println("Issue " + issueKey + " created \nURL: " + issueURL + "\n");
        driver.quit();
    }
}
