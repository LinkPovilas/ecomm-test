package web.objects.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Item {
    protected final WebDriver driver;

    @FindBy(css = "[class='c-btn c-btn--brand-primary c-btn--block c-btn--center c-btn--modifier']")
    private WebElement addToCartBtn;

    @FindBy(css = "[class='b-product-price-current-number']")
    private WebElement prices;

    public Item(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
