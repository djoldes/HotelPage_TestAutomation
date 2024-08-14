import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

public class StandardRoomPage_TestNG {
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

    private void SwitchToStandardRoom() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(mainFrame);
        Thread.sleep(5000);
        WebElement standardRoomButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[1]/div/div[1]"));
        standardRoomButton.click();
        driver.switchTo().defaultContent();
        Thread.sleep(5000);
        WebElement bookFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6klgqap_0 > iframe")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(bookFrame);
    }

    @Test
    private void StandardRoomDescription() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        SwitchToRoomsPage();
        WebElement mainFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(mainFrame);
        SoftAssert softAssert = new SoftAssert();
        Thread.sleep(5000);
        WebElement standardRoomButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[1]/div/div[1]"));
        standardRoomButton.click();
        driver.switchTo().defaultContent();
        Thread.sleep(5000);
        WebElement bookFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6klgqap_0 > iframe")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(bookFrame);
        // Validate Properties
        WebElement accommodates = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[3]/div[2]/div[1]/div/ul/li[1]"));
        softAssert.assertTrue(accommodates.isDisplayed(), "Accommodates: 6 is displayed");

        WebElement size = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[3]/div[2]/div[1]/div/ul/li[2]"));
        softAssert.assertTrue(size.isDisplayed(), "Size: 100 sq m is displayed");

        WebElement beds = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[3]/div[2]/div[1]/div/ul/li[3]"));
        softAssert.assertTrue(beds.isDisplayed(), "Beds: 1 Double(s), 1 King(s) is displayed");

        // Validate Amenities
        WebElement ac = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[3]/div[2]/div[2]/ul/li[1]"));
        softAssert.assertTrue(ac.isDisplayed(), "A/C is displayed");

        WebElement tv = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[3]/div[2]/div[2]/ul/li[2]"));
        softAssert.assertTrue(tv.isDisplayed(), "TV is displayed");

        WebElement shower = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[3]/div[2]/div[2]/ul/li[3]"));
        softAssert.assertTrue(shower.isDisplayed(), "Shower is displayed");

        WebElement telephone = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[3]/div[2]/div[2]/ul/li[4]"));
        softAssert.assertTrue(telephone.isDisplayed(), "Telephone is displayed");

        WebElement bath = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[3]/div[2]/div[2]/ul/li[5]"));
        softAssert.assertTrue(bath.isDisplayed(), "Bath is displayed");

        // Validate Check In and Out
        WebElement checkIn = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[3]/div[2]/div[3]/ul/li[1]"));
        softAssert.assertTrue(checkIn.isDisplayed(), "Check-In: 02:00 PM is displayed");

        WebElement checkOut = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[3]/div[2]/div[3]/ul/li[2]"));
        softAssert.assertTrue(checkOut.isDisplayed(), "Check-Out: 12:00 PM is displayed");

        // Validate Terms
        WebElement minimumNights = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[3]/div[2]/div[4]/ul/li[1]/div/div"));
        softAssert.assertTrue(minimumNights.isDisplayed(), "Minimum nights: 3 is displayed");

        WebElement readPolicies = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[3]/div[2]/div[4]/ul/li[2]/span"));
        softAssert.assertTrue(readPolicies.isDisplayed(), "Read Our Policies is displayed");

        softAssert.assertAll();
    }

    @Test
    private void TodayDateSelectionCheckInCalendar() throws InterruptedException {
        SwitchToRoomsPage();
        SwitchToStandardRoom();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //performing the selection of today
        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);

        String formattedDate1 = today.format(formatter);
        String datesButton = String.format("//button[@aria-label='%s']", formattedDate1);

        WebElement dateButtonCheckIn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(datesButton)));
        dateButtonCheckIn1.click();

        //check if selected check in date is the same in the check in label after selection
        WebElement checkInRoomsDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check_in-value\"]")));
        String cIRD = checkInRoomsDate.getText();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
        Assert.assertEquals(cIRD, today.format(timeFormatter), "Check In date did not match.");
    }

    @Test
    private void FutureDateSelectionCheckInCalendar() throws InterruptedException {
        SwitchToRoomsPage();
        SwitchToStandardRoom();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //performing the selection of today
        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
        LocalDate futureDate = today.plusDays(5);

        String formattedDate1 = futureDate.format(formatter);
        String datesButton = String.format("//button[@aria-label='%s']", formattedDate1);

        WebElement dateButtonCheckIn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(datesButton)));
        dateButtonCheckIn1.click();

        //check if selected check in date is the same in the check in label after selection
        WebElement checkInRoomsDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check_in-value\"]")));
        String cIRD = checkInRoomsDate.getText();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
        Assert.assertEquals(cIRD, futureDate.format(timeFormatter), "Check In date did not match.");
    }

    @Test
    private void PastDateSelectionCheckInCalendar() throws InterruptedException {
        SwitchToRoomsPage();
        SwitchToStandardRoom();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //performing the selection of today
        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
        LocalDate pastDate = today.minusDays(5);

        String formattedDate1 = pastDate.format(formatter);
        String datesButton = String.format("//button[@aria-label='%s']", formattedDate1);

        WebElement dateButtonCheckIn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(datesButton)));
        dateButtonCheckIn1.click();
        boolean canSelect = false;
        //check if selected check in date is the same in the check in label after selection
        try {
            WebElement checkInRoomsDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check_in-value\"]")));
            canSelect = true;
            String cIRD = checkInRoomsDate.getText();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
            Assert.assertEquals(cIRD, pastDate.format(timeFormatter), "Check In date did not match.");
        } catch (Exception e) {
            Assert.assertTrue(canSelect, "Cannot select a past date as a check in date!");
        }
    }

    @Test
    private void PrevoiusMonthCheckInCalendar() throws InterruptedException {
        SwitchToRoomsPage();
        SwitchToStandardRoom();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        //obtaining and comparing the months names
        WebElement previousMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[2]/div/form/ul/li[1]/div[2]/div/nav/button[1]")));
        previousMonthButton.click();
        WebElement actualMonthLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[2]/div/form/ul/li[1]/div[2]/div/div[1]")));
        String actualMonthName = actualMonthLabel.getText();
        String previousMonthName = today.minusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Assert.assertTrue(actualMonthName.contains(previousMonthName), "Previous Month is not displayed correctly.");
    }

    @Test
    private void NextMonthCheckInCalendar() throws InterruptedException {
        SwitchToRoomsPage();
        SwitchToStandardRoom();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();
        //obtaining and comparing the months names
        WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[2]/div/form/ul/li[1]/div[2]/div/nav/button[2]")));
        nextMonthButton.click();
        WebElement actualMonthLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[2]/div/form/ul/li[1]/div[2]/div/div[1]")));
        String actualMonthName = actualMonthLabel.getText();
        String nextMonthName = today.plusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Assert.assertTrue(actualMonthName.contains(nextMonthName), "Next Month date is not displayed correctly.");
    }

    @Test
    private void CheckOutNextDay() throws InterruptedException {
        SwitchToRoomsPage();
        SwitchToStandardRoom();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        //performing the selection of today
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);

        String formattedDate1 = today.format(formatter);
        String datesButton = String.format("//button[@aria-label='%s']", formattedDate1);

        WebElement dateButtonCheckIn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(datesButton)));
        dateButtonCheckIn1.click();

        //check if the checkout calendar is opened automatically
        WebElement checkOutCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[2]/div/form/ul/li[2]/div[2]/div")));
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
    private void PrevoiusMonthCheckOutCalendar() throws InterruptedException {
        SwitchToRoomsPage();
        SwitchToStandardRoom();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement checkOutCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-out\"]")));
        checkOutCalendarButton.click();
        //obtaining and comparing the months names
        WebElement previousMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[2]/div/form/ul/li[2]/div[2]/div/nav/button[1]")));
        previousMonthButton.click();
        WebElement actualMonthLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[2]/div/form/ul/li[2]/div[2]/div/div[1]")));
        String actualMonthName = actualMonthLabel.getText();
        String previousMonthName = today.minusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Assert.assertTrue(actualMonthName.contains(previousMonthName), "Previous Month is not displayed correctly.");
    }

    @Test
    private void NextMonthCheckOutCalendar() throws InterruptedException {
        SwitchToRoomsPage();
        SwitchToStandardRoom();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement checkOutCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-out\"]")));
        checkOutCalendarButton.click();
        //obtaining and comparing the months names
        WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[2]/div/form/ul/li[2]/div[2]/div/nav/button[2]")));
        nextMonthButton.click();
        WebElement actualMonthLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[2]/div/form/ul/li[2]/div[2]/div/div[1]")));
        String actualMonthName = actualMonthLabel.getText();
        String nextMonthName = today.plusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Assert.assertTrue(actualMonthName.contains(nextMonthName), "Next Month date is not displayed correctly.");
    }

    @Test
    private void CheckOutFutureDate() throws InterruptedException {
        SwitchToRoomsPage();
        SwitchToStandardRoom();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        //performing the selection of today
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);

        String formattedDate1 = today.format(formatter);
        String datesButton = String.format("//button[@aria-label='%s']", formattedDate1);

        WebElement dateButtonCheckIn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(datesButton)));
        dateButtonCheckIn1.click();

        //check if the checkout calendar is opened automatically
        WebElement checkOutCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[2]/div/form/ul/li[2]/div[2]")));
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
    private void AdultsNumberIncrease() throws InterruptedException {
        SwitchToRoomsPage();
        SwitchToStandardRoom();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //searching the AdultsIncrease Button and increasing the number of Adults to 2
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
    private void AdultsDecreaseButton() throws InterruptedException {
        SwitchToRoomsPage();
        SwitchToStandardRoom();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //searching the AdultsIncrease Button and increasing the number of Adults to 2
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
    private void AdultsDecreaseNoLowerThanOne() throws InterruptedException {
        SwitchToRoomsPage();
        SwitchToStandardRoom();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //searching the AdultsDecrease Button, checking if default number is 1 and that it cannot be decreased
        //lower than 1
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
    private void BookNowButton() throws InterruptedException {
        SwitchToRoomsPage();
        SwitchToStandardRoom();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        //performing the selection of today
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);

        String formattedDate1 = today.format(formatter);
        String datesButton = String.format("//button[@aria-label='%s']", formattedDate1);

        WebElement dateButtonCheckIn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(datesButton)));
        dateButtonCheckIn1.click();

        //check if the checkout calendar is opened automatically
        WebElement checkOutCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[2]/div/form/ul/li[2]/div[2]")));
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
        WebElement bookNowButton = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[4]/span/button"));
        bookNowButton.click();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://ancabota09.wixsite.com/intern/reservation&payment";
        softAssert.assertEquals(currentUrl, expectedUrl, "Book Now button not redirecting to the payment page!");
        softAssert.assertAll();
    }

    @Test
    private void AdultsNumberMoreThan4() throws InterruptedException {
        SwitchToRoomsPage();
        SwitchToStandardRoom();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement checkInCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));
        checkInCalendarButton.click();

        //performing the selection of today
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);

        String formattedDate1 = today.format(formatter);
        String datesButton = String.format("//button[@aria-label='%s']", formattedDate1);

        WebElement dateButtonCheckIn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(datesButton)));
        dateButtonCheckIn1.click();

        //check if the checkout calendar is opened automatically
        WebElement checkOutCalendarFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[2]/div/form/ul/li[2]/div[2]")));
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
        //searching the AdultsIncrease Button and increasing the number of Adults to 2
        WebElement adultsIncreaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"adults\"]/a[1]")));
        softAssert.assertTrue(adultsIncreaseButton.isDisplayed(),"Adults increase button is not displayed.");
        WebElement adultsNumberLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
        String currentAdultsNumber = adultsNumberLabel.getText();
        int nrAdults = Integer.parseInt(currentAdultsNumber);
        while(nrAdults<4){
            adultsIncreaseButton.click();
            nrAdults++;
        }
        WebElement totalPriceBottom = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[3]/table/tbody/tr[2]/td[2]"));
        String totalPriceBottomText = totalPriceBottom.getText();
        WebElement totalPrice = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[1]/span[2]"));
        String totalPriceText = totalPrice.getText();
        softAssert.assertEquals(totalPriceText, totalPriceBottomText, "Price did not update correctly!");

        softAssert.assertAll();
    }

    @Test
    private void RoomPoliciesPage() throws InterruptedException {
        SwitchToRoomsPage();
        SwitchToStandardRoom();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement roomPoliciesButton = driver.findElement(By.xpath("//*[@id=\"singleroom\"]/div[3]/div[2]/div[4]/ul/li[2]/span/a"));
        roomPoliciesButton.click();

        String originalWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://hotels.wixapps.net/index.html?pageId=crop&compId=i6klgqap_0&viewerCompId=i6klgqap_0&siteRevision=3&viewMode";
        softAssert.assertTrue(currentUrl.contains(expectedUrl) ,"Rooms Policies Page is not displayed successfully after click on the Policies button.");
        softAssert.assertAll();
    }
}
