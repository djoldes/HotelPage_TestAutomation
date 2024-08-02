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

public class HomePage_MenuBar_TestNG {
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
    public void ExploreButtonTest(){
        beforeClass();
        driver.get("https://ancabota09.wixsite.com/intern");

        WebElement exploreButton = driver.findElement(By.id("i6kl732v1"));

        // Click the button
        exploreButton.click();

        // Verify that the current URL contains the expected path for the explore page
        String expectedExplorePageUrl = "https://ancabota09.wixsite.com/intern/explore";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedExplorePageUrl), "Explore button did not redirect to the explore page.");
        afterClass();
    }

    @Test
    public void RoomsButtonTest(){
        beforeClass();
        driver.get("https://ancabota09.wixsite.com/intern");

        WebElement roomsLink = driver.findElement(By.id("i6kl732v2label"));

        // Click the "Rooms" link
        roomsLink.click();

        // Verify that the current URL contains the expected path for the rooms page
        String expectedRoomsPageUrl = "https://ancabota09.wixsite.com/intern/rooms"; // Adjust the URL as needed
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedRoomsPageUrl), "Rooms link did not teleport us to the rooms page.");
        afterClass();
    }

    @Test
    public void ContactButtonTest(){
        beforeClass();
        driver.get("https://ancabota09.wixsite.com/intern");
        WebElement contactLink = driver.findElement(By.id("i6kl732v3"));

        // Click the "Rooms" link
        contactLink.click();

        // Verify that the current URL contains the expected path for the rooms page
        String expectedContactPageUrl = "https://ancabota09.wixsite.com/intern/contact"; // Adjust the URL as needed
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedContactPageUrl), "Contact link did not teleport us to the contact page.");
        afterClass();
    }

    @Test
    public void BookNowButtonTest(){
        beforeClass();
        driver.get("https://ancabota09.wixsite.com/intern");
        WebElement bookNowLink = driver.findElement(By.id("i6tj0u8x"));

        // Click the "Rooms" link
        bookNowLink.click();

        // Verify that the current URL contains the expected path for the rooms page
        String expectedBookNowPageUrl = "https://ancabota09.wixsite.com/intern/booknow"; // Adjust the URL as needed
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedBookNowPageUrl), "BookNow link did not teleport us to the Book Now page.");
        afterClass();
    }

    @Test
    public void HomeButtonTest(){
        beforeClass();
        driver.get("https://ancabota09.wixsite.com/intern");
        WebElement homeLink = driver.findElement(By.id("i6kl732v0"));

        homeLink.click();

        String expectedHomePageUrl = "https://ancabota09.wixsite.com/intern";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedHomePageUrl), "Home button did not redirect to the Home Page");
        afterClass();
    }

    @Test
    public void LogoButtonTest(){
        beforeClass();
        driver.get("https://ancabota09.wixsite.com/intern");
        WebElement logoLink = driver.findElement(By.id("i6ksxrtk"));

        logoLink.click();

        String expectedLogoUrl = "https://ancabota09.wixsite.com/intern";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedLogoUrl), "Logo button did not redirect to the Home Page");
        afterClass();
    }
}
