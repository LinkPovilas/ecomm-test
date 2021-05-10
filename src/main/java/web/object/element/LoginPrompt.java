package web.object.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;

public class LoginPrompt {
    protected final WebDriver driver;

    @FindBy(css = "[class=\"form-horizontal b-login-form\"]")
    public WebElement loginForm;

    @FindBy(id = "b-login-email")
    public WebElement emailField;

    @FindBy(id = "b-login-password")
    public WebElement passwordField;

    @FindBy(css = "[class=\"btn btn-default b-login-form--login-button\"]")
    public WebElement loginButton;

    public LoginPrompt(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        return wait.until($ -> $(loginForm).isDisplayed());
    }

    public void setEmailField(String email) {
        HtmlElement emailInput = new HtmlElement(emailField, emailField.getAttribute("name"));
        emailInput.sendKeys(email);
    }

    public void setPasswordField(String password) {
        HtmlElement passInput = new HtmlElement(passwordField, passwordField.getAttribute("name"));
        passInput.sendKeys(password);
    }

    public void loginWithUser(String email, String password) {
        setEmailField(email);
        setPasswordField(password);
        HtmlElement login = new HtmlElement(loginButton, loginButton.getText());
        login.click();
    }
}
