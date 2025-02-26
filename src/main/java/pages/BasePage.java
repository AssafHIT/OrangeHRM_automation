package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to click an element
    public void click(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Method to fill text in an input field
    public void fillText(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        if (element != null) {
            element.clear();
            element.sendKeys(text);
        } else {
            throw new IllegalArgumentException("Element not found: " + locator);
        }
    }


    // Method to get text from an element
    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    // Method to clear an input field
    public void clear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        element.clear();
    }

    // Method to perform double click and delete an element's content
    public void doubleClickAndDelete(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        // Perform double-click
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();

        // Perform delete action
        actions.sendKeys(Keys.DELETE).perform();

        // Wait for changes to take effect
        try {
            Thread.sleep(3000); // Wait time for demo purposes, consider using WebDriverWait for better synchronization
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to wait for an element to be present
    public boolean waitForElement(By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
