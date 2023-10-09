package com.QAcart.Screen;

import com.QAcart.base.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Base {

    @iOSXCUITFindBy(xpath = "//android.widget.EditText[@index='1']")
    @AndroidFindBy(xpath = "//android.widget.EditText[@index='1']")
    private MobileElement emailField;

    @iOSXCUITFindBy(xpath = "//android.widget.EditText[@index='2']")
    @AndroidFindBy(xpath = "//android.widget.EditText[@index='2']")
    private MobileElement passwordField;

    @iOSXCUITFindBy(xpath = "//*[@text='Login']")
    @AndroidFindBy(xpath = "//*[@text='Login']")
    private MobileElement loginButton;

    public void FillEmailAndPassword(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
    }



}

