package barbora.basket;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import web.browser.Browser;
import web.objects.pages.MainPage;
import web.objects.pages.LandingPage;

import static org.junit.jupiter.api.Assertions.*;
import static web.objects.references.Region.*;
import static web.objects.references.Service.*;

@DisplayName("Region and Service selection")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegionServiceSelectionTest {
    private static WebDriver driver;

    final LandingPage landingPage = new LandingPage(driver);

    @BeforeAll
    public static void setup() {
        driver = Browser.openBrowser("https://www.barbora.lt");
    }

    @Test
    @Order(1)
    @DisplayName("Landing page validation")
    public void navigateToLandingPage() {

        //Arrange
        String titleMessage = "BARBORA internetinė parduotuvė";
        String headerMessage = "Pasirinkite apskritį, kurioje norite apsipirkti:";
        int noOfRegions = 10;

        //Act
        landingPage.checkIfPageIsOpen();

        //Assert
        assertEquals(titleMessage, landingPage.getPageTitle());
        assertEquals(headerMessage, landingPage.getHeader());
        assertEquals(noOfRegions, landingPage.getOptions().size());

        for (WebElement region : landingPage.getOptions()) {
            assertTrue(region.isDisplayed());
        }
    }

    @Test
    @Order(2)
    @DisplayName("Region option selection")
    public void selectRegion() {

        //Arrange
        String barbora = "BARBORA";
        String barboraExpress = "BARBORA EXPRESS";
        String region = VILNIAUS.getValue();
        int noOfDescriptions = 2;

        //Act
        landingPage.selectOption(region);

        //Assert
        assertTrue(landingPage.isBackButtonVisible());
        assertEquals(noOfDescriptions, landingPage.getOptions().size());
        assertTrue(barbora.equalsIgnoreCase(landingPage.getOptions().get(0).getText()));
        assertTrue(barboraExpress.equalsIgnoreCase(landingPage.getOptions().get(1).getText()));
        assertEquals(noOfDescriptions, landingPage.getDescriptions().size());

        for (WebElement service : landingPage.getOptions()) {
            assertTrue(service.isDisplayed());
        }

        for (WebElement description : landingPage.getDescriptions()) {
            assertFalse(description.getText().isEmpty());
        }
    }

    @Test
    @Order(3)
    @DisplayName("Service option selection")
    public void selectService() {

        //Arrange
        String service = BARBORA.getValue();
        String pageUrl = "https://pagrindinis.barbora.lt/";

        //Act
        MainPage mainPage = landingPage.selectOption(service);

        //Assert
        assertTrue(mainPage.isOpen(pageUrl));
    }
}
