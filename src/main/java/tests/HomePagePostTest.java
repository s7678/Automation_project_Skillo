package tests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PostModal;

public class HomePagePostTest extends BaseTest {
    @DataProvider(name = "validCredentials")
    public Object[][] validCredentials() {
        return new Object[][]{
                {"sisi_auto1", "1043a3e5"}
        };

    }

    @Test(dataProvider = "validCredentials")
    public void likePost(String username, String password) {
        System.out.println("1.Navigate to Login page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();

        System.out.println("2. Verify Login URL");
        loginPage.verifyUrl();

        System.out.println("3. Check that appear Sign in Text in login form");
        String signInHeaderText = loginPage.getSignInHeaderText();
        Assert.assertEquals(signInHeaderText, "Sign in", "Incorrect Sign In text appear!");


        System.out.println("4. Login with valid credentials");
        loginPage.login(username, password);

        System.out.println("5.Verify that appear toast message for Successful login");
        HomePage homePage=new HomePage(driver);
        Assert.assertEquals(homePage.getToastMsgSuccessLogin(), "Successful login!", "The toast message doesn't appear!");
        homePage.waitForPopupToDisappear();

        System.out.println("6. Verify Home page URL");
        homePage.verifyUrl();

        System.out.println("7. Click on Like button on first post from the list in Home page");
        homePage.likePostHomePage();

        System.out.println("8. Check if its displayed toast message - Post liked");
        Assert.assertEquals(homePage.getToastMsgLikedPost(), "Post liked", "The toast message doesn't appear!");

        System.out.println("9. Open liked post");
        homePage.openLikedPost();


        System.out.println("10. Verify that the post is mark as liked");
        PostModal postModal = new PostModal(driver);
        postModal.verifyLikedPost();



    }
}
