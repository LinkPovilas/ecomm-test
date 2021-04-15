package web;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.WebDriverRunner.*;
import static com.codeborne.selenide.Selenide.*;
import static util.Config.*;

public class Driver {

    public void openAndAddCookie(String url) {
        webDriverSetup();
        goTo(url);
        addCookie("CookieConsent", COOKIE_CONSENT);
        refresh();
    }

    public void webDriverSetup() {
        Configuration.browser = BROWSER_NAME;
        Configuration.headless = HEADLESS;
        Configuration.startMaximized = MAXIMIZED;
    }

    public void goTo(String url) {
        open(url);
    }

    public void addCookie(String cookieName, String cookieValue) {
        getWebDriver().manage().addCookie(new Cookie(cookieName, cookieValue));
    }

    public void close() {
        getWebDriver().close();
    }
}