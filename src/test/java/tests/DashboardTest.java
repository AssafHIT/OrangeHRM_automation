package tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import java.time.Duration;

public class DashboardTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PIMPage pimPage;

    @Test
    public void test_01_search_employee() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        PIMPage pimPage = new PIMPage(driver);

        // Login
        loginPage.fillInfo("Admin", "admin123");
        dashboardPage.goToPIM();

        // Perform search action
        pimPage.searchEmployee("A", "B", "12345");

        // Wait for the records text element to be visible
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.findElement(pimPage.RECORDS_FOUND_TEXT).isDisplayed());

        String recordsText = driver.findElement(pimPage.RECORDS_FOUND_TEXT).getText();

        if (recordsText.contains("No Records Found")) {
            System.out.println("No records found for A B with employee ID 12345.");
            Assertions.assertTrue(recordsText.contains("No Records Found"));
        } else {
            System.out.println("Employee A B with employee ID 12345 found.");
            Assertions.assertTrue(recordsText.contains("(") && recordsText.contains(")"));
            int numberOfRecords = Integer.parseInt(recordsText.split(" ")[0].replace("(", "").replace(")", ""));
            Assertions.assertTrue(numberOfRecords > 0);
        }
    }

    @Test
    public void test_02_add_employee() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        PIMPage pimPage = new PIMPage(driver);

        loginPage.fillInfo("Admin", "admin123");
        dashboardPage.goToPIM();

        pimPage.addEmployee("Assaf", "B", "91546");
        String currentUrl = driver.getCurrentUrl();

        // Wait for success case (URL change)
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> !driver.getCurrentUrl().equals(currentUrl));

        Assertions.assertNotEquals(currentUrl, driver.getCurrentUrl(), "Employee not added successfully.");
    }
}
