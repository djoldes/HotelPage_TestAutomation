//--
import org.openqa.selenium.By;
//--
import org.openqa.selenium.WebDriver;
//--
import org.openqa.selenium.WebElement;
//--
import org.openqa.selenium.chrome.ChromeDriver;
//--
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
//--
import org.testng.annotations.AfterMethod;
//--
import org.testng.annotations.BeforeMethod;
//--
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePage_MenuBar_TestNG {
    private WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ancabota09.wixsite.com/intern");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test
    public void ExploreButtonTest(){
        SoftAssert softAssert = new SoftAssert();
        WebElement exploreButton = driver.findElement(By.id("i6kl732v1"));

        String colorBeforeHover = exploreButton.getCssValue("color");
        Actions actions = new Actions(driver);
        actions.moveToElement(exploreButton).perform();

        try{
            Thread.sleep(5000);
            String colorAfterHover = exploreButton.getCssValue("color");
            softAssert.assertEquals(colorBeforeHover, colorAfterHover, "The color did not change on hover");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        exploreButton.click();

        String expectedExplorePageUrl = "https://ancabota09.wixsite.com/intern/explore";
        String actualUrl = driver.getCurrentUrl();
        softAssert.assertTrue(actualUrl.contains(expectedExplorePageUrl), "Explore button did not redirect to the explore page.");

        softAssert.assertAll();

    }

    @Test
    public void RoomsButtonTest(){
        SoftAssert softAssert = new SoftAssert();
        WebElement roomsLink = driver.findElement(By.id("i6kl732v2label"));

        String colorBeforeHover = roomsLink.getCssValue("color");
        Actions actions = new Actions(driver);
        actions.moveToElement(roomsLink).perform();

        try{
            Thread.sleep(5000);
            String colorAfterHover = roomsLink.getCssValue("color");
            softAssert.assertNotEquals(colorBeforeHover, colorAfterHover, "The color did not change on hover");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        roomsLink.click();

        String expectedRoomsPageUrl = "https://ancabota09.wixsite.com/intern/rooms";
        String actualUrl = driver.getCurrentUrl();
        softAssert.assertTrue(actualUrl.contains(expectedRoomsPageUrl), "Rooms link did not teleport us to the rooms page.");

        softAssert.assertAll();

    }

    @Test
    public void ContactButtonTest() throws InterruptedException {

        WebElement contactLink = driver.findElement(By.id("i6kl732v3"));
        SoftAssert softAssert = new SoftAssert();
        String colorBeforeHover = contactLink.getCssValue("color");
        Actions actions = new Actions(driver);
        actions.moveToElement(contactLink).perform();

        try{
            Thread.sleep(5000);
            String colorAfterHover = contactLink.getCssValue("color");
            softAssert.assertEquals(colorBeforeHover, colorAfterHover, "The color did not change on hover");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        contactLink.click();

        String expectedContactPageUrl = "https://ancabota09.wixsite.com/intern/contact";
        String actualUrl = driver.getCurrentUrl();
        softAssert.assertTrue(actualUrl.contains(expectedContactPageUrl), "Contact link did not teleport us to the contact page.");

        softAssert.assertAll();

    }

    @Test
    public void BookNowButtonTest(){
        SoftAssert softAssert = new SoftAssert();
        WebElement bookNowLink = driver.findElement(By.id("i6tj0u8x"));

        String colorBeforeHover = bookNowLink.getCssValue("color");
        Actions actions = new Actions(driver);
        actions.moveToElement(bookNowLink).perform();

        try{
            Thread.sleep(5000);
            String colorAfterHover = bookNowLink.getCssValue("color");
            softAssert.assertEquals(colorBeforeHover, colorAfterHover, "The color did not change on hover");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        bookNowLink.click();

        String expectedBookNowPageUrl = "https://ancabota09.wixsite.com/intern/booknow";
        String actualUrl = driver.getCurrentUrl();
        softAssert.assertTrue(actualUrl.contains(expectedBookNowPageUrl), "BookNow link did not redirect you to the Book Now page.");

        softAssert.assertAll();

    }

    @Test
    public void HomeButtonTest(){

        WebElement homeLink = driver.findElement(By.id("i6kl732v0"));

        homeLink.click();

        String expectedHomePageUrl = "https://ancabota09.wixsite.com/intern";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedHomePageUrl), "Home button did not redirect to the Home Page");

    }

    @Test
    public void LogoButtonTest(){

        WebElement logoLink = driver.findElement(By.id("i6ksxrtk"));

        logoLink.click();

        String expectedLogoUrl = "https://ancabota09.wixsite.com/intern";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedLogoUrl), "Logo button did not redirect to the Home Page");

    }
}
