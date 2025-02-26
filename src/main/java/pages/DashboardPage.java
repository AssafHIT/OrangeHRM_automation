package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage extends BasePage {

    // Locators for the elements
    private final By LOGOUT_BTN = By.cssSelector("li.--active .oxd-dropdown-menu .oxd-userdropdown-link[href='/web/index.php/auth/logout']");
    private final By DROPDOWN = By.cssSelector("i.oxd-icon.bi-caret-down-fill.oxd-userdropdown-icon");
    private final By PIM_BTN = By.cssSelector("nav .oxd-sidepanel-body ul li:nth-child(2) a span");
    // Constructor
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // Logout method
    public void logout() {
        try {
            // Wait for the user dropdown to be clickable and click it
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(DROPDOWN)).click();
            // Wait for the logout button to be clickable and click it
            wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_BTN)).click();
        } catch (TimeoutException e) {
            System.out.println("Timeout occurred while trying to log out.");
            throw e;  // Rethrow the exception after logging the message
        }
    }


    // Navigate to PIM page
    public void goToPIM() {
        try {
            // Wait for the PIM button to be clickable and click it
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(PIM_BTN)).click();
        } catch (TimeoutException e) {
            System.out.println("Timeout occurred while trying to navigate to PIM.");
            throw e;
        } catch (Exception e) {
            System.out.println("An error occurred while trying to navigate to PIM: " + e.getMessage());
            throw e;
        }
    }
}
