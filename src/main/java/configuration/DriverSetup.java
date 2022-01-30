package configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import util.TestUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DriverSetup {

    public WebDriver driver;

    public WebDriver initializeDriver() throws MalformedURLException {

        String browserName = System.getProperty("BROWSER");

        if (System.getProperty("HUB_HOST") != null) {
            String host = "localhost";
            DesiredCapabilities dc = DesiredCapabilities.chrome();
            if (browserName.equalsIgnoreCase("chrome")) {
                dc.acceptInsecureCerts();
                host = System.getProperty("HUB_HOST");
                String url = "http://" + host + ":4444/wd/hub";
                this.driver = new RemoteWebDriver(new URL(url), dc);
            }
            if (browserName.equalsIgnoreCase("firefox")) {
                dc = DesiredCapabilities.firefox();
                host = System.getProperty("HUB_HOST");
                String url = "http://" + host + ":4444/wd/hub";
                this.driver = new RemoteWebDriver(new URL(url), dc);
            }

            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());

        } else {
            if (browserName.contains("chrome")) {

                WebDriverManager.chromedriver().setup();

                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

                this.driver = new ChromeDriver(options);

            } else if (browserName.contains("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setProfile(new FirefoxProfile());
                FirefoxProfile profile = new ProfilesIni().getProfile("default");
                profile.setPreference("network.cookie.cookieBehavior", 2);
                options.setLogLevel(FirefoxDriverLogLevel.FATAL);
                options.setAcceptInsecureCerts(true);

                this.driver = new FirefoxDriver(options);

            } else if (browserName.equals("IE")) {
                WebDriverManager.iedriver().setup();
                this.driver = new InternetExplorerDriver();
            }
        }
            return this.driver;
    }

    public void hitUrl(String url) {
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.get(url);

    }

}


