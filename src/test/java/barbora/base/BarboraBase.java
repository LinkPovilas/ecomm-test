package barbora.base;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import web.Initiation;
import util.Config;

import static util.Config.BARBORA_MAIN;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BarboraBase extends Initiation {

    final String baseUrl = BARBORA_MAIN;

    //ToDo fix cookie management when going to another page
    @BeforeAll
    public void initiate() {
        setUp(baseUrl);
        addCookieByElementId("CybotCookiebotDialog", "CookieConsent", Config.COOKIE_CONSENT);
    }
}
