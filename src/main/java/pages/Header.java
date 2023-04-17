package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header extends BasePage {
    @FindBy(xpath = "//a[@class=\"nav-link\"]/i")
    WebElement logoutButton;

    @FindBy(id = "nav-link-login")
    WebElement loginButton;

    @FindBy(id = "nav-link-profile")
    WebElement profileLink;

    @FindBy(id = "nav-link-new-post")
    WebElement newPostLink;


    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);


    }

    public void openLogin() {
        clickElement(loginButton);
    }


    public void openProfilePage() {
        clickElement(profileLink);


    }

    public void openNewPostPage() {
        clickElement(newPostLink);
    }

    public void clickLogoutButton() {
        clickElement(logoutButton);
    }
}
