package com.QAcart.base;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.tools.ant.util.FileUtils;

import static org.apache.tools.ant.util.FileUtils.*;

public class Base {

    //constracor will run first
    public Base (){

        PageFactory.initElements( new AppiumFieldDecorator(driver),this);
    }
    protected  static AndroidDriver<MobileElement> driver;
    protected  Properties pro;
    protected  FileInputStream inputStream;
    protected  DesiredCapabilities caps;
    
    public static  ExtentReports extent;
    public static ExtentTest logger;
    @BeforeSuite
    public  void BeforeSuite(){
        extent = new ExtentReports("Reports/index.html");
        extent.addSystemInfo("Framework Type","Appium Page object");
        extent.addSystemInfo("Author","Islam Lotfy");
        extent.addSystemInfo("Enviroment","Android");
        extent.addSystemInfo("App","To Do QACart");
    }

    @AfterSuite
    public  void AfterSuite() {
        extent.flush();
    }

    @BeforeMethod
    public void BeforeMethod(Method method){
        logger = extent.startTest(method.getName());
    }

    @AfterMethod
    public void AfterMethod(Method method, ITestResult result) throws IOException {

        File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.getFileUtils().copyFile(image, new File("snapshot/" + method.getName() + ".jpg"));
        String fullpath = System.getProperty("user.dir") + File.separator + "snapshot\\" + method.getName() + ".jpg";

        System.out.println("fullpath : "+fullpath);
        if (result.getStatus() == ITestResult.SUCCESS)
        {
            logger.log(LogStatus.PASS,"Test is Passed Because there is no Error",result.getName());
            logger.log(LogStatus.PASS,logger.addScreenCapture(fullpath));
        }else if (result.getStatus()==ITestResult.FAILURE)
        {
            logger.log(LogStatus.FAIL,"Test is Fail Because there is Error",result.getName());
            logger.log(LogStatus.FAIL,result.getThrowable());
            logger.log(LogStatus.PASS,logger.addScreenCapture(fullpath));
        }else{
            logger.log(LogStatus.SKIP,"Test is skip");
        }
    }


    @Parameters({"PLATFORM_NAME","DEVICE_NAME","PLATFORM_VERSION"})
    @BeforeClass
    public void setup(String PLATFORM_NAME,String DEVICE_NAME , String PLATFORM_VERSION ) throws IOException {
        File propFile  = new File("src/main/resources/config/config.properties");
        inputStream = new FileInputStream(propFile);
        pro= new Properties();
        pro.load(inputStream);

        if (PLATFORM_NAME.equalsIgnoreCase("Android")){
        File Androidapp = new File(pro.getProperty("AndriodAppPath"));
        caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,pro.getProperty("AUTOMATIONNAME"));
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,PLATFORM_VERSION);
        caps.setCapability(MobileCapabilityType.APP, Androidapp.getAbsolutePath());
        driver = new AndroidDriver<MobileElement>(new URL(pro.getProperty("AppuimServer")), caps);
        }
        else if (PLATFORM_NAME.equalsIgnoreCase("Android"))
        {
            File Iosapp = new File(pro.getProperty("IOSAppPath"));
            caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,pro.getProperty("iosAUTOMATIONNAME"));
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,PLATFORM_VERSION);
            caps.setCapability(MobileCapabilityType.APP, Iosapp.getAbsolutePath());
            driver = new AndroidDriver<MobileElement>(new URL(pro.getProperty("AppuimServer")), caps);

        }
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
