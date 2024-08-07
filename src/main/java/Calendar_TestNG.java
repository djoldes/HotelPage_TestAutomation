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

    LocalDate today = LocalDate.now();
    @Test
    private void DateHiglightedCalendar(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        WebElement CheckInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        CheckInCalendarButton.click();

        driver.switchTo().defaultContent();
        WebElement calendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(calendarFrame);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);

        String formattedDate = today.format(formatter);
        String dateButton = String.format("//button[@aria-label='%s']", formattedDate);
        WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dateButton)));
        Assert.assertTrue(dateButtonCheckIn.getAttribute("class").contains("focused"), "The current day is not highlighted");
    }

    @Test
    private void FutureDateSelection() throws InterruptedException {
        //open the checkin calendar
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        driver.switchTo().defaultContent();
        WebElement checkInCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkInCalendarFrame);

        //performing the future date selection
        LocalDate futureDate = today.plusDays(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);

        String formattedDate1 = futureDate.format(formatter);
        String datesButton = String.format("//button[@aria-label='%s']", formattedDate1);

        WebElement dateButtonCheckIn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(datesButton)));
        dateButtonCheckIn1.click();

        driver.switchTo().defaultContent();
        driver.switchTo().frame(mainFrame);
        WebElement checkInDateSelected = driver.findElement(By.id("check-in-value"));
        String expectedDate = futureDate.format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH));
        Assert.assertEquals(checkInDateSelected.getText(), expectedDate, "Date is not selected.");
    }

    @Test
    private void TodayDateSelection(){
        //open check in calendar
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        driver.switchTo().defaultContent();
        WebElement checkInCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkInCalendarFrame);

        //performing the selection of today
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);

        String formattedDate1 = today.format(formatter);
        String datesButton = String.format("//button[@aria-label='%s']", formattedDate1);

        WebElement dateButtonCheckIn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(datesButton)));
        dateButtonCheckIn1.click();

        driver.switchTo().defaultContent();
        driver.switchTo().frame(mainFrame);
        WebElement checkInDateSelected = driver.findElement(By.id("check-in-value"));
        String expectedDate = today.format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH));
        Assert.assertEquals(checkInDateSelected.getText(), expectedDate, "Date is not selected.");
    }

    @Test
    public void PastDaySelection(){
        //open check in calendar
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        driver.switchTo().defaultContent();
        WebElement checkInCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkInCalendarFrame);

        //performing the past day selection
        SoftAssert softAssert = new SoftAssert();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);

        LocalDate pastDay = today.minusDays(2);
        String formattedDate1 = pastDay.format(formatter);
        String datesButton = String.format("//button[@aria-label='%s']", formattedDate1);

        WebElement dateButtonCheckIn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(datesButton)));
        Assert.assertTrue(!dateButtonCheckIn1.isEnabled(), "Past Day can be selected");
    }

    @Test
    public void PrevoiusMonth(){
        //open check in calendar
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        driver.switchTo().defaultContent();
        WebElement checkInCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkInCalendarFrame);

        //obtaining and comparing the months names
        WebElement previousMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/nav/button[1]")));
        previousMonthButton.click();
        WebElement actualMonthLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div/div[1]")));
        String actualMonthName = actualMonthLabel.getText();
        String previousMonthName = today.minusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Assert.assertTrue(actualMonthName.contains(previousMonthName), "Previous Month date is not displayed correctly.");
    }

    @Test
    public void NextMonth(){
        //open check in calendar
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        driver.switchTo().defaultContent();
        WebElement checkInCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkInCalendarFrame);

        //obtaining and comparing the months names
        WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/nav/button[2]")));
        nextMonthButton.click();
        WebElement actualMonthLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div/div[1]")));
        String actualMonthName = actualMonthLabel.getText();
        String nextMonthName = today.plusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Assert.assertTrue(actualMonthName.contains(nextMonthName), "Next Month date is not displayed correctly.");
    }

    @Test
    public void CloseButton() throws InterruptedException {
        //open check in calendar
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        driver.switchTo().defaultContent();
        WebElement checkInCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkInCalendarFrame);

        //search & click on the close button of the checkin calendar
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='close ng-scope']")));
        closeButton.click();
        //verify if the checkin calendar is closed after clicking on the close button
        driver.switchTo().frame(mainFrame);
        boolean isCalendarClosed = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]"))).isDisplayed();
        Assert.assertTrue(isCalendarClosed, "Check In calendar is not closed!");
    }

    @Test
    public void CheckOutNextDay() throws InterruptedException {
        //open check in calendar
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        WebElement CheckInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        CheckInCalendarButton.click();

        driver.switchTo().defaultContent();
        WebElement checkInCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkInCalendarFrame);

        //performing the selection of today as checkin date
        SoftAssert softAssert = new SoftAssert();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);

        String formattedCheckInDate = today.format(formatter);
        String checkInDatePath = String.format("//button[@aria-label='%s']", formattedCheckInDate);

        WebElement checkInDateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(checkInDatePath)));
        checkInDateButton.click();

        driver.switchTo().defaultContent();

        //check if the checkout calendar is opened automatically
        WebElement checkOutCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        boolean checkOutCalendarDisplayed= checkOutCalendarFrame.isDisplayed();
        softAssert.assertTrue(checkOutCalendarDisplayed, "Check Out Calendar is not displayed after selecting a check-in date.");

        //select tommorrow as a checkout date
        driver.switchTo().defaultContent();
        driver.switchTo().frame(checkOutCalendarFrame);
        LocalDate tommorrow = today.plusDays(1);

        String formattedCheckOutDate = tommorrow.format(formatter);
        String checkOutDatePath = String.format("//button[@aria-label='%s']", formattedCheckOutDate);
        Thread.sleep(2000);
        WebElement checkOutDateButton = driver.findElement(By.xpath(checkOutDatePath));
        // Check if possible to select tommorrow as a checkout date
        try{
            checkOutDateButton.isEnabled();
            checkOutDateButton.click();
            driver.switchTo().defaultContent();
            driver.switchTo().frame(mainFrame);
            WebElement checkOutDateSelected = driver.findElement(By.id("check-out-value"));
            String expectedDate = tommorrow.format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH));
            softAssert.assertEquals(checkOutDateSelected.getText(), expectedDate, "Cannot select "+tommorrow+" as a checkout date!");
        }catch (Exception e){
            softAssert.assertTrue(checkOutDateButton.isEnabled(), "Cannot select tommorow as a check-out date.");
        }
        softAssert.assertAll();
    }
}


