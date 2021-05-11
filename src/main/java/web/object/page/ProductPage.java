package web.object.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.object.element.HtmlElement;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    protected final WebDriver driver;

    public final By addToCart = By.cssSelector("[class='c-btn c-btn--brand-primary c-btn--block c-btn--center c-btn--modifier']");

    @FindBy(xpath ="//*[name()='use'][contains(@*, '#trash-bin')]")
    public WebElement removeAll;

    @FindBy(xpath = "//span[@class=\"b-cart--item-title\"]/a")
    public WebElement itemInCart;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openProductPage(WebElement element) {
        HtmlElement product = new HtmlElement(element, element.getText());
        product.click();
    }

    public void isDisplayed(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until($ -> $(webElement).isDisplayed());
    }

    public void addToCart() {
        HtmlElement product = new HtmlElement($(addToCart), $(addToCart).getText());
        product.click();
    }
}
