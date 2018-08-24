import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;

public class DashboardPage {

    private String dashboardPageURL = "http://jira.hillel.it:8080/secure/Dashboard.jspa";

    public void clickIssueButton(){ $(By.id("find_link")).click(); }

    public void clickSearchOfIssues(){ $("#issues_new_search_link_lnk").click(); }

    public boolean atRequiredPage(){
        return title().equalsIgnoreCase("System Dashboard - Hillel IT School JIRA");
    }

    public void clickCreateIssueButton() { $(By.id("create_link")).click();}

    public void navigate(){
        open(dashboardPageURL);
    }
}