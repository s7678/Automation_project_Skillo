package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class NewPostPage extends BasePage {

    private final String URL = "http://training.skillo-bg.com:4200/posts/create";

    @FindBy(css = ".image-preview")
    WebElement imagePreview;

    @FindBy(css = "input.form-control.input-lg")
    WebElement fileName;

    @FindBy(name = "caption")
    WebElement postCaptionInput;

    @FindBy(id = "create-post")
    WebElement submitButton;

    @FindBy(css = "input.file")
    WebElement fileUploadInput;


    public NewPostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void verifyUrl() {
        verifyUrl(URL);
    }

    public void uploadImage(File file) {
        fileUploadInput.sendKeys(file.getAbsolutePath());

    }

    public void waitImageToBePresented() {
        smallWait.until(ExpectedConditions.visibilityOf(imagePreview));
    }

    public String getImageFileName() {
        smallWait.until(ExpectedConditions.visibilityOf(fileName));
        return fileName.getAttribute("placeholder");
    }

    public void enterPostCaption(String enteredText) {
        enterText(postCaptionInput, enteredText);

    }

    public void clickOnSubmitButton() {
        clickElement(submitButton);
    }
}
