package com.QAcart.TestCases;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class Reports {
    public ExtentReports  extent;
    public ExtentTest  logger;
    @BeforeSuite
    public  void BeforeClass(){
        extent = new ExtentReports("Reports/index.html");
        extent.addSystemInfo("Framework Type","Appium Page object");
        extent.addSystemInfo("Author","Islam Lotfy");
        extent.addSystemInfo("Enviroment","Android");
        extent.addSystemInfo("App","GHP QACart");
    }

    @AfterSuite
    public  void AfterClass() {
          extent.flush();
    }

    @BeforeMethod
    public void BeforeMethod(Method method){
        logger = extent.startTest(method.getName());
    }

    @AfterMethod
    public void AfterMethod(Method method, ITestResult result){
           if (result.getStatus() == ITestResult.SUCCESS)
           {
                logger.log(LogStatus.PASS,"Test is Passed");

           }else if (result.getStatus()==ITestResult.FAILURE)
           {
                logger.log(LogStatus.FAIL,"Test is Fail");
                logger.log(LogStatus.FAIL,result.getThrowable());
           }else{
                logger.log(LogStatus.SKIP,"Test is skip");
           }

    }

  /*  @Test
    public void Test1(){
        System.out.println("Test1");
    }

    @Test
    public void Test2(){
        System.out.println("Test2");
        Assert.fail();
    }*/

}
