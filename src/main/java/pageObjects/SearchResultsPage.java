package pageObjects;

import base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//label[@for='active_filter_tags-list_price_range--50000']")
    WebElement priceFilter;

    @FindBy(xpath = "//label[@for='active_filter_tags-makes-honda']")
    WebElement makeFilter;

    @FindBy(xpath = "//label[@for='active_filter_tags-stock_type-used']")
    WebElement usedFilter;

    @FindBy(xpath = "//label[@for='active_filter_tags-stock_type-new']")
    WebElement newFilter;

    @FindBy(xpath = "//label[@for='active_filter_tags-models-honda-pilot']")
    WebElement pilotFilter;

    @FindBy(xpath = "//label[@for='active_filter_tags-trims-honda-pilot-touring_8_passenger']")
    WebElement eightPassengerTouringFilter;

    @FindBy(id ="stock-type-select")
    WebElement stockType;

    @FindBy(xpath = "//label[@for='trim_honda-pilot-touring_8_passenger']")
    WebElement eightPassengerTouring;

    @FindBy(xpath = "//h2[@class='title']")
    List<WebElement> cars;

    public boolean priceFilterIsDisplayed (){
        waitForVisibilityOfElement(stockType);
        try {
            return webElementIsDisplayed(priceFilter);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean makeFilterIsDisplayed (){
        try {
            return webElementIsDisplayed(makeFilter);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean usedFilterIsDisplayed (){
        try {
            return webElementIsDisplayed(usedFilter);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean pilotFilterIsDisplayed (){
        try {
            return webElementIsDisplayed(pilotFilter);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean newFilterIsDisplayed (){
        waitForVisibilityOfElement(newFilter);
        try {
            return webElementIsDisplayed(newFilter);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean eightPassengerTouringFilterIsDisplayed (){
        waitForVisibilityOfElement(eightPassengerTouringFilter);
        try {
            return webElementIsDisplayed(eightPassengerTouringFilter);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void selectStockType(String type){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", stockType);
        ddlByVisibleText(stockType, type);
        log.info("Stock type selected.");
    }

    public void selectEightPassengerTouring (){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", eightPassengerTouring);
        aClick(eightPassengerTouring);
        log.info("Eight Passenger touring filter selected.");
    }

    public void selectNthCar(Integer carNumber){
        for(int i = 0; i < cars.size(); i++){
            if(i == carNumber){
                aClick(cars.get(i));
                log.info("Car nr" + i + " selected.");
                break;
            }
        }
    }
}
