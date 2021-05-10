package web.object.page;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.browser.Browser;
import web.object.element.HtmlElement;

import static com.codeborne.selenide.Selenide.*;

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

    private String category;

    private String childCategory;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isOpen(String url) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        return wait.until(d -> d.getCurrentUrl().equals(url));
    }

    public void closeCookieConsent() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(d -> onlyNecessary.isDisplayed() && onlyNecessary.isEnabled());
        HtmlElement cookieButton = new HtmlElement(onlyNecessary, onlyNecessary.getText());
        cookieButton.click();
    }

    public void goToProductPage(@NotNull String href) {

        String[] path = href.split("/");

        selectProductCategory(path[1]);
        selectProdChildCategory(path[2]);
        selectProdChildCategoryItem(path[3]);
    }

    public void selectProductCategory(String href) {
        category = href;
        WebElement element = $(By.xpath("//a[@href=\"/" + category + "\"]"));
        HtmlElement categoryButton = new HtmlElement(element, element.getText());
        categoryButton.click();
    }

    public void selectProdChildCategory(String href) {
        childCategory = category + "/" + href;
        WebElement element = $(By.xpath("//a[@href=\"/" + childCategory + "\"]"));
        HtmlElement categoryButton = new HtmlElement(element, element.getText());
        categoryButton.click();
    }

    public void selectProdChildCategoryItem(String href) {
        String childCategoryItem = childCategory + "/" + href;
        WebElement element = $(By.xpath("//a[@href=\"/" + childCategoryItem + "\"]"));
        HtmlElement categoryButton = new HtmlElement(element, element.getText());
        categoryButton.click();
    }

    public void selectSortingByValue(String option) {
        HtmlElement ordering = new HtmlElement(orderByForm, orderByForm.getText());
        ordering.selectOption(option);
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
