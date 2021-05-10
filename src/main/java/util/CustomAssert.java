package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.object.element.HtmlElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomAssert {

    private final Logger LOGGER = LogManager.getLogger(HtmlElement.class.getName());

    public void assertTextIsEqual(String expected, String actual) {
        LOGGER.info("Expecting text '{}'", expected);
        assertTrue(expected.equalsIgnoreCase(actual));
    }

    public void assertNumberOfElementsEquals(String name, int expected, int actual) {
        LOGGER.info("Expecting number of '{}' to be equal to '{}'", name, expected);
        assertEquals(expected, actual);
    }

    public void assertWebElementIsDisplayed(@NotNull WebElement webElement) {
        String altIdentifier = "";

        if (webElement.getAttribute("xlink:href") != null){
            altIdentifier = webElement.getAttribute("xlink:href");
        }

        LOGGER.info("Expecting web element '{}'{} to be displayed", webElement.getText(), altIdentifier);
        assertTrue(webElement.isDisplayed());
    }

    public void assertEveryElementIsDisplayed(@NotNull List<WebElement> webElements) {
        for (WebElement webElement : webElements) {
            assertWebElementIsDisplayed(webElement);
        }
    }

    public void isPageOpen(WebDriver driver, String url) {
        LOGGER.info("Expecting URL '{}' to be open", url);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(d -> d.getCurrentUrl().equals(url));
    }

}
