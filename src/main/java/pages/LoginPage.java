package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BasePage {

    private final String URL = "http://training.skillo-bg.com:4200/users/login";

    @FindBy(css = "p.h4.mb-4")
    WebElement signInHeader;

    @FindBy(id = "defaultLoginFormUsername")
    WebElement usernameField;

    @FindBy(id = "defaultLoginFormPassword")
    WebElement passwordField;

    @FindBy(id = "sign-in-button")
    WebElement signInButton;

    @FindBy(linkText = "Register")
    WebElement registerLink;
    @FindBy(id = "toast-container")
    WebElement toastMsgSuccessLogout;



    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void enterUserName(String username) {
        enterText(usernameField, username);

    }

    public void enterPassword(String password) {
        enterText(passwordField, password);

    }

    public void clickSignInButton() {
        clickElement(signInButton);

    }

    public String getSignInHeaderText() {
        return getElementText(signInHeader);


    }

    public void verifyUrl() {
        verifyUrl(URL);

    }

    public void login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickSignInButton();

    }

    public void openRegisterPage() {
        clickElement(registerLink);
    }

    public void navigate() {
        driver.get(URL);
    }

    public String getToastMsgSuccessLogout() {
        validateToastMsg(toastMsgSuccessLogout);
        return toastText;
    }

}