/*@Test
    public void CheckInCalendarTest() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        Thread.sleep(5000);

        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        WebElement calendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        calendarButton.click();
        try {
            driver.switchTo().defaultContent();
            WebElement calendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
            driver.switchTo().frame(calendarFrame);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);

            LocalDate today = LocalDate.now();
            LocalDate futureDate = today.plusDays(10);

            String formattedDate = today.format(formatter);
            String dateButton = String.format("//button[@aria-label='%s']", formattedDate);
            WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dateButton)));
            softAssert.assertTrue(dateButtonCheckIn.getAttribute("class").contains("focused"), "The current day is not highlighted");
           // driver.switchTo().defaultContent();

            LocalDate[] datesToCheck = {today, futureDate};

            for(LocalDate date : datesToCheck) {

                String formattedDate1 = date.format(formatter);
                String datesButton = String.format("//button[@aria-label='%s']", formattedDate1);

                WebElement dateButtonCheckIn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(datesButton)));
                Thread.sleep(50000);
                dateButtonCheckIn1.click();

                driver.switchTo().defaultContent();
                driver.switchTo().frame(mainFrame);

                WebElement checkInDateSelected = driver.findElement(By.id("check-in-value"));
                String expectedDate = date.format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH));
                Assert.assertEquals(checkInDateSelected.getText(), expectedDate, "Date is not selected.");
                //System.out.println(checkInDateSelected.getText());

                Thread.sleep(5000);

                driver.switchTo().defaultContent();
                driver.navigate().refresh();
                mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
                driver.switchTo().frame(mainFrame);

                calendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
                calendarButton.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            softAssert.assertAll();
        }
    }*/