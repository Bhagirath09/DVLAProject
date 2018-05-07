package DVLAProject;

import com.example.fileservices.services.FileServices;
import com.example.fileservices.services.FileServicesImpl;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;

public class TestSuite extends BaseTest {

    private By _startNowButton = By.linkText("Start now");
    private By _vehicleNumberInput = By.id("Vrm");
    private By _continueButton = By.name("Continue");
    private By _actualMake = By.xpath("//div[@id=\"pr3\"]/div/ul/li[2]/span[2]/strong");
    private By _actualColour = By.xpath("//div[@id=\"pr3\"]/div/ul/li[3]/span[2]/strong");

    static String vehicleNo, make, colour;


@Test
public void dvlaVehicleMakeAndColourCheck(){

    try {
        String [] arrExtn = {"xls", "xlsx", "csv"};
        Collection<File> files= FileUtils.listFiles(Paths.get("C:\\Users\\Welcome\\Dropbox\\Software Testing\\EXTRA\\Projects\\DVLAProject\\src\\test\\Resources\\Properties").toFile(),arrExtn, false );
        for (File file: files) {
            if (file != null && file.exists()) {
                FileInputStream input = new FileInputStream(file);
                XSSFWorkbook wb = new XSSFWorkbook(input);

              //if vehicle number column in excel is not empty then driver will enter in this looop
                for (int i = 0; !loadProp.getExcelProperty(wb, 0, i + 1, 0).isEmpty(); i++) {

                    //reading Vehicle No., Make and Colour from excel and saving in String
                    vehicleNo = loadProp.getExcelProperty(wb,0, i + 1, 0);
                    make = loadProp.getExcelProperty(wb,0, i + 1, 1);
                    colour = loadProp.getExcelProperty(wb, 0, i + 1, 2);


                    click(_startNowButton);

                    //Enter vehicle number in input field
                    enterText(_vehicleNumberInput, vehicleNo);

                    click(_continueButton);

                    //get text of actual Make & Color
                    String actualMake = driver.findElement(_actualMake).getText();
                    String actualColour = driver.findElement(_actualColour).getText();

                    String imagepath = "./target/ScreenShots/" + vehicleNo + ".png";

                    captureScreenShot(driver, imagepath);

                    //Assert actual result from website against values taken frim excel
                    softAssert.assertEquals(actualMake, make, "Vehicle No." + vehicleNo + " Make is not matching");
                    softAssert.assertEquals(actualColour, colour, "Vehicle No." + vehicleNo + " Colour is not matching");

                    driver.navigate().to(loadProp.getProperty("url"));

                    softAssert.assertAll();
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

}

}




