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

public class HomePage_FooterButtons_TestNG {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        //driver = new ChromeDriver();
        //driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    private void FacebookLogoButtonTest(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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
        driver.quit();
    }

    @Test
    private void XLogoButtonTest(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ancabota09.wixsite.com/intern");

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
        driver.quit();
    }

    @Test
    private void CreatorButtonTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ancabota09.wixsite.com/intern");

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
        driver.quit();
    }

    @Test
    private void PinterestLogoButtonTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ancabota09.wixsite.com/intern");

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
        driver.quit();
    }
}
