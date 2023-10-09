
package com.QAcart.TestCases;

import com.QAcart.Screen.LoginPage;
import com.QAcart.base.Base;
import org.testng.annotations.Test;

public class LoginTestCase extends Base {

    LoginPage loginPage;
    @Test
    public void UserCanLoginTest(){
        loginPage = new LoginPage();
        loginPage.FillEmailAndPassword("email3@test.com","test123");
    }


}
