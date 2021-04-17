package web;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.*;
import static com.codeborne.selenide.Selenide.*;
import static util.Config.*;

public class Driver {

    public void setUpWebDriver() {
        if (BUILD_PROFILE_LOCALITY.equals("server")) {
            Configuration.browserBinary = WEB_DRIVER_PATH;
        }
        Configuration.browser = BROWSER_NAME;
        Configuration.headless = HEADLESS;
        Configuration.startMaximized = MAXIMIZED;
    }

    public void goTo(String url) {
        open(url);
    }

    public void navigateToUrl(String url) {
        getWebDriver().navigate().to(url);
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
        $(By.xpath(xpath)).shouldBe(visible).click();
    }

    public void acceptAlert() {
        getWebDriver().switchTo().alert().accept();
    }

    public void close() {
        getWebDriver().close();
    }

    public boolean isBrowserOpen() {
        try {
            getWebDriver().getTitle();
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
}
