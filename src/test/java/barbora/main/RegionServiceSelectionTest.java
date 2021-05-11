package barbora.main;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import util.CustomAssert;
import web.browser.Browser;
import web.object.page.LandingPage;

import static web.object.reference.Region.*;
import static web.object.reference.Service.*;

@DisplayName("Region and Service selection")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegionServiceSelectionTest {
    private static WebDriver driver;

    final LandingPage landingPage = new LandingPage(driver);
    final CustomAssert custom = new CustomAssert(driver);

    @BeforeAll
    public static void setup() {
        driver = Browser.openBrowser("https://www.barbora.lt");
    }

    @Test
    @Order(1)
    @DisplayName("Landing page validation")
    public void navigateToLandingPage() {

        String titleMessage = "BARBORA internetinė parduotuvė";
        String headerMessage = "Pasirinkite apskritį, kurioje norite apsipirkti:";
        int noOfRegions = 10;

        landingPage.isPageOpen();

        custom.assertTextIsEqual(titleMessage, landingPage.getPageTitle());
        custom.assertTextIsEqual(headerMessage, landingPage.header.getText());
        custom.assertNumberOfElementsEquals("Regions", noOfRegions, landingPage.options.size());
        custom.assertEveryElementIsDisplayed(landingPage.options);
    }

    @Test
    @Order(2)
    @DisplayName("Region option selection")
    public void selectRegion() {

        String barbora = "BARBORA";
        String barboraExpress = "BARBORA EXPRESS";
        String region = VILNIAUS.getValue();
        int noOfServices = 2;

        landingPage.selectOption(region);

        custom.assertWebElementIsDisplayed(landingPage.backButton);
        custom.assertNumberOfElementsEquals("Services", noOfServices, landingPage.options.size());
        custom.assertTextIsEqual(barbora, landingPage.options.get(0).getText());
        custom.assertTextIsEqual(barboraExpress, landingPage.options.get(1).getText());
        custom.assertEveryElementIsDisplayed(landingPage.options);
    }

    @Test
    @Order(3)
    @DisplayName("Service option selection")
    public void selectService() {

        String service = BARBORA.getValue();
        String pageUrl = "https://pagrindinis.barbora.lt/";

        landingPage.selectOption(service);

        custom.isPageOpen(pageUrl);
    }
}
