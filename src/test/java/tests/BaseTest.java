package tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;
    private static final Logger logger = Logger.getLogger(BaseTest.class.getName());

    @BeforeEach
    public void setUp() {
        logger.info("Setting up the test environment...");
        WebDriverManager.chromedriver().setup();
        // Setup ChromeDriver with options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Maximize the browser window

        // Initialize WebDriver (ChromeDriver only)
        driver = new ChromeDriver(options);

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the login page
        String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        driver.get(baseUrl);

        logger.info("Navigated to the login page: " + baseUrl);
    }

    @AfterEach
    public void tearDown() {
        logger.info("Tearing down the test environment...");
        if (driver != null) {
            driver.quit(); // Close the browser after each test
            logger.info("Browser closed.");
        }
    }
}