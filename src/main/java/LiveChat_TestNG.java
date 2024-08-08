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
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
public class LiveChat_TestNG {
    private WebDriver driver;

    @BeforeMethod
    public void BeforeMethod() {
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ancabota09.wixsite.com/intern");
    }

    @AfterMethod
    public void AfterMethod() {
        driver.quit();
    }

    @Test
    private void LiveChatOpened() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        SoftAssert softAssert = new SoftAssert();
        WebElement liveChatMinimizedFrame = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(liveChatMinimizedFrame);
        WebElement liveChatButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-hook='minimized-chat']")));
        liveChatButton.click();
        driver.switchTo().defaultContent();
        WebElement liveChatFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(liveChatFrame);
        WebElement textArea = wait.until(ExpectedConditions.elementToBeClickable(By.className("_3nFws")));
        textArea.sendKeys("hi");
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        softAssert.assertTrue(sendButton.isDisplayed(),"Send button is not displayed.");
        sendButton.click();
        WebElement myMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        softAssert.assertTrue(myMessage.isDisplayed());
        softAssert.assertAll();
    }
}
