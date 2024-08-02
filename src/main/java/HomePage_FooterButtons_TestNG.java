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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    private void FacebookLogoButtonTest(){
        driver.get("https://ancabota09.wixsite.com/intern");

        WebElement facebookLink = driver.findElement(By.xpath("//*[@id=\"img_0_i6rlbitx\"]/img"));
        facebookLink.click();

        String expectedLogoUrl = "https://www.facebook.com/wix";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedLogoUrl), "Facebook logo button did not redirect to the Facebook Page");
    }
}
