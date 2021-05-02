package web;

import web.browser.Browser;

public class Initiation extends Browser {

    public void setUp(String baseUrl) {
        if (!isBrowserOpen()) {
            setUpWebDriver();
            openBrowser(baseUrl);
        }
    }
}
