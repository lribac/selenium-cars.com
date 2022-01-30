package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReporterNGAventstack {

    static ExtentReports extent;

    public static ExtentReports getReportObject() {

        String path = System.getProperty("user.dir") + "//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);

        reporter.config().setDocumentTitle("Test Results");  // Title of the report
        reporter.config().setReportName("Smoke Test");
        reporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(reporter);

        // extent.setSystemInfo("OS", OS);
        // extent.setSystemInfo("Browser", prop.getProperty("browser"));
        extent.setSystemInfo("Environment", "Staging1");
        extent.setSystemInfo("Tester", "Katrina & Lucia'");
        return extent;

    }
}
