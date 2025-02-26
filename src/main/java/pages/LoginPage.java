package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage {

    // Locators for the page elements
    private final By USER_NAME = By.cssSelector("input[name='username']");
    private final By PASSWORD = By.cssSelector("input[name='password']");
    private final By LOGIN_BTN = By.cssSelector("button[type='submit']");
    private final By ERROR_MSG = By.cssSelector("p.oxd-text.oxd-text--p.oxd-alert-content-text");
    private final By FORGOT_PASSWORD = By.cssSelector("div.orangehrm-login-forgot > p");
    private final By RESET_PASSWORD_USER_NAME = By.cssSelector(".oxd-input.oxd-input--active");
    private final By RESET_PASSWORD_ERROR_MSG = By.cssSelector("span.oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message");
    private final By RESET_PASSWORD_CONFIRMATION_MSG = By.cssSelector(".orangehrm-forgot-password-container .orangehrm-forgot-password-wrapper h6");

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Method to fill in username and password
    public void fillInfo(String username, String password) {
        fillText(USER_NAME, username);  // Using BasePage's method to fill text
        waitForElementToBeClickable(PASSWORD).sendKeys(password);
        click(LOGIN_BTN);  // Using BasePage's click method
    }

    // Method to get the error message displayed
    public String getErrorMessage() {
        return waitForElementToBeClickable(ERROR_MSG).getText();
    }

    // Method for clicking "Forgot Password" and entering the username for reset
    public void forgotPassword(String username) {
        click(FORGOT_PASSWORD);
        WebElement resetUsernameField = waitForElementToBeClickable(RESET_PASSWORD_USER_NAME);
        resetUsernameField.sendKeys(username + Keys.RETURN);
    }

    // Method to get reset password error message
    public String getResetPasswordErrorMessage() {
        return waitForElementToBeClickable(RESET_PASSWORD_ERROR_MSG).getText();
    }

    // Method to get confirmation message after password reset
    public String getResetConfirmationMessage() {
        return waitForElementToBeClickable(RESET_PASSWORD_CONFIRMATION_MSG).getText();
    }

    // Helper method to wait for an element to be clickable
    private WebElement waitForElementToBeClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
