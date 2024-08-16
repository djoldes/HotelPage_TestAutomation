//--
//--
//import javafx.scene.web.WebEngine;
import org.openqa.selenium.*;
//--
//--
import org.openqa.selenium.chrome.ChromeDriver;
//--
//--
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
//--
import org.testng.annotations.BeforeMethod;
//--
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContactPage_TestNG {
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

    private void SwitchToTheContactPage(){
        WebElement contactLink = driver.findElement(By.xpath("//*[@id=\"i6kl732v3\"]/a"));
        contactLink.click();
    }

    @Test
    private void ContactUsSection(){
        SwitchToTheContactPage();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6ly3ckd\"]")));
        softAssert.assertTrue(pageTitle.isDisplayed(), "Page title is not displayed.");
        String pageTitleText = pageTitle.getText();
        softAssert.assertEquals(pageTitleText, "CONTACT US", "Title of the page is not correct.");
        WebElement contactUsInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6ly3ckc_0\"]")));
        softAssert.assertTrue(contactUsInfo.isDisplayed(), "The info section is not displayed.");
        String contactUsInfoText = contactUsInfo.getText();
        softAssert.assertTrue(contactUsInfoText.contains("Contact Us"), "There is no info about how to contact the hotel.");
        softAssert.assertAll();
    }

    @Test
    private void MapNavigation(){
        SwitchToTheContactPage();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mapFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mapContainer_i6lyzjsh\"]/iframe")));
        driver.switchTo().frame(mapFrame);
        WebElement mapCanvas = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"map_canvas\"]")));
        softAssert.assertTrue(mapCanvas.isDisplayed());
    }

    @Test
    private void FullScreenAndExitFullScreenMap(){
        SwitchToTheContactPage();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mapFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mapContainer_i6lyzjsh\"]/iframe")));
        driver.switchTo().frame(mapFrame);
        WebElement fullScreenButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"map_canvas\"]/div/div[3]/div[7]/button")));
        fullScreenButton.click();

        // Verify the map is in full screen mode
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Boolean isFullscreen = (Boolean) jse.executeScript("return document.fullscreenElement != null;");
        softAssert.assertTrue(isFullscreen, "The map is not displayed in fullscreen mode.");

        WebElement toggleFullScreen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"map_canvas\"]/div/div[3]/div[7]/button")));
        toggleFullScreen.click();
        Boolean isNotFullScreen = (Boolean) jse.executeScript("return document.fullscreenElement == null;");
        softAssert.assertTrue(isNotFullScreen, "The fullscreen mode is not closed.");
    }

    @Test
    private void FormDataTest() throws InterruptedException {
        SwitchToTheContactPage();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0,250)");

        // Test name input
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input_comp-jxbsa1e9\"]")));
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"input_comp-jxbsa1em\"]"));
        WebElement phoneInput = driver.findElement(By.xpath("//*[@id=\"input_comp-jxbsa1ev\"]"));
        List<String> nameTestData = new ArrayList<>();
        nameTestData.add("a");
        nameTestData.add("9");
        nameTestData.add("Ana1234");
        nameTestData.add("12ana");
        nameTestData.add("An123a");
        for (String testName : nameTestData) {
            nameInput.sendKeys(testName);
            String backgroundColor = nameInput.getCssValue("background-color");
            String expectedBGColor = "rgba(237, 209, 50, 1)";
            softAssert.assertEquals(backgroundColor, expectedBGColor, "The Background color did not change to yellow.");
            emailInput.click();
            try {
                String ariaInvalidValue = nameInput.getAttribute("aria-invalid");
                softAssert.assertEquals(ariaInvalidValue, "false", testName + " is not a valid name.");
            } catch (NoSuchElementException e) {
                System.out.println("Cannot enter a testname in the name field.");
            }
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='';", nameInput);
        }

        // Test email input data
        List<String> emailTestData = new ArrayList<>();
        emailTestData.add("angbd");
        emailTestData.add("angbd@.com");
        emailTestData.add("anabgd@gmail");
        for (String testEmail : emailTestData) {
            emailInput.sendKeys(testEmail);
            String backgroundColor = emailInput.getCssValue("background-color");
            String expectedBGColor = "rgba(237, 209, 50, 1)";
            softAssert.assertEquals(backgroundColor, expectedBGColor, "The Background color did not change to yellow.");
            nameInput.click();
            try {
                String ariaInvalidValue = emailInput.getAttribute("aria-invalid");
                softAssert.assertEquals(ariaInvalidValue, "false", testEmail + " is an invalid mail!");
            } catch (NoSuchElementException e) {
                System.out.println("Cannot enter a testmail in the email field.");
            }
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='';", emailInput);
        }

        //Test phone input field
        List<String> phoneTestData = new ArrayList<>();
        phoneTestData.add("d");
        phoneTestData.add("6");
        phoneTestData.add("a21c78an");
        for (String testPhone : phoneTestData) {
            phoneInput.sendKeys(testPhone);
            String backgroundColor = phoneInput.getCssValue("background-color");
            String expectedBGColor = "rgba(237, 209, 50, 1)";
            softAssert.assertEquals(backgroundColor, expectedBGColor, "The Background color did not change to yellow.");
            emailInput.click();
            try {
                String ariaInvalidValue = phoneInput.getAttribute("aria-invalid");
                softAssert.assertEquals(ariaInvalidValue, "false", testPhone + " is an invalid phone number!");
            } catch (NoSuchElementException e) {
                System.out.println("Cannot enter a testphone in the phone field.");
            }
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='';", phoneInput);
        }

        //Message Test
        WebElement messageField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"textarea_comp-jxbsa1f7\"]")));
        messageField.sendKeys("Hello, I need some help.");
        String backgroundColor = messageField.getCssValue("background-color");
        String expectedBGColor = "rgba(237, 209, 50, 1)";
        softAssert.assertEquals(backgroundColor, expectedBGColor, "The Background color did not change to yellow.");
        String ariaInvalidValue = messageField.getAttribute("aria-invaild");
        softAssert.assertEquals(ariaInvalidValue, null, "The message is invalid!");
        softAssert.assertAll();
    }

    @Test
    private void SubmitButton(){
        SwitchToTheContactPage();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0,250)");
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input_comp-jxbsa1e9\"]")));
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"input_comp-jxbsa1em\"]"));
        WebElement phoneInput = driver.findElement(By.xpath("//*[@id=\"input_comp-jxbsa1ev\"]"));
        WebElement messageField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"textarea_comp-jxbsa1f7\"]")));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"comp-jxbsa1fi\"]/button")));
        nameInput.sendKeys("Ana");
        emailInput.sendKeys("ana@gmail");
        phoneInput.sendKeys("7021ga15");
        messageField.sendKeys("Hello, I need some help.");
        submitButton.click();
        WebElement responseMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jxbsa1fv\"]/p/span")));
        softAssert.assertTrue(responseMessage.isDisplayed());
        softAssert.assertAll();
    }

    @Test
    private void ResponseMessageNotDisplayedByDefault(){
        SwitchToTheContactPage();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0,250)");
        WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jxbs915i\"]/div")));
        softAssert.assertTrue(form.isDisplayed(), "Form is not displayed on the page.");
        WebElement responseMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jxbsa1fv\"]/p/span")));
        softAssert.assertFalse(responseMessage.isDisplayed(), "Response message is showed before submitting any message!");
        softAssert.assertAll();
    }
}
