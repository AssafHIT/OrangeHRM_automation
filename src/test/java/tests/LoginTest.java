package tests;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest{

    @Test
    public void test_01_LoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillInfo("Admin", "admin123");
        // After login, assert that the URL is the dashboard page
        assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", driver.getCurrentUrl());
    }

    @Test
    public void test_02_LoginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillInfo("Admin", "wrongpassword");
        // Assert that the error message is shown
        assertTrue(loginPage.getErrorMessage().contains("Invalid credentials"));
    }

    @Test
    public void test_03ForgotPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.forgotPassword("Admin");
        // Assert that the reset password confirmation message is shown
        assertTrue(loginPage.getResetConfirmationMessage().contains("Reset Password link sent successfully"));
    }
}

