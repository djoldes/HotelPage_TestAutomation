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

public class ExplorePage_TestNG {
    private WebDriver driver;

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

    public void GoToTheExplorePage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement exploreButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("i6kl732v1")));
        exploreButton.click();
    }
    @Test
    private void ExploreTheHotelSection(){
        //go to the explore page
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        GoToTheExplorePage();
        //search Explore the hotel title and check if correct
        WebElement exploreSectionTitle = driver.findElement(By.xpath("//*[@id=\"i6ksjvsy\"]/h2"));
        String eSTToText = exploreSectionTitle.getText();
        softAssert.assertEquals(eSTToText, "EXPLORE THE HOTEL", "Title of the section is not correct.");
        //search the Explore the hotel section and check if correct
        WebElement exploreSectionText = driver.findElement(By.xpath("//*[@id=\"i6kvh3dl\"]/p/span"));
        String eSToText = exploreSectionText.getText();
        softAssert.assertTrue(eSToText.contains("Home And Away Hotel"), "The content about the Hotel is not correct.");
        softAssert.assertAll();
    }

    @Test
    private void AmenitiesAndServicesSection(){
        //go to the explore page
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        GoToTheExplorePage();
        //search the Amenities title and check if correct
        WebElement amenitiesSectionTitle = driver.findElement(By.xpath("//*[@id=\"i6lurq9f\"]/h2"));
        String aSTToText = amenitiesSectionTitle.getText();
        softAssert.assertEquals(aSTToText, "AMENITIES & SERVICES", "Title of the section is not correct.");
        //search the amenities section and check if displayed
        WebElement brushIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//wow-image[@data-image-info[contains(., '9c608a_40c6a63735ab47b096691cfd25e22168.png')]]/img")));
        WebElement cleaningServicesText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='font_8 wixui-rich-text__text']/span[text()='Cleaning Services']")));
        softAssert.assertTrue((brushIcon.isDisplayed()), "Brush icon is not displayed");
        softAssert.assertTrue(cleaningServicesText.isDisplayed(), "CleaningServices text is not displayed");
        WebElement parkingIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//wow-image[@data-image-info[contains(., '9c608a_faef9c2646824b4cb7d694d28e246dae.png')]]/img")));
        WebElement freeParkingText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='font_8 wixui-rich-text__text']/span[text()='Free Parking']")));
        softAssert.assertTrue((parkingIcon.isDisplayed()), "Parking icon is not displayed");
        softAssert.assertTrue(freeParkingText.isDisplayed(), "Parking text is not displayed");
        WebElement lampIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//wow-image[@data-image-info[contains(., '9c608a_3c58fe1f4ad24cac8823dbfb3445a4e6.png')]]/img")));
        WebElement furnishedText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='font_8 wixui-rich-text__text']/span[text()='Fully Furnished']")));
        softAssert.assertTrue((lampIcon.isDisplayed()), "Lamp icon is not displayed");
        softAssert.assertTrue(furnishedText.isDisplayed(), "Furnished text is not displayed");
        WebElement wifiIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//wow-image[@data-image-info[contains(., '9c608a_b504533992514b198819a54f27520449.png')]]/img")));
        WebElement wifiText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='font_8 wixui-rich-text__text']/span[text()='Free WiFi']")));
        softAssert.assertTrue((wifiIcon.isDisplayed()), "WiFi icon is not displayed");
        softAssert.assertTrue(wifiText.isDisplayed(), "WiFi text is not displayed");
        WebElement airplaneIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//wow-image[@data-image-info[contains(., '9c608a_b7451c0859164f889f85d82de735148e.png')]]/img")));
        WebElement airportText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='font_8 wixui-rich-text__text']/span[text()='Airport Transfers']")));
        softAssert.assertTrue((airplaneIcon.isDisplayed()), "Airplane icon is not displayed");
        softAssert.assertTrue(airportText.isDisplayed(), "Airport text is not displayed");
    }

    @Test
    private void ExploreTheCitySection(){
        //go to the explore page
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        GoToTheExplorePage();
        //search the Explore The City title and check if correct
        WebElement exploreTheCityTitle = driver.findElement(By.xpath("//*[@id=\"i6kvcfzq\"]/h2"));
        String eTCTToText = exploreTheCityTitle.getText();
        softAssert.assertEquals(eTCTToText,"EXPLORE THE CITY","Title of the section is not correct.");
        //search each section and test if displayed
        WebElement chinatownSection = driver.findElement(By.xpath("//*[@id=\"i6kv2te2\"]"));
        softAssert.assertTrue(chinatownSection.isDisplayed(), "Chinatown section is not displayed correct.");
        WebElement haightAndAshburySection = driver.findElement(By.xpath("//*[@id=\"i6kvbhma\"]"));
        softAssert.assertTrue(haightAndAshburySection.isDisplayed(),"Haight&Ashbury section is not displayed correct.");
        WebElement goldenGateBridgeSection = driver.findElement(By.xpath("//*[@id=\"i6kvbkvz\"]"));
        softAssert.assertTrue(goldenGateBridgeSection.isDisplayed(),"Golden Gate Bridge section is not displayed correct.");
        softAssert.assertAll();
    }

    @Test
    private void ChinatownSection(){
        //go to the explore page
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        GoToTheExplorePage();
        //search Chinatown image and check if correct
        WebElement chinatownImage = driver.findElement(By.xpath("//*[@id=\"img_i6kv4ak9\"]/img"));
        js.executeScript("arguments[0].scrollIntoView();", chinatownImage);
        String cTIToText = chinatownImage.getAttribute("src").toString();
        softAssert.assertTrue(cTIToText.contains("9c608a_14eb60e42d3a42f29fe67d9b579e26de.jpg"), "Chinatown image is not correct.");
        //search Haight&Ashbury image and check if correct
        WebElement haightAndAshburyImage = driver.findElement(By.xpath("//*[@id=\"img_i6kvbhmc\"]/img"));
        js.executeScript("arguments[0].scrollIntoView();", haightAndAshburyImage);
        String hAAIToText = haightAndAshburyImage.getAttribute("src").toString();
        softAssert.assertTrue(hAAIToText.contains("9c608a_569e962c58334d07a4048e125af8fb82.jpg"), "Haight&Ashbury image is not correct.");
        //search GoldenGateBridge image and check if correct
        WebElement goldenGateBridgeImage = driver.findElement(By.xpath("//*[@id=\"img_i6kvbkw0_0\"]/img"));
        js.executeScript("arguments[0].scrollIntoView();", goldenGateBridgeImage);
        String gGBIToText = goldenGateBridgeImage.getAttribute("src").toString();
        softAssert.assertTrue(gGBIToText.contains("9c608a_66f0495affeb412ba01b0d9f0bd3dd6b.jpg"), "Golden Gate Bridge image is not correct.");

        softAssert.assertAll();
    }
}
