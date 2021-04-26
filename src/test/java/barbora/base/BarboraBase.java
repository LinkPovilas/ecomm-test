package barbora.base;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import web.Initiation;

import static util.Config.BARBORA_MAIN;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BarboraBase extends Initiation {

    final String baseUrl = BARBORA_MAIN;

    @BeforeAll
    public void initiate() {
        setUp(baseUrl);
        click("//a[@id=\"CybotCookiebotDialogBodyLevelButtonLevelOptinDeclineAll\"]");
    }
}
