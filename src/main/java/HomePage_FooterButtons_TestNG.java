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

    @BeforeClass
    public void beforeClass() {
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ancabota09.wixsite.com/intern");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    private void FacebookLogoButtonTest(){
        beforeClass();

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
        afterClass();
    }

    @Test
    private void XLogoButtonTest(){
        beforeClass();

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
        afterClass();
    }

    @Test
    private void CreatorButtonTest() {
        beforeClass();

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
        afterClass();
    }

    @Test
    private void PinterestLogoButtonTest() {
        beforeClass();

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
        afterClass();
    }

    @Test
    private void MailToButtonTest(){
        beforeClass();

        WebElement mailToButton = driver.findElement(By.xpath("//*[@id=\"i71ww6nk\"]/p[1]/a"));
        if(mailToButton.isDisplayed()){
            mailToButton.click();

            String parentWindowHandle = driver.getWindowHandle();

            Set<String> allWindowHandles = driver.getWindowHandles();


            String subWindowHandle = null;
            for (String handle : allWindowHandles) {
                if (!handle.equals(parentWindowHandle)) {
                    subWindowHandle = handle;
                    break;
                }
            }
            driver.switchTo().window(subWindowHandle);
            driver.switchTo().window(parentWindowHandle);
        }
        afterClass();
    }
}
