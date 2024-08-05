//--
import org.openqa.selenium.By;
//--
import org.openqa.selenium.WebDriver;
//--
import org.openqa.selenium.WebElement;
//--
import org.openqa.selenium.chrome.ChromeDriver;
//--
import org.testng.Assert;
//--
import org.testng.annotations.AfterClass;
//--
import org.testng.annotations.BeforeClass;
//--
import org.testng.annotations.Test;

import java.util.Set;

public class HomePage_FooterButtons_TestNG {
    private WebDriver driver;
    String mainWindowHandle;

    @BeforeClass
    public void beforeClass() {
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ancabota09.wixsite.com/intern");
        mainWindowHandle = driver.getWindowHandle();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    private void FacebookLogoButtonTest(){

        driver.get("https://ancabota09.wixsite.com/intern");
        WebElement facebookLink = driver.findElement(By.xpath("//*[@id=\"img_0_i6rlbitx\"]/img"));
        if(facebookLink.isDisplayed()) {
            facebookLink.click();

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
        Assert.assertTrue(actualUrl.contains(expectedLogoUrl), "Facebook logo button did not redirect to the Facebook Page");

        driver.switchTo().window(mainWindowHandle);
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
    private void MailToButtonTest(){

        WebElement mailToButton = driver.findElement(By.xpath("//*[@id=\"i71ww6nk\"]/p[1]/a"));
        if(mailToButton.isDisplayed()){
            mailToButton.click();

          //  String parentWindowHandle = driver.getWindowHandle();

           // Set<String> allWindowHandles = driver.getWindowHandles();
        }
        driver.close();
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
}
