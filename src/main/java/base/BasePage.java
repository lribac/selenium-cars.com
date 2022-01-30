package base;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public static Logger log = LogManager.getLogger(BasePage.class.getName());
    public ArrayList<String> tabs;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 130);
        PageFactory.initElements(driver, this);
    }

    /**
     * Explicit Wait methods
     */
    public void waitForPageTitle(String title) {
        try {
            wait.until(ExpectedConditions.titleContains(title));
        } catch (Exception e) {
            System.out.println("some exception/error occurred while waiting for title " + title.toString());
        }
    }

    public void waitForVisibilityOfElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("some exception/error occurred while waiting for the element " + element.toString());
        }
    }

    public void waitForVisibilityOfElements(List<WebElement> elements) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e) {
            System.out.println("some exception/error occurred while waiting for the element " + elements.toString());
        }
    }


    public void waitForInvisibilityOfElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            System.out.println("some exception/error occurred while waiting for the element " + element.toString());
        }
    }

    public void waitUntilElementIsClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println("some exception/error occurred while waiting for the element " + element.toString());
        }
    }
    public void refreshPage() {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            System.out.println("error occurred while refreshing the page");
        }
    }

    public void openNewWindow(){
        ((JavascriptExecutor) driver).executeScript("window.open()");
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public boolean webElementIsDisplayed(WebElement locator){
        return locator.isDisplayed();
    }

    /**
     * Select methods
     */
    public void ddlByVisibleText(WebElement locator, String value) {
        Select ddl = new Select(locator);
        ddl.selectByVisibleText(value);
    }

    /**
     * Actions methods
     */
    public void aMoveToElement (WebElement locator){
        Actions actions = new Actions(driver);
        actions.moveToElement(locator);
    }
    public void aClick(WebElement locator){
        Actions actions = new Actions(driver);
        actions.moveToElement(locator).click().build().perform();
    }
    public void aSendKeys(WebElement locator,String value){
        Actions actions = new Actions(driver);
        actions.moveToElement(locator).sendKeys(value).build().perform();
    }
    public void aEnterKeys(WebElement locator){
        Actions actions = new Actions(driver);
        actions.moveToElement(locator).sendKeys(Keys.ENTER).build().perform();
    }
    public void aArrowDownKeys(WebElement locator, Integer numberOfTimes){
        Actions actions = new Actions(driver);
        actions.moveToElement(locator).click();
        int i=1;
        while(i<numberOfTimes) {
            actions.sendKeys(Keys.ARROW_DOWN).build().perform();
            i++;
        }
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    public static String getScreenshot(String fileName, WebDriver driver) throws IOException {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String imagePath = ".//screenshots//" + fileName + ".PNG";
        FileUtils.copyFile(src, new File(imagePath));
        return imagePath;
    }
}