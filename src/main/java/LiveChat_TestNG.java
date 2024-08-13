//--
//--
//import javafx.scene.web.WebEngine;
import org.openqa.selenium.By;
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
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;


public class LiveChat_TestNG {
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

    @Test
    private void LiveChatOpened() throws InterruptedException {
        //open the live chat and switching to the lice chat frame
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
        //send a message and validate that it is displayed in the chat
        WebElement textArea = wait.until(ExpectedConditions.elementToBeClickable(By.className("_3nFws")));
        textArea.sendKeys("hi");
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        softAssert.assertTrue(sendButton.isDisplayed(),"Send button is not displayed.");
        sendButton.click();
        WebElement myMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        softAssert.assertTrue(myMessage.isDisplayed());
        softAssert.assertAll();
    }

    @Test
    private void FormDataTest() throws InterruptedException {
        //open the live chat and switching to the lice chat frame
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
        //send a message and validate that it is displayed in the chat
        WebElement textArea = wait.until(ExpectedConditions.elementToBeClickable(By.className("_3nFws")));
        textArea.sendKeys("hi");
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        softAssert.assertTrue(sendButton.isDisplayed(),"Send button is not displayed.");
        sendButton.click();
        WebElement myMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        softAssert.assertTrue(myMessage.isDisplayed());
        //validate the form is sent as an answer
        WebElement formResponse = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zd18O")));
        softAssert.assertTrue(formResponse.isDisplayed());
        //test name input data
        WebElement nameInput = driver.findElement(By.cssSelector("input[name='name']"));
        List<String> nameTestData = new ArrayList<>();
        nameTestData.add("a");
        nameTestData.add("7");
        nameTestData.add("Ana1234");
        nameTestData.add("An4261a");
        nameTestData.add("An4261a");
        for(String testName : nameTestData){
            nameInput.sendKeys(testName);
            try{
                WebElement validNameCheck = driver.findElement(By.xpath("//*[@id=\"name-valid\"]"));
                softAssert.assertTrue(validNameCheck.isDisplayed());
            }
            catch(NoSuchElementException e){
                WebElement invalidNameCheck = driver.findElement(By.xpath("//*[@id=\"name-error\"]"));
                softAssert.assertTrue(invalidNameCheck.isDisplayed());
                System.out.println(testName + " is an invalid name!");
            }
            nameInput.clear();
        }
        //test email input data
        WebElement emailInput = driver.findElement(By.cssSelector("input[name='email']"));
        List<String> emailTestData = new ArrayList<>();
        emailTestData.add("angdab");
        emailTestData.add("angdab@.com");
        emailTestData.add("angdab@gmail");
        for(String testEmail : emailTestData){
            emailInput.sendKeys(testEmail);
            try{
                WebElement validEmailCheck = driver.findElement(By.xpath("//*[@id=\"email-valid\"]"));
                softAssert.assertTrue(validEmailCheck.isDisplayed());
            } catch (NoSuchElementException e){
                WebElement invalidEmailCheck = driver.findElement(By.xpath("//*[@id=\"email-error\"]"));
                softAssert.assertTrue(invalidEmailCheck.isDisplayed());
                System.out.println(testEmail + " is an invalid email adress!");
            }
            emailInput.clear();
        }
        //test message area
        WebElement messageInput = driver.findElement(By.xpath("//*[@id=\"message\"]"));
        messageInput.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sollicitudin massa quis malesuada consectetur. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Ut arcu justo, dapibus ac urna consequat, cursus feugiat urna. Fusce eu sem efficitur, sagittis enim faucibus, consequat tortor. Etiam tortor enim, mollis ut laoreet vitae, vulputate eu urna. Maecenas tempus enim eget est placerat, sit amet elementum magna rhoncus. Curabitur gravida tellus sed finibus tempor. Nulla at ligula imperdiet diam lobortis pharetra.");
        WebElement validMessageCheck = driver.findElement(By.xpath("//*[@id=\"message-valid\"]"));
        softAssert.assertTrue(validMessageCheck.isDisplayed());
        softAssert.assertAll();
    }

