package pageObjects;

import base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.Arrays;

public class VehicleDetailPage extends BasePage {

    public VehicleDetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='title-section']//h1[@class='listing-title']")
    WebElement carTitle;

    @FindBy(xpath = "//form[@id='fields-lead-form-embedded']//section//div//div[2]//button")
    WebElement checkAvailabilityButton;

    @FindBy(id = "first_name")
    WebElement firstNameField;

    @FindBy(id = "last_name")
    WebElement lastNameField;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "lengthOfLoan")
    WebElement paymentCalculator;

    public String carTitle(){
        return carTitle.getText();
    }

    public boolean verifyCarTitle(String actualTitle, String expectedTitle){
        String[] actualCarTitleWords = actualTitle.split("\\s+");
        String[] expectedCarTitleWords = expectedTitle.split("\\s+");

        boolean result = false;

        for(int i = 0; i < expectedCarTitleWords.length; i++){
            if(Arrays.stream(actualCarTitleWords).anyMatch(expectedCarTitleWords[i]::equals)){
                 result = true;
            }else{
                 result = false;
                 break;
            }
        }
        return result;
    }

    public boolean checkAvailabilityIsDisplayed(){
        try {
            return webElementIsDisplayed(checkAvailabilityButton);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void fillForm(String firstName, String lastName, String email){
        waitForVisibilityOfElement(firstNameField);
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        log.info("Form filled.");
    }

    public void screenshotPaymentCalculator() throws IOException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", paymentCalculator);
        getScreenshot("paymentCalculator", driver);
        log.info("Payment calculator screenshot taken.");
    }
}
