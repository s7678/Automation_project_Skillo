package tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.apache.commons.io.FileUtils.cleanDirectory;

public class BaseTest {
    protected WebDriver driver;
    public static final String TEST_RESOURCES_DIR = "src" + File.separator + "main" + File.separator + "resources" + File.separator;
    public static final String SCREENSHOT_DIR = TEST_RESOURCES_DIR.concat("screenshots" + File.separator);
    public static final String REPORT_DIR = TEST_RESOURCES_DIR.concat("reports" + File.separator);

    @BeforeSuite
    public void setupSuite(ITestContext context) throws IOException {
        TestRunner runner = (TestRunner) context;
        runner.setOutputDirectory(REPORT_DIR);
        cleanDirectory(new File(REPORT_DIR));
        cleanDirectory(new File(SCREENSHOT_DIR));
        WebDriverManager.chromedriver().setup();

    }

    @BeforeMethod
    public void setupDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @AfterMethod
    public void tearDown(ITestResult testResult) {
        takeScreenShot(testResult);
        if (driver != null) {
            driver.close();
        }
    }

    private void takeScreenShot(ITestResult testResult) {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
            File screenshot = takeScreenShot.getScreenshotAs(OutputType.FILE);
            String testName = testResult.getName();
            try {
                FileUtils.copyFile(screenshot, new File(SCREENSHOT_DIR.concat(testName).concat(".jpg")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