    @Test
    private void SubmitForm(){
        //open the live chat and switching to the lice chat frame
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
        //send a message and validate that it is displayed in the chat
        WebElement textArea = wait.until(ExpectedConditions.elementToBeClickable(By.className("_3nFws")));
        textArea.sendKeys("hi");
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        softAssert.assertTrue(sendButton.isDisplayed(),"Send button is not displayed.");
        sendButton.click();
        WebElement myMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        softAssert.assertTrue(myMessage.isDisplayed());
        //validate the form is sent as an answer
        WebElement formResponse = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zd18O")));
        softAssert.assertTrue(formResponse.isDisplayed());
        //enter valid name
        WebElement nameInput = driver.findElement(By.cssSelector("input[name='name']"));
        nameInput.sendKeys("Andrew");
        //enter valid mail
        WebElement emailInput = driver.findElement(By.cssSelector("input[name='email']"));
        emailInput.sendKeys("andrew.faith2000@gmail.com");
        //enter valid message
        WebElement messageInput = driver.findElement(By.xpath("//*[@id=\"message\"]"));
        messageInput.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sollicitudin massa quis malesuada consectetur. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Ut arcu justo, dapibus ac urna consequat, cursus feugiat urna. Fusce eu sem efficitur, sagittis enim faucibus, consequat tortor. Etiam tortor enim, mollis ut laoreet vitae, vulputate eu urna. Maecenas tempus enim eget est placerat, sit amet elementum magna rhoncus. Curabitur gravida tellus sed finibus tempor. Nulla at ligula imperdiet diam lobortis pharetra.");
        //click on submit button
        WebElement submitButton = driver.findElement(By.className("dUbg3"));
        submitButton.click();
        softAssert.assertFalse(formResponse.isDisplayed());
    }

