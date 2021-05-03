package web.objects.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.browser.Browser;

import static com.codeborne.selenide.Selenide.*;

import java.time.Duration;
import java.util.List;

public class MainPage extends Browser {
    protected final WebDriver driver;

    @FindBy(id = "CybotCookiebotDialogBodyLevelButtonLevelOptinDeclineAll")
    private WebElement onlyNecessary;

    @FindBy(css = "[class=\"b-orderby form-control\"]")
    private WebElement orderByForm;

    @FindBys(@FindBy(css = "[class='c-btn c-btn--brand-primary c-btn--block c-btn--center c-btn--modifier']"))
    private List<WebElement> addToCart;

    @FindBys(@FindBy(xpath = "//div[@class=\"b-product-wrap-img\"]/a"))
    private List<WebElement> products;

    String category;

    String childCategory;

    String childCategoryItem;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isOpen(String url) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        return wait.until(d -> d.getCurrentUrl().equals(url));
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
        Select select = new Select(orderByForm);
        select.selectByValue(option);
    }

    public WebElement findFirstItem(String product) {

        WebElement itemToAdd = null;

        for (WebElement item : products) {
            if (item.getText().contains(product)) {
                itemToAdd = item;
                break;
            }
        }
        return itemToAdd;
    }
}
