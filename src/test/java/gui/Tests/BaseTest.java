package gui.Tests;

import helper.SelBoxApiUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected RemoteWebDriver driver;
    String Node = "https://selenium.testingcloud.internal.rbigroup.cloud/wd/hub";

    @BeforeMethod
    public void setUp(ITestResult result) {
        String gridToken = System.getenv("GRIDTOKEN");

        if (!gridToken.isEmpty()) {
            Reporter.log("Chrome started in SBOX");
            /* The execution happens on the Selenium Grid with the address mentioned earlier */
            try {
                // SBox
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setBrowserName("chrome");
                caps.setCapability("e34:token", gridToken);
                caps.setCapability("e34:l_testName", result.getMethod().getMethodName());

                driver = new RemoteWebDriver(new URL(Node), caps);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else {
            Reporter.log("Chrome started locally");
            System.setProperty("webdriver.chrome.driver", "C:\\Program_Dev\\drivers\\chromedriver\\chromedriver_114.exe");
            driver = new ChromeDriver();
        }

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        Reporter.setCurrentTestResult(result);
        if (result.getStatus() == 2) { //failed scenario
            String methodName = result.getMethod().getMethodName();

            // Get the current timestamp
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = now.format(formatter);

            // Construct the file path with timestamp
            String filePath = System.getProperty("user.dir") + File.separator + "screenshots" +
                    File.separator + timestamp + File.separator + "screen_" + methodName + "_" + ".png";

            File screenshotFile = new File(filePath);

            // Create the necessary parent directories
            boolean directoriesCreated = screenshotFile.getParentFile().mkdirs();

            if (directoriesCreated) {
                System.out.println("Parent directories created successfully.");
            } else {
                System.out.println("Parent directories already exist or could not be created.");
            }

            Reporter.log("This is failed log from reporter.log", true);

            try {
                FileOutputStream screenshotStream = new FileOutputStream(screenshotFile);
                screenshotStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
                screenshotStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Reporter.log(" <a href='" + screenshotFile.getAbsolutePath() + "'> <img src='" + screenshotFile.getAbsolutePath() + "' height='200' width='200'/> </a>  ");
        }

        // Set State on SeleniumBox
        SessionId sessionId = driver.getSessionId();
        System.out.println(
                "after Method called ..."
                        + result.getMethod().getMethodName()
                        + "... session: "
                        + sessionId);

        if (driver != null) {
            driver.quit();
            SelBoxApiUtils.postResult(sessionId, result.isSuccess());
        }
    }


}
