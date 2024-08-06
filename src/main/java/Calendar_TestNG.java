//--
//--
//import javafx.scene.web.WebEngine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//--
//--
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//--
//--
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
//--
import org.testng.annotations.BeforeMethod;
//--
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Calendar_TestNG{
    private WebDriver driver;

    @BeforeMethod
    public void BeforeMethod() {
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ancabota09.wixsite.com/intern");
    }

    @AfterMethod
    public void AfterMethod() {
        driver.quit();
    }

    private void clickWithRetry(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int attempts = 0;
        boolean success = false;
        while (attempts < 5) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                element.click();
                success = true;
                break;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                attempts++;
                // Wait a bit before retrying
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (!success) {
            throw new org.openqa.selenium.WebDriverException("Failed to click element after multiple attempts: " + xpath);
        }
    }

    @Test
    public void CheckInCalendarTest() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Thread.sleep(5000);

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        WebElement calendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        calendarButton.click();
        try {
            driver.switchTo().defaultContent();
            WebElement iframe2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
            driver.switchTo().frame(iframe2);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);

            LocalDate today = LocalDate.now();
            LocalDate futureDate = today.plusDays(10);

            String formattedDate = today.format(formatter);
            String xpath = String.format("//button[@aria-label='%s']", formattedDate);
            WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            softAssert.assertTrue(dateButtonCheckIn.getAttribute("class").contains("focused"), "The current day is not highlighted");

            LocalDate[] datesToCheck = {today, futureDate};

            for(LocalDate date : datesToCheck) {

                String formattedDate1 = date.format(formatter);
                String xpath1 = String.format("//button[@aria-label='%s']", formattedDate1);
                WebElement dateButtonCheckIn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath1)));

                dateButtonCheckIn1.click();

                driver.switchTo().defaultContent();
                driver.switchTo().frame(iframe);

                WebElement checkInDateSelected = driver.findElement(By.id("check-in-value"));
                String expectedDate = date.format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH));
                Assert.assertEquals(checkInDateSelected.getText(), expectedDate, "Date is not selected.");
                System.out.println(checkInDateSelected.getText());

                Thread.sleep(5000);

                driver.switchTo().defaultContent();
                driver.switchTo().frame(iframe2);
                WebElement closeButton = driver.findElement(By.xpath("/html/body/div/main/div/button"));
                closeButton.click();

                driver.switchTo().defaultContent();
                driver.switchTo().frame(iframe);
                calendarButton.click();
                //clickWithRetry("//*[@id=\"check-in\"]");

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            softAssert.assertAll();
        }
    }
}
