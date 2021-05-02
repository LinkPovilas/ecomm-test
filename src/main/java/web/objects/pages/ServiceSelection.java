package web.objects.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import web.browser.Browser;
import web.objects.references.Service;

import java.util.List;


public class ServiceSelection extends Browser {
    protected final WebDriver driver;

    @FindBys(@FindBy(css = "[class='barbora-btn-txt']"))
    private List<WebElement> services;

    @FindBys(@FindBy(css = "[class='sub-text']"))
    private List<WebElement> descriptions;

    public ServiceSelection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getServices() {
        return services;
    }

    public List<WebElement> getDescriptions() {
        return descriptions;
    }

    public HomePage selectService(Service service) {
        click("//span[text()='" + service.getValue() + "']");
        return new HomePage(driver);
    }
}
