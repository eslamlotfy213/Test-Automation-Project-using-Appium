
package com.QAcart.TestCases;

import com.QAcart.Screen.LoginPage;
import com.QAcart.base.Base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class OpenWebapplicationonAndriod{

    LoginPage loginPage;
    @Test
    public void UserCanOpenWebapplicationon() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "sdk");


        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.get("https://google.com");



    }
}
