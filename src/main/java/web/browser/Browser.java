package web.browser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.*;
import static com.codeborne.selenide.Selenide.*;
import static util.Config.*;

public class Browser {

    @NotNull
    public static WebDriver openBrowser(String url) {
        setUpWebDriver();
        open(url);
        return getWebDriver();
    }

    public static void setUpWebDriver() {
        if (BUILD_PROFILE_LOCALITY.equals("server")) {
            Configuration.browserBinary = WEB_DRIVER_PATH;
        }
        Configuration.browser = BROWSER_NAME;
        if ("chrome".equals(BROWSER_NAME)) {
            Configuration.headless = HEADLESS;
            // headless browser not supported in Safari.
        } else {
            Configuration.headless = false;
        }
        Configuration.startMaximized = MAXIMIZED;
    }

    public void navigateToUrl(String url) {
        getWebDriver().navigate().to(url);
    }

    public String getTitle() {
        return getWebDriver().getTitle();
    }

    public void addCookieByUrl(String url, String cookie, String value) {
        addCookieByCondition(getWebDriver().getCurrentUrl().equals(url), cookie, value);
    }

    public void addCookieByElementId(String id, String cookie, String value) {
        addCookieByCondition($(By.id(id)).isDisplayed(), cookie, value);
    }

    public void addCookieByCondition(boolean condition, String cookie, String value) {
        if (condition) {
            addCookie(cookie, value);
            refresh();
        }
    }

    public void addCookie(String cookieName, String cookieValue) {
        getWebDriver().manage().addCookie(new Cookie(cookieName, cookieValue));
    }

    public void click(String xpath) {
        $(By.xpath(xpath)).shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.enabled, Duration.ofSeconds(15))
                .click();
    }

    public void close() {
        getWebDriver().close();
    }

    public boolean isBrowserOpen() {
        try {
            getWebDriver().getTitle();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
