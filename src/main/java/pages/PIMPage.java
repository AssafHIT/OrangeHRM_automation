package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PIMPage extends BasePage {

    // Locators for the elements
    private final By EMPLOYEE_NAME_INPUT = By.cssSelector("input[placeholder='Type for hints...']:first-of-type");
    private final By EMPLOYEE_ID_INPUT = By.cssSelector("div.oxd-grid-item.oxd-grid-item--gutters div.oxd-input-group__label-wrapper + div input.oxd-input.oxd-input--active");
    private final By SEARCH_BTN = By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space");
    public final By RECORDS_FOUND_TEXT = By.cssSelector("div.orangehrm-horizontal-padding.orangehrm-vertical-padding span.oxd-text.oxd-text--span");
    private final By ADD_EMPLOYEE_BTN = By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--secondary i.oxd-icon.bi-plus");

    private final By INVALID_ID_ERROR_MESSAGE = By.cssSelector(".oxd-input-field-error-message");
    private final By REQUIRED_ERROR_MESSAGE = By.cssSelector(".oxd-text.oxd-text--span oxd-input-field-error-message.oxd-input-group__message");
    private final By EMPLOYEE_DETAILS_ID = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > div > div.orangehrm-edit-employee-content > div.orangehrm-horizontal-padding.orangehrm-vertical-padding > form > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div > div:nth-child(2) > input");

    private final By ADD_EMPLOYEE_FIRSTNAME_INPUT = By.cssSelector("input[name='firstName']");
    private final By ADD_EMPLOYEE_LASTNAME_INPUT = By.cssSelector("input[name='lastName']");
    private final By ADD_EMPLOYEE_ID_INPUT = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div.orangehrm-employee-container > div.orangehrm-employee-form > div:nth-child(1) > div.oxd-grid-2.orangehrm-full-width-grid > div > div > div:nth-child(2) > input");
    private final By SAVE_EMPLOYEE_BTN = By.cssSelector("button[type='submit'][class*='oxd-button']");
    private final By PIM_BTN = By.cssSelector("nav .oxd-sidepanel-body ul li:nth-child(2) a span");

    // Constructor
    public PIMPage(WebDriver driver) {
        super(driver);
    }

    // Method to search employee
    public void searchEmployee(String firstname, String lastname, String employeeId) {
        fillText(EMPLOYEE_NAME_INPUT, firstname + " " + lastname);
        fillText(EMPLOYEE_ID_INPUT, employeeId);
        click(SEARCH_BTN);
    }

    // Method to add an employee
    public void addEmployee(String firstname, String lastname, String employeeId) {
        click(ADD_EMPLOYEE_BTN);
        try {
            Thread.sleep(2000);  // Sleep is used for the example, replace with appropriate wait in real tests
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fillText(ADD_EMPLOYEE_FIRSTNAME_INPUT, firstname);
        fillText(ADD_EMPLOYEE_LASTNAME_INPUT, lastname);
        clear(ADD_EMPLOYEE_ID_INPUT);
        doubleClickAndDelete(ADD_EMPLOYEE_ID_INPUT);
        fillText(ADD_EMPLOYEE_ID_INPUT, employeeId);
        click(SAVE_EMPLOYEE_BTN);
        try {
            Thread.sleep(2000);  // Sleep for demonstration, replace with proper wait strategy
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to get employee ID details
    public String getEmployeeDetailsId() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(EMPLOYEE_DETAILS_ID));
        String detailsPageEmployeeId = getText(EMPLOYEE_DETAILS_ID);
        System.out.println("Employee ID from details page: " + detailsPageEmployeeId);
        return detailsPageEmployeeId;
    }

    // Method to get error message for invalid ID
    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.elementToBeClickable(INVALID_ID_ERROR_MESSAGE)).getText();
    }
}
