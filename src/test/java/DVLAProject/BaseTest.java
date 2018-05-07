package DVLAProject;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class BaseTest extends Utils {


    protected BrowserSelector browserSelector = new BrowserSelector();
    protected LoadProp loadProp = new LoadProp();
    SoftAssert softAssert = new SoftAssert();

    static String imagePath;

    @BeforeMethod
    public void openBrowserAndEnterURL(){

        //open browser
        browserSelector.openBrowser(loadProp.getProperty("browser"));

        //Enter URL navigate to homepage
        driver.get(loadProp.getProperty("url"));

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

    }

@AfterMethod
    public static void tearDown(ITestResult result)
    {

        // Here will compare if test is failing then only it will enter into if condition
        if(ITestResult.FAILURE==result.getStatus())
        {
            try
            {
                // Create reference of TakesScreenshot
                TakesScreenshot ts=(TakesScreenshot)driver;

                // Call method to capture screenshot
                File source=ts.getScreenshotAs(OutputType.FILE);

                imagePath = "target/ScreenShots/"+result.getName()+".png";

                // Copy files to specific location here it will save all screenshot in our project home directory and
                // result.getName() will return name of test case so that screenshot name will be same
                FileUtils.copyFile(source, new File(imagePath));

                System.out.println("Screenshot taken");

            }
            catch (Exception e)
            {
                System.out.println("Exception while taking screenshot "+e.getMessage());
            }
        }

        // close browser
        driver.quit();
    }
}
