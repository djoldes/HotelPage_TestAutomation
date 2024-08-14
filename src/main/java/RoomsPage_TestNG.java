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
import org.openqa.selenium.interactions.Actions;
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
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

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
        // finding the active calendar
        WebElement activeCalendar = driver.findElement(By.xpath("//div[contains(@class, 'calendar-popup') and not(@hidden)]"));
        LocalDate tomorrow = today.plusDays(1);

        String formattedCheckOutDate = tomorrow.format(formatter);
        WebElement checkOutDatePath = activeCalendar.findElement(By.xpath(String.format(".//button[@aria-label='%s']", formattedCheckOutDate)));
        Thread.sleep(2000);
        String isDisabled = checkOutDatePath.getAttribute("disabled");
        if(isDisabled == "false"){
            checkOutDatePath.click();
        }else{
            softAssert.assertEquals(isDisabled, "false", "Cannot select tomorrow as a check out date.");
        }
        softAssert.assertAll();
    }

    @Test
    private void CheckOutFutureDate() throws InterruptedException {
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
        // finding the active calendar
        WebElement activeCalendar = driver.findElement(By.xpath("//div[contains(@class, 'calendar-popup') and not(@hidden)]"));
        LocalDate futureDate = today.plusDays(10);

        String formattedCheckOutDate = futureDate.format(formatter);
        WebElement checkOutDatePath = activeCalendar.findElement(By.xpath(String.format(".//button[@aria-label='%s']", formattedCheckOutDate)));
        Thread.sleep(2000);
        String isDisabled = checkOutDatePath.getCssValue("disabled");
        System.out.println(isDisabled);
        if(Objects.equals(isDisabled, "")){
            checkOutDatePath.click();
            WebElement checkOutDateLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check_out-value\"]")));
            String cODLText = checkOutDateLabel.getText();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
            Assert.assertEquals(cODLText, futureDate.format(timeFormatter), "Check Out date did not match.");
        }else{
            softAssert.assertEquals(isDisabled, "false", "Cannot select tomorrow as a check out date.");
        }
        softAssert.assertAll();

    }

    @Test
    private void CheckOutPastDate() throws InterruptedException {
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
        // finding the active calendar
        WebElement activeCalendar = driver.findElement(By.xpath("//div[contains(@class, 'calendar-popup') and not(@hidden)]"));
        LocalDate pastDate = today.minusDays(2);

        String formattedCheckOutDate = pastDate.format(formatter);
        WebElement checkOutDatePath = activeCalendar.findElement(By.xpath(String.format(".//button[@aria-label='%s']", formattedCheckOutDate)));
        Thread.sleep(2000);
        String isDisabled = checkOutDatePath.getAttribute("disabled");
        if(isDisabled == "false"){
            checkOutDatePath.click();
        }else{
            softAssert.assertEquals(isDisabled, "false", "Cannot select a past date as a check out date.");
        }
        softAssert.assertAll();
    }

    @Test
    private void PrevoiusMonthCheckOutCalendar(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(mainFrame);

        WebElement checkOutCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-out\"]")));
        checkOutCalendarButton.click();

        //obtaining and comparing the months names
        WebElement previousMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div/nav/button[1]")));
        previousMonthButton.click();
        WebElement actualMonthLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div/div[1]")));
        String actualMonthName = actualMonthLabel.getText();
        String previousMonthName = today.minusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Assert.assertTrue(actualMonthName.contains(previousMonthName), "Previous Month is not displayed correctly.");
    }

    @Test
    private void NextMonthCheckOutCalendar(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(mainFrame);

        WebElement checkOutCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-out\"]")));
        checkOutCalendarButton.click();

        //obtaining and comparing the months names
        WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div/nav/button[2]")));
        nextMonthButton.click();
        WebElement actualMonthLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div/div[1]")));
        String actualMonthName = actualMonthLabel.getText();
        String nextMonthName = today.plusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Assert.assertTrue(actualMonthName.contains(nextMonthName), "Next Month date is not displayed correctly.");
    }

    @Test
    private void AdultsNumberIncrease(){
        //finding the main frame and switching to it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
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
    private void AdultsDecreaseNoLowerThanOne(){
        //finding the main frame and switching to it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
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
    private void AdultsDecreaseButton(){
        //finding the main frame and switching to it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
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
    private void KidsIncreaseButton(){
        //finding the main frame and switching to it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
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
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
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
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
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
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(mainFrame);
        //finding the search button and click on it
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[5]/button")));
        searchButton.click();
        WebElement checkInCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[1]/div[2]")));
        Assert.assertTrue(checkInCalendarFrame.isDisplayed(), "CheckIn Calendar frame is not displayed when click on the search button without any data.");
    }

    @Test
    private void SearchMoreAdults() throws InterruptedException {
        //finding the main frame and switching to it
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
        // finding the active calendar
        WebElement activeCalendar = driver.findElement(By.xpath("//div[contains(@class, 'calendar-popup') and not(@hidden)]"));
        LocalDate futureDate = today.plusDays(10);

        String formattedCheckOutDate = futureDate.format(formatter);
        WebElement checkOutDatePath = activeCalendar.findElement(By.xpath(String.format(".//button[@aria-label='%s']", formattedCheckOutDate)));
        Thread.sleep(2000);
        String isDisabled = checkOutDatePath.getCssValue("disabled");
        System.out.println(isDisabled);
        if(Objects.equals(isDisabled, "")) {
            checkOutDatePath.click();
            WebElement checkOutDateLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check_out-value\"]")));
            String cODLText = checkOutDateLabel.getText();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
            Assert.assertEquals(cODLText, futureDate.format(timeFormatter), "Check Out date did not match.");
        }else{
            softAssert.assertEquals(isDisabled, "false", "Cannot select tomorrow as a check out date.");
        }
        //increase the adults number
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
        //finding the search button and click on it
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[5]/button")));
        searchButton.click();
        //validate the results
        WebElement availableRoomsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[1]/h2/span")));
        String aRTToText = availableRoomsTitle.getText();
        softAssert.assertEquals(aRTToText, "Results for:");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM");
        String monthText = futureDate.format(monthFormatter);
        DateTimeFormatter daysFormatter = DateTimeFormatter.ofPattern("dd");
        String checkInDay = today.format(daysFormatter);
        String checkOutDay = futureDate.format(daysFormatter);
        int year = today.getYear();
        softAssert.assertEquals(aRTToText + monthText + checkInDay + "-" + checkOutDay + "," + year,
                "Results for:" + monthText + checkInDay + "-" + checkOutDay + "," + year, "The search is correct.");

        WebElement list = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("list")));
        // Retrieve the list items
        List<WebElement> listItems = list.findElements(By.tagName("li"));
        softAssert.assertFalse(listItems.isEmpty(), "No results displayed.");
        softAssert.assertAll();
    }

    @Test
    private void SearchMoreKids() throws InterruptedException {
        //finding the main frame and switching to it
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
        // finding the active calendar
        WebElement activeCalendar = driver.findElement(By.xpath("//div[contains(@class, 'calendar-popup') and not(@hidden)]"));
        LocalDate futureDate = today.plusDays(10);

        String formattedCheckOutDate = futureDate.format(formatter);
        WebElement checkOutDatePath = activeCalendar.findElement(By.xpath(String.format(".//button[@aria-label='%s']", formattedCheckOutDate)));
        Thread.sleep(2000);
        String isDisabled = checkOutDatePath.getCssValue("disabled");
        System.out.println(isDisabled);
        if(Objects.equals(isDisabled, "")) {
            checkOutDatePath.click();
            WebElement checkOutDateLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check_out-value\"]")));
            String cODLText = checkOutDateLabel.getText();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
            Assert.assertEquals(cODLText, futureDate.format(timeFormatter), "Check Out date did not match.");
        }else{
            softAssert.assertEquals(isDisabled, "false", "Cannot select tomorrow as a check out date.");
        }
        //searching the KidsIncrease Button and increasing the number of Kids to 1
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
        //finding the search button and click on it
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[5]/button")));
        searchButton.click();
        //validate the results
        WebElement availableRoomsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[1]/h2/span")));
        String aRTToText = availableRoomsTitle.getText();
        softAssert.assertEquals(aRTToText, "Results for:");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM");
        String monthText = futureDate.format(monthFormatter);
        DateTimeFormatter daysFormatter = DateTimeFormatter.ofPattern("dd");
        String checkInDay = today.format(daysFormatter);
        String checkOutDay = futureDate.format(daysFormatter);
        int year = today.getYear();
        softAssert.assertEquals(aRTToText + monthText + checkInDay + "-" + checkOutDay + "," + year,
                "Results for:" + monthText + checkInDay + "-" + checkOutDay + "," + year, "The search is correct.");

        try{
            WebElement list = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("list")));
            // Retrieve the list items
            List<WebElement> listItems = list.findElements(By.tagName("li"));
            softAssert.assertFalse(listItems.isEmpty(), "No results displayed.");
        } catch (Exception e){
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/p/span")));
            String errorMessageText = errorMessage.getText();
            softAssert.assertTrue(errorMessageText.contains("We can’t seem to find what you’re looking for. Try another search."), "Error message not displayed!");
        }
        softAssert.assertAll();
    }

    @Test
    private void SearchAgain() throws InterruptedException {
        //finding the main frame and switching to it
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
        // finding the active calendar
        WebElement activeCalendar = driver.findElement(By.xpath("//div[contains(@class, 'calendar-popup') and not(@hidden)]"));
        LocalDate futureDate = today.plusDays(10);

        String formattedCheckOutDate = futureDate.format(formatter);
        WebElement checkOutDatePath = activeCalendar.findElement(By.xpath(String.format(".//button[@aria-label='%s']", formattedCheckOutDate)));
        Thread.sleep(2000);
        String isDisabled = checkOutDatePath.getCssValue("disabled");
        System.out.println(isDisabled);
        if(Objects.equals(isDisabled, "")) {
            checkOutDatePath.click();
            WebElement checkOutDateLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check_out-value\"]")));
            String cODLText = checkOutDateLabel.getText();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
            Assert.assertEquals(cODLText, futureDate.format(timeFormatter), "Check Out date did not match.");
        }else{
            softAssert.assertEquals(isDisabled, "false", "Cannot select" + futureDate + " as a check out date.");
        }
        //finding the search button and click on it
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[5]/button")));
        searchButton.click();
        WebElement searchAgain = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[5]/button")));
        WebElement checkOutCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-out\"]")));
        checkOutCalendarButton.click();
        LocalDate newCheckOutDate = today.plusDays(5);

        String formattedCheckOutDate2 = newCheckOutDate.format(formatter);
        WebElement checkOutDatePath2 = activeCalendar.findElement(By.xpath(String.format(".//button[@aria-label='%s']", formattedCheckOutDate2)));
        Thread.sleep(2000);
        String isDisabled2 = checkOutDatePath2.getCssValue("disabled");
        System.out.println(isDisabled2);
        if(Objects.equals(isDisabled2, "")) {
            checkOutDatePath2.click();
            WebElement checkOutDateLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check_out-value\"]")));
            String cODLText = checkOutDateLabel.getText();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
            Assert.assertEquals(cODLText, newCheckOutDate.format(timeFormatter), "Check Out date did not match.");
        }else{
            softAssert.assertEquals(isDisabled, "false", "Cannot select the" + newCheckOutDate + " as a check out date.");
        }
        searchAgain.click();
        //validate the results
        WebElement availableRoomsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[1]/h2/span")));
        String aRTToText = availableRoomsTitle.getText();
        softAssert.assertEquals(aRTToText, "Results for:");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM");
        String monthText = newCheckOutDate.format(monthFormatter);
        DateTimeFormatter daysFormatter = DateTimeFormatter.ofPattern("dd");
        String checkInDay = today.format(daysFormatter);
        String checkOutDay = newCheckOutDate.format(daysFormatter);
        int year = today.getYear();
        softAssert.assertEquals(aRTToText + monthText + checkInDay + "-" + checkOutDay + "," + year,
                "Results for:" + monthText + checkInDay + "-" + checkOutDay + "," + year, "The search is not correct.");

        try{
            WebElement list = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("list")));
            // Retrieve the list items
            List<WebElement> listItems = list.findElements(By.tagName("li"));
            softAssert.assertFalse(listItems.isEmpty(), "No results displayed.");
        } catch (Exception e){
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/p/span")));
            String errorMessageText = errorMessage.getText();
            softAssert.assertTrue(errorMessageText.contains("We can’t seem to find what you’re looking for. Try another search."), "Error message not displayed!");
        }
        softAssert.assertAll();
    }

    @Test
    private void ClearButton() throws InterruptedException {
        //finding the main frame and switching to it
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
        // finding the active calendar
        WebElement activeCalendar = driver.findElement(By.xpath("//div[contains(@class, 'calendar-popup') and not(@hidden)]"));
        LocalDate futureDate = today.plusDays(10);

        String formattedCheckOutDate = futureDate.format(formatter);
        WebElement checkOutDatePath = activeCalendar.findElement(By.xpath(String.format(".//button[@aria-label='%s']", formattedCheckOutDate)));
        Thread.sleep(2000);
        String isDisabled = checkOutDatePath.getCssValue("disabled");
        System.out.println(isDisabled);
        if(Objects.equals(isDisabled, "")) {
            checkOutDatePath.click();
            WebElement checkOutDateLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check_out-value\"]")));
            String cODLText = checkOutDateLabel.getText();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
            Assert.assertEquals(cODLText, futureDate.format(timeFormatter), "Check Out date did not match.");
        }else{
            softAssert.assertEquals(isDisabled, "false", "Cannot select" + futureDate + " as a check out date.");
        }
        //finding the search button and click on it
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[5]/button")));
        searchButton.click();
        WebElement clearButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content\"]/div/div[1]/h2/a")));
        clearButton.click();
        WebElement standardPageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[1]/h2")));
        String sPTToText = standardPageTitle.getText();
        softAssert.assertEquals(sPTToText, "Our Rooms","Standard Page was not loaded.");
    }

    @Test
    private void StandardSuiteImage(){
        //finding the main frame and switching to it
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(mainFrame);

        //search the image of standard suite and check if correct
        WebElement image = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[1]/div/div[1]/img")));
        String imageUrl = image.getAttribute("src").toString();
        System.out.println(imageUrl);
        softAssert.assertTrue(imageUrl.contains("ij8o0FbW0FEDKMu4SmcG_large.jpg"), "Image is not the one expected!");
        softAssert.assertAll();
    }

    @Test
    private void StandardSuiteDescription() throws InterruptedException {
        //finding the main frame and switching to it
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(mainFrame);
        Thread.sleep(5000);

        // Validate "Size" text
        WebElement sizeElement = driver.findElement(By.cssSelector("li.size span[stranslate='room.SIZE'] .strans"));
        String sizeText = sizeElement.getText();
        softAssert.assertEquals(sizeText, "Size");

        // Validate "Beds" text
        WebElement bedsElement = driver.findElement(By.cssSelector("li.beds span[stranslate='room.BEDS'] .strans"));
        String bedsText = bedsElement.getText();
        softAssert.assertEquals(bedsText, "Beds");

        // Validate "Size" value
        WebElement sizeValueElement = driver.findElement(By.cssSelector("li.size abbr"));
        String sizeValue = sizeValueElement.getText().trim();
        softAssert.assertEquals(sizeValue, "100 sq m");

        // Validate "Beds" values
        WebElement bedsValueElement = driver.findElement(By.cssSelector("#content > div > div.content-body > div > ul > li:nth-child(1) > div > div.info > div.description > div > ul > li.beds > span:nth-child(3) > span > span"));
        String bedsValue = bedsValueElement.getText();
        softAssert.assertEquals(bedsValue, "1 Double(s)");

        WebElement bedsValueElement2 = driver.findElement(By.cssSelector("#content > div > div.content-body > div > ul > li:nth-child(1) > div > div.info > div.description > div > ul > li.beds > span:nth-child(4) > span > span"));
        String bedsValue2 = bedsValueElement2.getText();
        softAssert.assertEquals(bedsValue2, "1 King(s)");

        softAssert.assertAll();
    }

    @Test
    private void AmenitiesStandardSuite() throws InterruptedException {
        //finding the main frame and switching to it
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        Thread.sleep(5000);
        // Scroll down to the amenities section
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 250)");
        driver.switchTo().frame(mainFrame);
        // Hover over the AC button
        WebElement acButton = driver.findElement(By.cssSelector("div[tooltip='A/C']"));
        Actions action = new Actions(driver);
        action.moveToElement(acButton).perform();
        // Validate the corresponding text appeared
        WebElement acTooltip = driver.findElement(By.cssSelector("div[tooltip='A/C'] .strans"));
        softAssert.assertTrue(acTooltip.isDisplayed());

        // Hover over the TV button
        WebElement tvButton = driver.findElement(By.cssSelector("div[tooltip='TV']"));
        action.moveToElement(tvButton).perform();
        // Validate the corresponding text appeared
        WebElement tvTooltip = driver.findElement(By.cssSelector("div[tooltip='TV'] .strans"));
        softAssert.assertTrue(tvTooltip.isDisplayed());

        // Hover over the Shower button
        WebElement showerButton = driver.findElement(By.cssSelector("div[tooltip='Shower']"));
        action.moveToElement(showerButton).perform();
        // Validate the corresponding text appeared
        WebElement showerTooltip = driver.findElement(By.cssSelector("div[tooltip='Shower'] .strans"));
        softAssert.assertTrue(showerTooltip.isDisplayed());

        // Hover over the Telephone button
        WebElement telephoneButton = driver.findElement(By.cssSelector("div[tooltip='Telephone']"));
        action.moveToElement(telephoneButton).perform();
        // Validate the corresponding text appeared
        WebElement telephoneTooltip = driver.findElement(By.cssSelector("div[tooltip='Telephone'] .strans"));
        softAssert.assertTrue(telephoneTooltip.isDisplayed());

        // Hover over the Bath button
        WebElement bathButton = driver.findElement(By.cssSelector("div[tooltip='Bath']"));
        action.moveToElement(bathButton).perform();
        // Validate the corresponding text appeared
        WebElement bathTooltip = driver.findElement(By.cssSelector("div[tooltip='Bath'] .strans"));
        softAssert.assertTrue(bathTooltip.isDisplayed());

        //Hover over the more info button
        WebElement moreInfoButton = driver.findElement(By.cssSelector("span.more"));
        action.moveToElement(moreInfoButton).perform();
        // Validate the corresponding text appeared
        WebElement moreInfoTooltip = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[1]/div/div[1]/span/span"));
        softAssert.assertTrue(moreInfoTooltip.isDisplayed());
    }
}
