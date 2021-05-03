package web.objects.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;

public class LoginPrompt {
    protected final WebDriver driver;

    @FindBy(css = "[class=\"form-horizontal b-login-form\"]")
    private WebElement loginForm;

    @FindBy(id = "b-login-email")
    private WebElement emailField;

    @FindBy(id = "b-login-password")
    private WebElement passwordField;

    @FindBy(css = "[class=\"btn btn-default b-login-form--login-button\"]")
    private WebElement loginButton;

    public LoginPrompt(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        return wait.until($ -> $(loginForm).isDisplayed());
    }

    public void setEmailField(String email) {
        emailField.sendKeys(email);
    }

    public void setPasswordFieldField(String password) {
        passwordField.sendKeys(password);
    }

    public void loginWithUser() {
        loginButton.click();
    }
}
