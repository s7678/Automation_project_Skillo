package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private final String URL = "http://training.skillo-bg.com:4200/posts/all";

    @FindBy(css = ".far.fa-heart")
    WebElement likeButton;

    @FindBy(css = ".post-feed-img")
    WebElement post;
    @FindBy(css = ".toast-message.ng-star-inserted")
    WebElement toastMsgLikedPost;

    @FindBy(id = "toast-container")
    WebElement toastMsgSuccessLogin;



    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigate() {
        driver.get(URL);
    }

    public void verifyUrl() {
        verifyUrl(URL);

    }

    public void likePostHomePage() {
        clickElement(likeButton);


    }

    public void openLikedPost() {
        clickElement(post);
    }

    public String getToastMsgLikedPost() {
        validateToastMsg(toastMsgLikedPost);
        return toastText;


    }

    public String getToastMsgSuccessLogin() {
        validateToastMsg(toastMsgSuccessLogin);
        return toastText;
    }

    public void waitForPopupToDisappear() {
        mediumWait.until(ExpectedConditions.invisibilityOf(toastMsgSuccessLogin));

    }
}
