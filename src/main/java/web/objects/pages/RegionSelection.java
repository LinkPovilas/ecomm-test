package web.objects.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import web.browser.Browser;
import web.objects.references.Region;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class RegionSelection extends Browser {
    protected final WebDriver driver;

    @FindBy(css = "h1")
    private WebElement header;

    @FindBys(@FindBy(css = "[class='barbora-btn-txt']"))
    private List<WebElement> regions;

    private static String pageUrl="https://www.barbora.lt";

    public RegionSelection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkIfPageIsOpen() {
        driver.getCurrentUrl().equals(pageUrl);
    }

    public String getPageTitle() {
        return getTitle();
    }

    public String getHeader() {
        return header.getText();
    }

    public List<WebElement> getRegions() {
        return regions;
    }

    public ServiceSelection selectRegion(Region region) {
        click("//span[text()='" + region.getValue() + "']");
        $(By.cssSelector("[class='back-text']")).shouldBe(Condition.visible, Duration.ofSeconds(15000));
        $(By.cssSelector("[class='sub-text']")).shouldBe(Condition.visible, Duration.ofSeconds(15000));
        return new ServiceSelection(driver);
    }
}
