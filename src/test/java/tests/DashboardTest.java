package tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import java.time.Duration;

public class DashboardTest extends BaseTest
{
    private final String validUsername = "Admin";
    private final String validPassword = "admin123";
    private final String empFirstName = "Assaf";
    private final String empLastName = "Y";
    private final String empId = "12345";


    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PIMPage pimPage;

    @Test
    public void test_01_search_employee() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        PIMPage pimPage = new PIMPage(driver);

        // Login
        loginPage.fillInfo(validUsername, validPassword);
        dashboardPage.goToPIM();

        // Perform search action
        pimPage.searchEmployee(empFirstName, empLastName, empId);

        // Wait for the records text element to be visible
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.findElement(pimPage.RECORDS_FOUND_TEXT).isDisplayed());

        String recordsText = driver.findElement(pimPage.RECORDS_FOUND_TEXT).getText();

        if (recordsText.contains("No Records Found")) {
            System.out.println("No records found for " + empFirstName + " " + empLastName + " with employee ID " + empId);
            Assertions.assertTrue(recordsText.contains("No Records Found"));
        } else {
            System.out.println("Employee " + empFirstName + " " + empLastName + " with employee ID " + empId + " found.");
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

        loginPage.fillInfo(validUsername, validPassword);
        dashboardPage.goToPIM();

        pimPage.addEmployee(empFirstName, empLastName, empId);
        String currentUrl = driver.getCurrentUrl();

        // Wait for success case (URL change)
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> !driver.getCurrentUrl().equals(currentUrl));

        Assertions.assertNotEquals(currentUrl, driver.getCurrentUrl(), "Employee not added successfully.");
    }
}
