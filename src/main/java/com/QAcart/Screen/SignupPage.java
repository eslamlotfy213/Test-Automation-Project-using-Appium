package com.QAcart.Screen;

import com.QAcart.base.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SignupPage extends Base {

    @iOSXCUITFindBy(xpath = "//android.widget.Button[@text='Sign up']")
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Sign up']")
    private MobileElement SignupButton;



    @iOSXCUITFindBy(xpath = "//android.view.View[@text='Sign in']")
    @AndroidFindBy(xpath = "//android.view.View[@text='Sign in']")
    private MobileElement SigninButton;


    public void ClickOnSignupButton(){
        SignupButton.click();
    }
    public void ClickOnSigninButton(){
        SigninButton.click();
    }







}
