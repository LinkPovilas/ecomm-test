package barbora.base;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import web.Initiation;
import util.Config;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BarboraBase extends Initiation {

    String baseUrl = "https://barbora.lt/";

    @BeforeAll
    public void initiate() {
        setUp(baseUrl);
        click("//button[@data-county=\"vilnius\"]");
        click("//span[@class='barbora-btn-txt'][contains(text(),'Barbora')]");
        addCookieByElementId("CybotCookiebotDialog", "CookieConsent", Config.COOKIE_CONSENT);
    }
}
