package web.objects.pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    protected final WebDriver driver;

    @FindBy(css = "[class='c-btn c-btn--brand-primary c-btn--block c-btn--center c-btn--modifier']")
    private WebElement addToCart;

    @FindBy(xpath ="//*[name()='use'][contains(@*, '#trash-bin')]")
    private WebElement removeAll;

    @FindBy(xpath = "//span[@class=\"b-cart--item-title\"]/a")
    private WebElement itemInCart;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openProductPage(WebElement element) {
        element.click();
    }

    public boolean isDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        return wait.until($ -> $(removeAll).isDisplayed());
    }

    public void addToCart() {
        Selenide.$(By.cssSelector("[class=\"c-btn c-btn--brand-primary c-btn--block c-btn--center c-btn--modifier\"]")).click();
    }

    public WebElement getItemInCart() {
        return itemInCart;
    }
}
