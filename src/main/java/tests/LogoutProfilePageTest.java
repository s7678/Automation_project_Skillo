package tests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;


public class LogoutProfilePageTest extends BaseTest {
    @DataProvider(name = "validCredentials")
    public Object[][] validCredentials() {
        return new Object[][]{
                {"sisi_auto1", "1043a3e5"}
        };
    }

    @Test(dataProvider = "validCredentials")
    public void logoutProfilePage(String username, String password) {
        System.out.println("1.Navigate to Login page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();

        System.out.println("2. Verify Login URL");
        loginPage.verifyUrl();

        System.out.println("4. Login with valid credentials");
        loginPage.login(username, password);

        System.out.println("5. Verify Home page URL");
        HomePage homePage = new HomePage(driver);
        homePage.verifyUrl();
        homePage.waitForPopupToDisappear();

        System.out.println("6.Open profile page");
        Header header = new Header(driver);
        header.openProfilePage();

        System.out.println("7. Check profile username");
        ProfilePage profilePage = new ProfilePage(driver);
        String profileUsername = profilePage.getProfileUserName();
        Assert.assertEquals(profileUsername, username, "Its open wrong user Profile page");

        System.out.println("8. Click on Logout button");
        header.clickLogoutButton();

        System.out.println("9. Verify that is open Login page / check URL");
        loginPage.verifyUrl();

        System.out.println("10. Verify that is displayed toast message Successful logout");
        Assert.assertEquals(loginPage.getToastMsgSuccessLogout(), "Successful logout!", "The toast message doesn't appear!");


    }
}
