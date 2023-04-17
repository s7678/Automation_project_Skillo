package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.Random;

public class RegistrationPage extends BasePage {
    public String URL = "http://training.skillo-bg.com:4200/users/register";
    final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    StringBuilder sb = new StringBuilder();

    Random random = new Random();

    @FindBy(xpath = "//app-register//h4[text()='Sign up']")
    WebElement signUpHeader;

    @FindBy(name = "username")
    WebElement usernameField;

    @FindBy(xpath = "//input[@placeholder=\"email\"]")
    WebElement emailField;

    @FindBy(id = "defaultRegisterFormPassword")
    WebElement passwordField;
    @FindBy(id = "defaultRegisterPhonePassword")
    WebElement confirmPasswordField;

    @FindBy(id = "sign-in-button")
    WebElement signInButton;

    @FindBy(className = "toast-message")
    WebElement toastMessageFailedReg;



    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyUrl() {
        verifyUrl(URL);
    }

    public String getSignUpHeaderText() {
        return getElementText(signUpHeader);
    }


    public void clickSignInButton() {
        clickElement(signInButton);
    }

    public String getRegistrationFailedToastMessage() {
        validateToastMsg(toastMessageFailedReg);
        return toastText;

    }


    public void generateCredentials(){
        for(int i = 0; i < 7; i++) {
            // generate random index number
            int index = random.nextInt(alphabet.length());
            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);
            // append the character to string builder
            sb.append(randomChar);

        }
        String username = sb.toString();
        String password =sb.toString();
        String email=sb.toString()+"@gmail.com";
        enterText(usernameField,username);
        enterText(passwordField,password);
        enterText(emailField,email);
        enterText(confirmPasswordField,password);
    }

}
