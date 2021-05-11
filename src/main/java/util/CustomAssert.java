package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.*;

public class CustomAssert {
    protected final WebDriver driver;

    private final Logger LOGGER = LogManager.getLogger(CustomAssert.class.getName());

    public CustomAssert(WebDriver driver) {
        this.driver = driver;
    }

    public void assertTextIsEqual(String expected, String actual) {
        LOGGER.info("Expecting text '{}'", expected);
        assertTrue(expected.equalsIgnoreCase(actual));
    }

    public void assertNumberOfElementsEquals(String name, int expected, int actual) {
        LOGGER.info("Expecting number of '{}' to be equal to '{}'", name, expected);
        assertEquals(expected, actual);
    }

    public void assertWebElementIsDisplayed(WebElement webElement) {
        String altIdentifier = "";

        if (webElement.getAttribute("xlink:href") != null){
            altIdentifier = webElement.getAttribute("xlink:href");
        }
        LOGGER.info("Expecting web element '{}{}' to be displayed", webElement.getText(), altIdentifier);
        assertTrue(isDisplayed(webElement));
    }

    public void assertEveryElementIsDisplayed(@NotNull List<WebElement> webElements) {
        for (WebElement webElement : webElements) {
            assertTrue(webElement.isDisplayed());
        }
    }

    public boolean isDisplayed(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        return wait.until($ -> $(webElement).isDisplayed());
    }

    public void isPageOpen(String url) {
        LOGGER.info("Expecting URL '{}' to be open", url);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(d -> d.getCurrentUrl().equals(url));
    }

}