    @Test
    private void EmojiMessage(){
        //open the live chat and switching to the lice chat frame
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
        //send emoji as a message
        WebElement emojiKeyboardButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[1]")));
        softAssert.assertTrue(emojiKeyboardButton.isDisplayed());
        emojiKeyboardButton.click();
        WebElement emojiButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"emojis-tab-0\"]")));
        emojiButton.click();
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        sendButton.click();
        WebElement myMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        softAssert.assertTrue(myMessage.isDisplayed());
        softAssert.assertAll();
    }

    @Test
    private void SendAttachment(){
        //open the live chat and switching to the lice chat frame
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement liveChatMinimizedFrame = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(liveChatMinimizedFrame);
        WebElement liveChatButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-hook='minimized-chat']")));
        liveChatButton.click();
        driver.switchTo().defaultContent();
        WebElement liveChatFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(liveChatFrame);
        //validate that the button exist
        WebElement attachementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        Assert.assertTrue(attachementButton.isDisplayed());
    }

    @Test
    private void CloseChatButton(){
        //open the live chat and switching to the lice chat frame
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement liveChatMinimizedFrame = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(liveChatMinimizedFrame);
        WebElement liveChatButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-hook='minimized-chat']")));
        liveChatButton.click();
        driver.switchTo().defaultContent();
        WebElement liveChatFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(liveChatFrame);
        //validate and click on the close button
        SoftAssert softAssert = new SoftAssert();
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[1]/div/button")));
        softAssert.assertTrue(closeButton.isDisplayed());
        closeButton.click();
        driver.switchTo().defaultContent();
        softAssert.assertTrue(liveChatMinimizedFrame.isDisplayed());
        softAssert.assertAll();
    }

    /*@Test
    private void FormDataTest() throws InterruptedException {
        //open the live chat and switching to the lice chat frame
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
        //send a message and validate that it is displayed in the chat
        WebElement textArea = wait.until(ExpectedConditions.elementToBeClickable(By.className("_3nFws")));
        textArea.sendKeys("hi");
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        softAssert.assertTrue(sendButton.isDisplayed(),"Send button is not displayed.");
        sendButton.click();
        WebElement myMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        softAssert.assertTrue(myMessage.isDisplayed());
        //validate the form is sent as an answer
        WebElement formResponse = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zd18O")));
        softAssert.assertTrue(formResponse.isDisplayed());
        //test name input data
        WebElement nameInput = driver.findElement(By.cssSelector("input[name='name']"));
        List<String> nameTestData = new ArrayList<>();
        nameTestData.add("a");
        nameTestData.add("7");
        nameTestData.add("Ana1234");
        nameTestData.add("An4261a");
        nameTestData.add("An4261a");
        for(String testName : nameTestData){
            nameInput.sendKeys(testName);
            try{
                WebElement validNameCheck = driver.findElement(By.xpath("//*[@id=\"name-valid\"]"));
                softAssert.assertTrue(validNameCheck.isDisplayed());
            }
            catch(NoSuchElementException e){
                WebElement invalidNameCheck = driver.findElement(By.xpath("//*[@id=\"name-error\"]"));
                softAssert.assertTrue(invalidNameCheck.isDisplayed());
                System.out.println(testName + " is an invalid name!");
            }
            nameInput.clear();
        }
        //test email input data
        WebElement emailInput = driver.findElement(By.cssSelector("input[name='email']"));
        List<String> emailTestData = new ArrayList<>();
        emailTestData.add("angdab");
        emailTestData.add("angdab@.com");
        emailTestData.add("angdab@gmail");
        for(String testEmail : emailTestData){
            emailInput.sendKeys(testEmail);
            try{
                WebElement validEmailCheck = driver.findElement(By.xpath("//*[@id=\"email-valid\"]"));
                softAssert.assertTrue(validEmailCheck.isDisplayed());
            } catch (NoSuchElementException e){
                WebElement invalidEmailCheck = driver.findElement(By.xpath("//*[@id=\"email-error\"]"));
                softAssert.assertTrue(invalidEmailCheck.isDisplayed());
                System.out.println(testEmail + " is an invalid email adress!");
            }
            emailInput.clear();
        }
        //test message area
        WebElement messageInput = driver.findElement(By.xpath("//*[@id=\"message\"]"));
        messageInput.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sollicitudin massa quis malesuada consectetur. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Ut arcu justo, dapibus ac urna consequat, cursus feugiat urna. Fusce eu sem efficitur, sagittis enim faucibus, consequat tortor. Etiam tortor enim, mollis ut laoreet vitae, vulputate eu urna. Maecenas tempus enim eget est placerat, sit amet elementum magna rhoncus. Curabitur gravida tellus sed finibus tempor. Nulla at ligula imperdiet diam lobortis pharetra.");
        WebElement validMessageCheck = driver.findElement(By.xpath("//*[@id=\"message-valid\"]"));
        softAssert.assertTrue(validMessageCheck.isDisplayed());
        softAssert.assertAll();
    }*/

    /*@Test
    private void SubmitForm(){
        //open the live chat and switching to the lice chat frame
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
        //send a message and validate that it is displayed in the chat
        WebElement textArea = wait.until(ExpectedConditions.elementToBeClickable(By.className("_3nFws")));
        textArea.sendKeys("hi");
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        softAssert.assertTrue(sendButton.isDisplayed(),"Send button is not displayed.");
        sendButton.click();
        WebElement myMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        softAssert.assertTrue(myMessage.isDisplayed());
        //validate the form is sent as an answer
        WebElement formResponse = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zd18O")));
        softAssert.assertTrue(formResponse.isDisplayed());
        //enter valid name
        WebElement nameInput = driver.findElement(By.cssSelector("input[name='name']"));
        nameInput.sendKeys("Andrew");
        //enter valid mail
        WebElement emailInput = driver.findElement(By.cssSelector("input[name='email']"));
        emailInput.sendKeys("andrew.faith2000@gmail.com");
        //enter valid message
        WebElement messageInput = driver.findElement(By.xpath("//*[@id=\"message\"]"));
        messageInput.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sollicitudin massa quis malesuada consectetur. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Ut arcu justo, dapibus ac urna consequat, cursus feugiat urna. Fusce eu sem efficitur, sagittis enim faucibus, consequat tortor. Etiam tortor enim, mollis ut laoreet vitae, vulputate eu urna. Maecenas tempus enim eget est placerat, sit amet elementum magna rhoncus. Curabitur gravida tellus sed finibus tempor. Nulla at ligula imperdiet diam lobortis pharetra.");
        //click on submit button
        WebElement submitButton = driver.findElement(By.className("dUbg3"));
        submitButton.click();
        softAssert.assertFalse(formResponse.isDisplayed());
    }

    @Test
    private void EmojiMessage(){
        //open the live chat and switching to the lice chat frame
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
        //send emoji as a message
        WebElement emojiKeyboardButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[1]")));
        softAssert.assertTrue(emojiKeyboardButton.isDisplayed());
        emojiKeyboardButton.click();
        WebElement emojiButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"emojis-tab-0\"]")));
        emojiButton.click();
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        sendButton.click();
        WebElement myMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        softAssert.assertTrue(myMessage.isDisplayed());
        softAssert.assertAll();
    }

    @Test
    private void SendAttachment(){
        //open the live chat and switching to the lice chat frame
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement liveChatMinimizedFrame = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(liveChatMinimizedFrame);
        WebElement liveChatButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-hook='minimized-chat']")));
        liveChatButton.click();
        driver.switchTo().defaultContent();
        WebElement liveChatFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(liveChatFrame);
        //validate that the button exist
        WebElement attachementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        Assert.assertTrue(attachementButton.isDisplayed());
    }*/
}
