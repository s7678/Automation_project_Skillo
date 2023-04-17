package tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.*;

public class RegistrationTest extends BaseTest {


    @Test()
    public void registrationUser() {

        System.out.println("1.Navigate to Login page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.verifyUrl();

        System.out.println("2.Click on Register");
        loginPage.openRegisterPage();


        System.out.println("3.Validate that url has changed");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.verifyUrl();

        System.out.println("4.Validate that the Sign up header is shown");
        String signUpHeaderText = registrationPage.getSignUpHeaderText();
        Assert.assertEquals(signUpHeaderText, "Sign up", "Incorrect Sign Up header text!");


        System.out.println("5.Enter valid credentials");
        registrationPage.generateCredentials();

        System.out.println("6.Click on Sign in button");
        registrationPage.clickSignInButton();

        System.out.println("7.Validate that appear popup message -  Successful register!");
        Assert.assertEquals(registrationPage.getRegistrationFailedToastMessage(), "Successful register!", "Wrong toast message appear!");


    }

}
