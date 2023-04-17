package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProfilePage extends BasePage{
    public String BASE_URL = "http://training.skillo-bg.com:4200/users/";

    @FindBy(css = "app-profile-section h2")
    WebElement profileUserName;

    @FindBy(css = ".toast-bottom-right.toast-container")
    WebElement toastMessage;

    @FindBy(css = "app-post")
    List<WebElement> existingPosts;


    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void verifyUrl() {
        verifyUrlContains(BASE_URL);

    }

    public String getProfileUserName() {
        return getElementText(profileUserName);

    }

    public String getToastMessageText() {
        validateToastMsg(toastMessage);
        return toastText;

    }



    public int getExistingPostsCount() {
        return existingPosts.size();
    }

    public void openPostByIndex(int index) {
        clickElement(existingPosts.get(index));
    }

}
