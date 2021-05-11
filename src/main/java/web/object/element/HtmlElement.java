package web.object.element;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class HtmlElement {

    private final Logger LOGGER = LogManager.getLogger(HtmlElement.class.getName());

    private final WebElement element;
    private final String name;

    public HtmlElement(WebElement element, String name) {
        this.element = element;
        this.name = name;
    }

    public void click() {
        LOGGER.info("Clicking '{}'", name);
        $(element).shouldBe(Condition.visible).click();
    }

    public void selectOption(String option) {
        Select select = new Select(element);
        select.selectByValue(option);
        LOGGER.info("Selecting '{}'", select.getFirstSelectedOption().getText());
    }

    public void submit() {
        element.submit();
    }

    public void sendKeys(CharSequence... charSequences) {
        LOGGER.info("Entering details into '{}' field", name);
        element.sendKeys(charSequences);
    }

    public void clear() {
        LOGGER.info("Clearing " + name);
        element.clear();
    }

    public String getTagName() {
        return element.getTagName();
    }

    public String getAttribute(String s) {
        return element.getAttribute(s);
    }

    public boolean isSelected() {
        return element.isSelected();
    }

    public boolean isEnabled() {
        return element.isEnabled();
    }

    public String getText() {
        return element.getText();
    }

    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    public WebElement findElement(By by) {
        LOGGER.info("find element " + name);
        return element.findElement(by);
    }

    public boolean isDisplayed() {
        LOGGER.info("Checking if '{}' is displayed", name);
        return element.isDisplayed();
    }

    public Point getLocation() {
        return element.getLocation();
    }

    public Dimension getSize() {
        return element.getSize();
    }

    public Rectangle getRect() {
        return element.getRect();
    }

    public String getCssValue(String s) {
        return element.getCssValue(s);
    }

    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return element.getScreenshotAs(outputType);
    }
}
