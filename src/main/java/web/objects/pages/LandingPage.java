package web.objects.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import web.browser.Browser;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class LandingPage extends Browser {
    protected final WebDriver driver;

    @FindBy(css = "h1")
    private WebElement header;

    @FindBys(@FindBy(css = "[class='barbora-btn-txt']"))
    private List<WebElement> options;

    @FindBy(css = "[class='back-text']")
    private WebElement backButton;

    @FindBys(@FindBy(css = "[class='sub-text']"))
    private List<WebElement> descriptions;

    private static String pageUrl = "https://www.barbora.lt";

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean checkIfPageIsOpen() {
        return driver.getCurrentUrl().equals(pageUrl);
    }

    public String getPageTitle() {
        return getTitle();
    }

    public String getHeader() {
        return header.getText();
    }

    public List<WebElement> getOptions() {
        return options;
    }

    public List<WebElement> getDescriptions() {
        return descriptions;
    }

    public boolean isBackButtonVisible() {
        return $(backButton).isDisplayed();
    }

    public MainPage selectOption(String option) {
        click("//span[text()='" + option + "']");
        return new MainPage(driver);
    }
}
