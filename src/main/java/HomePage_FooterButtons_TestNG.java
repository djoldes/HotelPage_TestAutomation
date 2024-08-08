//--
import org.openqa.selenium.By;
//--
import org.openqa.selenium.WebDriver;
//--
import org.openqa.selenium.WebElement;
//--
import org.openqa.selenium.chrome.ChromeDriver;
//--
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//--
import org.testng.annotations.AfterMethod;
//--
import org.testng.annotations.BeforeMethod;
//--
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.time.LocalDate;

public class HomePage_FooterButtons_TestNG {
    private WebDriver driver;
    String mainWindowHandle;

    @BeforeMethod
    public void beforeMethod() {
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ancabota09.wixsite.com/intern");
        mainWindowHandle = driver.getWindowHandle();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test
    private void FacebookLogoButtonTest() throws InterruptedException {
        WebElement facebookLink = driver.findElement(By.xpath("//*[@id=\"img_0_i6rlbitx\"]/img"));
        if(facebookLink.isDisplayed()) {
            facebookLink.click();
            Thread.sleep(10000);
            String originalWindowHandle = driver.getWindowHandle();

            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(originalWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
        }

        String expectedLogoUrl = "https://www.facebook.com/wix";
        String actualUrl = driver.getCurrentUrl();
        System.out.println(actualUrl);
        Assert.assertTrue(actualUrl.contains(expectedLogoUrl), "Facebook logo button did not redirect to the Facebook Page");

    }

    @Test
    private void XLogoButtonTest(){

        WebElement xLink = driver.findElement(By.xpath("//*[@id=\"i220sc-i6rlbitx\"]/a"));
        if(xLink.isDisplayed()) {
            xLink.click();

            String originalWindowHandle = driver.getWindowHandle();
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(originalWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
        }

        String expectedLogoUrl = "https://x.com/wix";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedLogoUrl), "X logo button did not redirect to the X Page");

    }

    @Test
    private void CreatorButtonTest() {

        WebElement creatorLink = driver.findElement(By.xpath("//*[@id=\"i71wwqnj\"]/p[2]/span/a"));
        if (creatorLink.isDisplayed()) {
            creatorLink.click();

            String originalWindowHandle = driver.getWindowHandle();
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(originalWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
        }

        String expectedLogoUrl = "https://www.wix.com/?utm_campaign=vir_created_with";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedLogoUrl), "Creator logo button did not redirect to the Creator Page");
        driver.switchTo().window(mainWindowHandle);

    }

    @Test
    private void PinterestLogoButtonTest() {

        WebElement pinterestLink = driver.findElement(By.xpath("//*[@id=\"i3175p-i6rlbitx\"]/a"));
        if (pinterestLink.isDisplayed()) {
            pinterestLink.click();

            String originalWindowHandle = driver.getWindowHandle();
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(originalWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
        }

        String expectedLogoUrl = "https://www.pinterest.com/wixcom/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedLogoUrl), "Pinterest logo button did not redirect to the Pinterest Page");

    }

    @Test
    private void MailToButtonTest() throws InterruptedException {

        WebElement mailToButton = driver.findElement(By.xpath("//*[@id=\"i71ww6nk\"]/p[1]/a"));
        String email = mailToButton.getAttribute("href");
        Assert.assertEquals(email, "mailto:info@mysite.com", "Button not contain mailto tag in href tag");


    }

    @Test
    private void AdressTextTest(){

        WebElement adressContent = driver.findElement(By.id("i71wvfxg"));
        Assert.assertEquals(adressContent.getText(), "500 Terry Francois Street\n" + "San Francisco, CA 94158", "Text not correct!");

    }

    @Test
    private void ContactTextTest(){

        WebElement adressContent = driver.findElement(By.id("i71ww6nk"));
        Assert.assertEquals(adressContent.getText(), "info@mysite.com\n" + "Tel: 123-456-7890", "Text not correct!");

    }

    @Test
    private void HomeAndAwaySection(){
        SoftAssert softAssert = new SoftAssert();
        WebElement title = driver.findElement(By.xpath("//*[@id=\"i6ktzy38\"]/p/span"));
        softAssert.assertTrue(title.getText().contains("HOME & AWAY"), "Title is not correct!");
        WebElement copyrights = driver.findElement(By.xpath("//*[@id=\"i71wwqnj\"]/p[1]"));
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        softAssert.assertTrue(copyrights.toString().contains(currentYear+"by HOME & AWAY"), "Coyrights info is not correct!");
        WebElement creatorInfo = driver.findElement(By.xpath("//*[@id=\"i71wwqnj\"]/p[2]"));
        softAssert.assertTrue(creatorInfo.toString().contains("Proudly created with"));
        softAssert.assertAll();
    }

    @Test
    private void PaymentInfo() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"i6lux50r_0\"]/p/span")));
        softAssert.assertTrue(title.getText().contains("WE ACCEPT"), "Title of the Payment Block is not correct");
        WebElement galerryGrid = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"i71uub1o\"]/div[1]")));
        softAssert.assertTrue(galerryGrid.isDisplayed(), "Payment gallery is not displayed.");
        WebElement americanExpressImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"img_undefined\"]/img")));
        softAssert.assertTrue(americanExpressImage.isDisplayed(),"American Express image not displayed.");
        WebElement MasterCardImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"img_undefined\"]/img")));
        softAssert.assertTrue(MasterCardImage.isDisplayed(),"MasterCard image not displayed.");
        WebElement PayPal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"img_undefined\"]/img")));
        softAssert.assertTrue(PayPal.isDisplayed(),"PayPal image not displayed.");
        WebElement Visa = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"img_undefined\"]/img")));
        softAssert.assertTrue(Visa.isDisplayed(),"Visa image not displayed.");
        softAssert.assertAll();
    }
}
