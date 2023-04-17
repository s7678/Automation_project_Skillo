package tests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import java.io.File;


public class NewPostTest extends BaseTest {

    @DataProvider(name = "getData")
    public Object[][] getData() {
        return new Object[][]{
                {"sisi_auto1", "1043a3e5", new File("src/main/java/uploads/qa.jpg"), "caption"}
        };
    }


    @Test(dataProvider = "getData")
    public void createNewPostTest(String username, String password, File file, String enteredText) {
        System.out.println("1. Open Home page");
        HomePage homePage = new HomePage(driver);
        homePage.navigate();

        System.out.println("2. Login with existing user");
        Header header = new Header(driver);
        header.openLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyUrl();
        loginPage.login(username, password);


        System.out.println("3. Navigate to Profile page and get how many posts have on the profile page");
        header.openProfilePage();

        ProfilePage openProfilePage = new ProfilePage(driver);
        openProfilePage.verifyUrl();
        int initialExistingPost = openProfilePage.getExistingPostsCount();


        System.out.println("4. Click on New Post button");
        header.openNewPostPage();
        NewPostPage newPostPage = new NewPostPage(driver);
        newPostPage.verifyUrl();


        System.out.println("5. Upload new picture");
        newPostPage.uploadImage(file);


        System.out.println("6. Verify that the image is visible");
        newPostPage.waitImageToBePresented();


        System.out.println("7. Verify that the image is correct");
        Assert.assertEquals(newPostPage.getImageFileName(), file.getName(), "Image is not presented");


        System.out.println("8. Enter post caption");
        newPostPage.enterPostCaption(enteredText);


        System.out.println("9. Click on Submit button to create the post");
        newPostPage.clickOnSubmitButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyUrl();

        System.out.println("10. Verify that appear Post created toast message");
        Assert.assertEquals(profilePage.getToastMessageText(), "Post created!", "The post is NOT Created");

        System.out.println("11. Verify that the posts number has increased");
        int existingPosts = profilePage.getExistingPostsCount();
        Assert.assertEquals(existingPosts, initialExistingPost + 1, "The posts are NOT increased");

        System.out.println("12. Open the last added post");
        profilePage.openPostByIndex(existingPosts - 1);
        PostModal postModal = new PostModal(driver);
        postModal.waitForDialogToAppear();


        System.out.println("13. Verify the post details");
        Assert.assertEquals(postModal.getPostUsername(), username, "Its incorrect the username");

    }

}
