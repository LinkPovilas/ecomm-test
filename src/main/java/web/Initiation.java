package web;

public class Initiation extends Driver {

    public void setUp(String baseUrl) {
        if (!isBrowserOpen()) {
            setUpWebDriver();
            goTo(baseUrl);
        }
    }
}
