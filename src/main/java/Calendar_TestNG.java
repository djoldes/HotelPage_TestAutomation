//--
//--
//import javafx.scene.web.WebEngine;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
//--
//--
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//--
//--
import org.openqa.selenium.support.Color;
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
    private void DateHiglightedCalendarCheckInCalendar(){

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
    private void FutureDateSelectionCheckInCalendar() throws InterruptedException {
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
    private void TodayDateSelectionCheckInCalendar(){
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
    private void PastDaySelectionCheckInCalendar(){
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
    private void PrevoiusMonthCheckInCalendar(){
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
        Assert.assertTrue(actualMonthName.contains(previousMonthName), "Previous Month is not displayed correctly.");
    }

    @Test
    private void NextMonthCheckInCalendar(){
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
    private void CloseButtonCheckInCalendar() throws InterruptedException {
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
    private void CheckOutNextDay() throws InterruptedException {
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

        //select tomorrow as a checkout date
        driver.switchTo().defaultContent();
        driver.switchTo().frame(checkOutCalendarFrame);
        LocalDate tomorrow = today.plusDays(1);

        String formattedCheckOutDate = tomorrow.format(formatter);
        String checkOutDatePath = String.format("//button[@aria-label='%s']", formattedCheckOutDate);
        Thread.sleep(2000);
        WebElement checkOutDateButton = driver.findElement(By.xpath(checkOutDatePath));
        // Check if possible to select tomorrow as a checkout date
        try{
            checkOutDateButton.isEnabled();
            checkOutDateButton.click();
            driver.switchTo().defaultContent();
            driver.switchTo().frame(mainFrame);
            WebElement checkOutDateSelected = driver.findElement(By.id("check-out-value"));
            String expectedDate = tomorrow.format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH));
            softAssert.assertEquals(checkOutDateSelected.getText(), expectedDate, "Cannot select "+tomorrow+" as a checkout date!");
        }catch (Exception e){
            softAssert.assertTrue(checkOutDateButton.isEnabled(), "Cannot select tommorow as a check-out date.");
        }
        softAssert.assertAll();
    }

    @Test
    private void PreviousMonthCheckOutCalendar(){
        //finding the main frame and switching to it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        //finding and opening the checkout calendar
        WebElement checkOutCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-out\"]")));
        checkOutCalendarButton.click();
        driver.switchTo().defaultContent();
        WebElement checkOutCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkOutCalendarFrame);

        //obtaining and comparing the months names
        WebElement previousMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/nav/button[1]")));
        previousMonthButton.click();
        WebElement actualMonthLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div/div[1]")));
        String actualMonthName = actualMonthLabel.getText();
        String previousMonthName = today.minusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Assert.assertTrue(actualMonthName.contains(previousMonthName), "Previous Month is not displayed correctly.");
    }

    @Test
    private void NextMonthCheckOutCalendar(){
        //finding the main frame and switching to it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        //finding and opening the checkout calendar
        WebElement checkOutCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-out\"]")));
        checkOutCalendarButton.click();
        driver.switchTo().defaultContent();
        WebElement checkOutCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkOutCalendarFrame);

        //obtaining and comparing the months names
        WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/nav/button[2]")));
        nextMonthButton.click();
        WebElement actualMonthLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div/div[1]")));
        String actualMonthName = actualMonthLabel.getText();
        String nextMonthName = today.plusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Assert.assertTrue(actualMonthName.contains(nextMonthName), "Next Month date is not displayed correctly.");
    }

    @Test
    private void CloseButtonCheckOutCalendar(){
        //finding the main frame and switching to it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        //finding and opening the checkout calendar
        WebElement checkOutCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-out\"]")));
        checkOutCalendarButton.click();
        driver.switchTo().defaultContent();
        WebElement checkOutCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkOutCalendarFrame);

        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='close ng-scope']")));
        closeButton.click();
        //verify if the checkout calendar is closed after clicking on the close button
        driver.switchTo().frame(mainFrame);
        boolean isCalendarClosed = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-out\"]"))).isDisplayed();
        Assert.assertTrue(isCalendarClosed, "Check Out calendar is not closed!");
    }

    @Test
    private void AdultsIncreaseButton(){
        //finding the main frame and switching to it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        //searching the AdultsIncrease Button and increasing the number of Adults to 2
        SoftAssert softAssert = new SoftAssert();
        WebElement adultsIncreaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"adults\"]/a[1]")));
        softAssert.assertTrue(adultsIncreaseButton.isDisplayed(),"Adults increase button is not displayed.");
        try{
            adultsIncreaseButton.click();
            WebElement adultsNumberLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
            String  currentAdultsNumber = adultsNumberLabel.getText();
            softAssert.assertEquals(currentAdultsNumber, "2", "Adults number did not increase correctly.");
        } catch (Exception e){
            System.out.println("Adults increase button cannot be clicked.");
        }

        softAssert.assertAll();
    }

    @Test
    private void AdultsDecreaseButton(){
        //finding the main frame and switching to it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        //searching the AdultsIncrease Button and increasing the number of Adults to 2
        SoftAssert softAssert = new SoftAssert();
        WebElement adultsIncreaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"adults\"]/a[1]")));
        adultsIncreaseButton.click();
        //searching the AdultsDecrease Button and decreasing the number of Adults to 1
        WebElement adultsDecreaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"adults\"]/a[2]")));
        softAssert.assertTrue(adultsDecreaseButton.isDisplayed(),"Adults decrease button is not displayed.");
        try{
            adultsDecreaseButton.click();
            WebElement adultsNumberLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
            String  currentAdultsNumber = adultsNumberLabel.getText();
            softAssert.assertEquals(currentAdultsNumber, "1", "Adults number did not decrease correctly.");
        } catch (Exception e){
            System.out.println("Adults decrease button cannot be clicked.");
        }
        softAssert.assertAll();
    }

    @Test
    private void AdultsDecreaseNoLowerThanOne(){
        //finding the main frame and switching to it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);
        //searching the AdultsDecrease Button, checking if default number is 1 and that it cannot be decreased
        //lower than 1
        SoftAssert softAssert = new SoftAssert();
        WebElement adultsDecreaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"adults\"]/a[2]")));
        softAssert.assertTrue(adultsDecreaseButton.isDisplayed(),"Adults decrease button is not displayed.");

        WebElement adultsNumberLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
        String  defaultAdultsNumber = adultsNumberLabel.getText();
        softAssert.assertEquals(defaultAdultsNumber, "1", "Default adults number is not 1.");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean isDisabled = (Boolean) js.executeScript("return arguments[0].hasAttribute('disabled');", adultsDecreaseButton);
        if(!isDisabled){
            adultsDecreaseButton.click();
            WebElement adultsNumberLabel1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
            String  currentAdultsNumber1 = adultsNumberLabel1.getText();
            softAssert.assertEquals(currentAdultsNumber1, "0","Adults number cannot be decreased lower than 1.");
        } else{
            System.out.println("Adults number cannot be decreased lower than 1.");
        }
        softAssert.assertAll();
    }

    @Test
    private void KidsIncreaseButton(){
        //finding the main frame and switching to it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        //searching the KidsIncrease Button and increasing the number of Kids to 1
        SoftAssert softAssert = new SoftAssert();
        WebElement kidsIncreaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"children\"]/a[1]")));

        softAssert.assertTrue(kidsIncreaseButton.isDisplayed(),"Adults increase button is not displayed.");
        try{
            kidsIncreaseButton.click();
            WebElement kidsNumberLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]/span[1]")));
            String currentKidsNumber = kidsNumberLabel.getText();
            softAssert.assertEquals(currentKidsNumber, "1", "Kids number did not increase correctly.");
        } catch (Exception e){
            System.out.println("Kids increase button cannot be clicked.");
        }

        softAssert.assertAll();
    }

    @Test
    private void KidsDecreaseButton(){
        //finding the main frame and switching to it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);

        //searching the KidsIncrease Button and increasing the number of Kids to 1
        SoftAssert softAssert = new SoftAssert();
        WebElement kidsIncreaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"children\"]/a[1]")));
        kidsIncreaseButton.click();
        //searching the KidsDecrease Button and decreasing the number of Kids to 0
        WebElement kidsDecreaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"children\"]/a[2]")));
        softAssert.assertTrue(kidsDecreaseButton.isDisplayed(),"Kids decrease button is not displayed.");
        try{
            kidsDecreaseButton.click();
            WebElement kidsNumberLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]/span[1]")));
            String currentKidsNumber = kidsNumberLabel.getText();
            softAssert.assertEquals(currentKidsNumber, "0", "Kids number did not decrease correctly.");
        } catch (Exception e){
            System.out.println("Kids decrease button cannot be clicked.");
        }
        softAssert.assertAll();
    }

    @Test
    private void KidsDecreasedNoLowerThanZero(){
        //finding the main frame and switching to it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);
        //searching the AdultsDecrease Button, checking if default number is 1 and that it cannot be decreased
        //lower than 1
        SoftAssert softAssert = new SoftAssert();
        WebElement kidsDecreaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"children\"]/a[2]")));
        softAssert.assertTrue(kidsDecreaseButton.isDisplayed(),"Kids decrease button is not displayed.");

        WebElement kidsNumberLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]/span[1]")));
        String defaultKidsNumber = kidsNumberLabel.getText();
        softAssert.assertEquals(defaultKidsNumber, "0", "Default kids number is not 0.");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean isDisabled = (Boolean) js.executeScript("return arguments[0].hasAttribute('disabled');", kidsDecreaseButton);
        if(!isDisabled){
            kidsDecreaseButton.click();
            WebElement kidsNumberLabel1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
            String currentKidsNumber1 = kidsNumberLabel1.getText();
            softAssert.assertEquals(currentKidsNumber1, "-1","Kids number cannot be decreased lower than 0.");
        } else{
            System.out.println("Kids number cannot be decreased lower than 0.");
        }
        softAssert.assertAll();
    }

    @Test
    private void SearchButton(){
        //finding the main frame and switching to it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);
        //finding the search button and click on it
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"search-widget\"]/form/ul/li[6]/button")));
        searchButton.click();
        driver.switchTo().defaultContent();
        WebElement checkInCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        Assert.assertTrue(checkInCalendarFrame.isDisplayed(), "CheckIn Calendar frame is not displayed when click on the search button without any data.");
    }

    @Test
    private void PerformSearch() throws InterruptedException {
        //open check in calendar
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(mainFrame);
        //get the adults and kids number
        WebElement adultsNumberLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
        String  currentAdultsNumber = adultsNumberLabel.getText();
        WebElement kidsNumberLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]/span[1]")));
        String currentKidsNumber = kidsNumberLabel.getText();
        WebElement CheckInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        CheckInCalendarButton.click();
        driver.switchTo().defaultContent();
        WebElement checkInCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkInCalendarFrame);

        //performing the selection of today as checkin date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
        String formattedCheckInDate = today.format(formatter);
        String checkInDatePath = String.format("//button[@aria-label='%s']", formattedCheckInDate);
        WebElement checkInDateButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(checkInDatePath)));
        Thread.sleep(3000);
        checkInDateButton.click();
        driver.switchTo().defaultContent();

        //performing the selection of a future date as checkout date
        WebElement checkOutCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(checkOutCalendarFrame);
        LocalDate futureDate = today.plusDays(10);
        String formattedCheckOutDate = futureDate.format(formatter);
        String checkOutDatePath = String.format("//button[@aria-label='%s']", formattedCheckOutDate);
        Thread.sleep(2000);
        WebElement checkOutDateButton = driver.findElement(By.xpath(checkOutDatePath));
        checkOutDateButton.click();



        //click on the search button and validate that you're redirected to the rooms page
        driver.switchTo().defaultContent();
        driver.switchTo().frame(mainFrame);
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"search-widget\"]/form/ul/li[6]/button")));
        searchButton.click();
        String expectedUrlPart = "https://ancabota09.wixsite.com/intern/rooms";
        String actualUrl = driver.getCurrentUrl();
        softAssert.assertTrue(actualUrl.contains(expectedUrlPart));
        driver.switchTo().defaultContent();

        //validate that the rooms button color changed to white
        WebElement roomsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6kl732v2label\"]")));
        Color roomsColor = Color.fromString(roomsLink.getCssValue("color"));
        Color expectedColor = Color.fromString("#FFFFFF");
        softAssert.assertTrue(roomsColor.equals(expectedColor), "The color of Rooms menu button did not change when redirected to the Rooms page.");


        //validate that the form in the rooms page is completed after the search
        driver.switchTo().defaultContent();
        WebElement roomsFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(roomsFrame);
        //check-in date
        WebElement checkInRoomsDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check_in-value\"]")));
        String cIRD = checkInRoomsDate.getText();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
        softAssert.assertEquals(cIRD, today.format(timeFormatter), "Check In date did not match.");
        //check-out date
        WebElement checkOutRoomsDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check_out-value\"]")));
        String cORD = checkOutRoomsDate.getText();
        softAssert.assertEquals(cORD, futureDate.format(timeFormatter), "Check Out date did not match.");
        //Adults number
        WebElement adultsNumberRooms = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]")));
        String adultsNRText = adultsNumberRooms.getAttribute("aria-valuenow");
        softAssert.assertEquals(adultsNRText, currentAdultsNumber,"Adults number did not match.");
        //Kids number
        WebElement kidsNumberRooms = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]")));
        String kidsNRText = kidsNumberRooms.getAttribute("aria-valuenow");
        softAssert.assertEquals(kidsNRText, currentKidsNumber,"Kids number did not match.");

        softAssert.assertAll();
    }
}