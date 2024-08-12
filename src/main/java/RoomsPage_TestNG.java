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
import java.time.format.TextStyle;
import java.util.Locale;

public class RoomsPage_TestNG {
    private WebDriver driver;
    LocalDate today = LocalDate.now();

    @BeforeMethod
    public void BeforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ancabota09.wixsite.com/intern");
    }

    @AfterMethod
    public void AfterMethod() {
        driver.quit();
    }

    private void SwitchToRoomsPage(){
        WebElement roomsLink = driver.findElement(By.id("i6kl732v2label"));
        roomsLink.click();
    }

    @Test
    private void RoomsPageDescription(){
        SwitchToRoomsPage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement pageInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6lwrp17\"]/p/span")));
        String pageInfoText = pageInfo.getText();
        Assert.assertTrue(pageInfoText.contains("Rooms"), "Page description is not correct.");
    }

    @Test
    private void TodayDateSelectionCheckInCalendar(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(mainFrame);

        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        //performing the selection of today
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);

        String formattedDate1 = today.format(formatter);
        String datesButton = String.format("//button[@aria-label='%s']", formattedDate1);

        WebElement dateButtonCheckIn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(datesButton)));
        dateButtonCheckIn1.click();

        //check if selected check in date is the same in the check in label after selection
        driver.switchTo().defaultContent();
        driver.switchTo().frame(mainFrame);
        WebElement checkInRoomsDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check_in-value\"]")));
        String cIRD = checkInRoomsDate.getText();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
        Assert.assertEquals(cIRD, today.format(timeFormatter), "Check In date did not match.");
    }

    @Test
    private void FutureDateSelectionCheckInCalendar(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(mainFrame);

        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        //performing the selection of a future date
        LocalDate futureDate = today.plusDays(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);

        String formattedDate1 = futureDate.format(formatter);
        String datesButton = String.format("//button[@aria-label='%s']", formattedDate1);

        WebElement dateButtonCheckIn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(datesButton)));
        dateButtonCheckIn1.click();

        //check if selected check in date is the same in the check in label after selection
        driver.switchTo().defaultContent();
        driver.switchTo().frame(mainFrame);
        WebElement checkInRoomsDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check_in-value\"]")));
        String cIRD = checkInRoomsDate.getText();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
        Assert.assertEquals(cIRD, futureDate.format(timeFormatter), "Check In date did not match.");
    }

    @Test
    private void PrevoiusMonthCheckInCalendar(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(mainFrame);

        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        //obtaining and comparing the months names
        WebElement previousMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[1]/div[2]/div/nav/button[1]")));
        previousMonthButton.click();
        WebElement actualMonthLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[1]/div[2]/div/div[1]")));
        String actualMonthName = actualMonthLabel.getText();
        String previousMonthName = today.minusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Assert.assertTrue(actualMonthName.contains(previousMonthName), "Previous Month is not displayed correctly.");
    }

    @Test
    private void NextMonthCheckInCalendar(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(mainFrame);

        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        //obtaining and comparing the months names
        WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[1]/div[2]/div/nav/button[2]")));
        nextMonthButton.click();
        WebElement actualMonthLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[1]/div[2]/div/div[1]")));
        String actualMonthName = actualMonthLabel.getText();
        String nextMonthName = today.plusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Assert.assertTrue(actualMonthName.contains(nextMonthName), "Next Month date is not displayed correctly.");
    }

    @Test
    private void CheckOutNextDay() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(mainFrame);

        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        //performing the selection of today
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);

        String formattedDate1 = today.format(formatter);
        String datesButton = String.format("//button[@aria-label='%s']", formattedDate1);

        WebElement dateButtonCheckIn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(datesButton)));
        dateButtonCheckIn1.click();

        //check if the checkout calendar is opened automatically
        WebElement checkOutCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]")));
        boolean checkOutCalendarDisplayed= checkOutCalendarFrame.isDisplayed();
        softAssert.assertTrue(checkOutCalendarDisplayed, "Check Out Calendar is not displayed after selecting a check-in date.");

        //performing the selection of tomorrow as a checkout date
        // Găsirea calendarului activ (fără atributul hidden)
        WebElement activeCalendar = driver.findElement(By.xpath("//div[contains(@class, 'calendar-popup') and not(@hidden)]"));
        LocalDate tomorrow = today.plusDays(1);

        String formattedCheckOutDate = tomorrow.format(formatter);
        WebElement checkOutDatePath = activeCalendar.findElement(By.xpath(String.format(".//button[@aria-label='%s']", formattedCheckOutDate)));
        Thread.sleep(2000);
        //WebElement checkOutDateButton = driver.findElement(By.xpath(checkOutDatePath));
        String isDisabled = checkOutDatePath.getAttribute("disabled");
        if(isDisabled == "false"){
            checkOutDatePath.click();
        }else{
            System.out.println("Cannot select tomorrow as a checkout date.");
        }
        softAssert.assertAll();


    }
}
