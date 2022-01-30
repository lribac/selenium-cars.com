import configuration.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SearchResultsPage;
import pageObjects.VehicleDetailPage;
import properties.ReadElementPropertyValues;

import java.io.IOException;
import java.net.MalformedURLException;

public class SearchCarTest {

    public WebDriver driver;
    public ReadElementPropertyValues prop = new ReadElementPropertyValues();

    HomePage homePage;
    SearchResultsPage searchResultsPage;
    VehicleDetailPage vehicleDetailPage;

    @BeforeTest
    public void initialize() throws MalformedURLException {
        DriverSetup s=new DriverSetup();
        driver = s.initializeDriver();
        s.hitUrl(prop.getProperty("url"));
    }

    @AfterTest
    public void teardown() {
        driver.close();
        driver = null;
    }

    @Test
    public void searchCar() {
        homePage = new HomePage(driver);
        homePage.selectStockType("Used cars");
        homePage.selectMake("Honda");
        homePage.selectModel("Pilot");
        homePage.selectPrice("$50,000");
        homePage.selectDistance("100 miles");
        homePage.enterZip("60008");
        homePage.clickSearch();
        searchResultsPage = new SearchResultsPage(driver);
        Assert.assertTrue(searchResultsPage.priceFilterIsDisplayed());
        Assert.assertTrue(searchResultsPage.makeFilterIsDisplayed());
        Assert.assertTrue(searchResultsPage.usedFilterIsDisplayed());
        Assert.assertTrue(searchResultsPage.pilotFilterIsDisplayed());
    }

    @Test (dependsOnMethods={"searchCar"})
    public void changeFilters() {
        searchResultsPage.selectStockType("New");
        Assert.assertTrue(searchResultsPage.newFilterIsDisplayed());
        Assert.assertFalse(searchResultsPage.usedFilterIsDisplayed());
        searchResultsPage.selectEightPassengerTouring();
        Assert.assertTrue(searchResultsPage.eightPassengerTouringFilterIsDisplayed());
    }

    @Test (dependsOnMethods={"changeFilters"})
    public void selectCar() {
        searchResultsPage.selectNthCar(2);
        vehicleDetailPage = new VehicleDetailPage(driver);
        Assert.assertTrue(vehicleDetailPage.verifyCarTitle(vehicleDetailPage.carTitle(),
                "Honda Pilot 8-Passenger"));
        Assert.assertTrue(vehicleDetailPage.checkAvailabilityIsDisplayed());
    }

    @Test (dependsOnMethods={"selectCar"})
    public void fillContactForm() {
        vehicleDetailPage.fillForm("Car", "Owner", "carowner@yahoo.com");
    }

    @Test (dependsOnMethods={"fillContactForm"})
    public void screenshotPaymentCalculator() throws IOException {
        vehicleDetailPage.screenshotPaymentCalculator();
    }
}
