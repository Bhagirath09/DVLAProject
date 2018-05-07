Selenium Webdriver - 3.8.1
IntelliJ IDEA 2017.2.1
Language - Java (1.8)
Build - Maven 4
Tools - TestNg 6.10
Windows 10 - 64Bit

This is File Services program, which provides list of
1. All the Files in the set directory.
2. All the files of permitted extension is set directory.

In the resources.properties
1. Set scan.dir to valid directory.
2. Set extn.types to comma separated permitted file types.


In src/main/java there is a DVLAProject directory in which:

	- I have created BasePage where Webdriver is declared. 
	  I have extended this page to all test pages so no need to declare driver again anywhere in framework.

	- I have created BrowserSelector class for browser selection. I am using different version of driver for each web browser.
      I have included all drivers (gecko, chrome & IE) in framework for your ready reference.	
	  Browsers are latest version. Chrome 64, Firefox 57. 
	  
	- Test data is coming from property files located under test/Resources/Properties. 
	  In LoadProp class property file loading configuration is there.

    - All reusable methods are stored in Utils class. 	  

In src/test/java there is a DVLAProject directory in which:
	  
	- There is a BeseTest class in which I have created all object of pages, BeforeMethod to openBrowser 
	  and AfterMethod to close browser and take screenshot if test fails.

    - I have created TestSuite class in which Excersie Part-2 is there. I have created For Loop in which
      test is happening. It is readinig data from Excel file located under test/Resources/Properties.
      Condition is Vehicle Registration no. If it is there in excel file then it will enter into loop. It will fetch
      vehicle's details such as number, make and colour and save it in String to be used for Assert later on.

      Driver will go to DVLA website and it will enter vehicle registration number we got it from excel.
      It will get text of Make and Colour from Web elements and save it in String (actual).
      Then Asserting actual value with value from Excel.


Screenshots will be saved under target/Screenshots
 


 




	  
  


