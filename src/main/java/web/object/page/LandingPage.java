package web.object.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import web.object.element.HtmlElement;

import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class LandingPage {
    protected final WebDriver driver;

    @FindBy(css = "h1")
    public WebElement header;

    @FindBys(@FindBy(css = "[class='barbora-btn-txt']"))
    public List<WebElement> options;

    @FindBy(css = "[class='back-text']")
    public WebElement backButton;

    @FindBys(@FindBy(css = "[class='sub-text']"))
    public List<WebElement> descriptions;

    final private static String pageUrl = "https://www.barbora.lt";

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void isPageOpen() {
        driver.getCurrentUrl().equals(pageUrl);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void selectOption(String option) {
        HtmlElement btn = new HtmlElement($(byXpath("//button/span[text()='" + option + "']")), option);
        btn.click();
        new MainPage(driver);
    }
}
