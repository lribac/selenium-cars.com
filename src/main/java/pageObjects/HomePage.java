package pageObjects;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "make-model-search-stocktype")
    WebElement stocktype;

    @FindBy(id = "makes")
    WebElement make;

    @FindBy(id = "models")
    WebElement model;

    @FindBy(id = "make-model-max-price")
    WebElement price;

    @FindBy(id = "make-model-maximum-distance")
    WebElement distance;

    @FindBy(id = "make-model-zip")
    WebElement zip;

    @FindBy(xpath = "//*[@id='by-make-tab']/div/div[@class='sds-field sds-home-search__submit']")
    WebElement searchButton;


    public void selectStockType(String type){
        ddlByVisibleText(stocktype, type);
        log.info("Stock type selected.");
    }

    public void selectMake(String type){
        ddlByVisibleText(make, type);
        log.info("Make selected.");
    }

    public void selectModel(String type){
        ddlByVisibleText(model, type);
        log.info("Model selected.");
    }

    public void selectPrice(String type){
        ddlByVisibleText(price, type);
        log.info("Price selected.");
    }

    public void selectDistance(String type){
        ddlByVisibleText(distance, type);
        log.info("Distance selected.");
    }

    public void enterZip(String zipCode){
        zip.clear();
        zip.sendKeys(zipCode);
        log.info("Zip code entered.");
    }

    public void clickSearch() {
        aClick(searchButton);
        log.info("Search button clicked.");
    }
}
