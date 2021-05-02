package web.objects.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import web.browser.Browser;

import static com.codeborne.selenide.Selenide.*;

import java.time.Duration;

public class HomePage extends Browser {
    protected final WebDriver driver;

    @FindBy(id = "CybotCookiebotDialogBodyLevelButtonLevelOptinDeclineAll")
    private WebElement onlyNecessary;

    @FindBy(css = "[class='b-orderby form-control']")
    private WebElement orderByForm;

    String category;

    String childCategory;

    String childCategoryItem;

    private static String pageUrl = "https://pagrindinis.barbora.lt";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.getCurrentUrl().equals(pageUrl);
        PageFactory.initElements(driver, this);
    }

    public void closeCookieConsent() {
        $(onlyNecessary)
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.enabled, Duration.ofSeconds(15))
                .click();
    }

    public void goToProductPage(String href) {

        String[] path = href.split("/");

        selectProductCategory(path[1]);
        selectProdChildCategory(path[2]);
        selectProdChildCategoryItem(path[3]);
    }

    public void selectProductCategory(String href) {
        category = href;
        click("//a[@href=\"/" + category + "\"]");
    }

    public void selectProdChildCategory(String href) {
        childCategory = category + "/" + href;
        click("//a[@href=\"/" + childCategory + "\"]");
    }

    public void selectProdChildCategoryItem(String href) {
        childCategoryItem = childCategory + "/" + href;
        click("//a[@href=\"/" + childCategoryItem + "\"]");
    }

    public void selectSortingByValue(String option) {
        Select objSelect = new Select(orderByForm);
        objSelect.selectByValue(option);
    }
}
