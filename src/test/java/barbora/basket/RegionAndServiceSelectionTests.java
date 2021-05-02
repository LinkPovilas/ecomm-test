package barbora.basket;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import web.browser.Browser;

import web.objects.pages.RegionSelection;
import web.objects.pages.ServiceSelection;

import static web.objects.references.Region.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegionAndServiceSelectionTests extends Browser {
    private static WebDriver driver;

    final RegionSelection regionSelection = new RegionSelection(driver);
    final ServiceSelection serviceSelection = new ServiceSelection(driver);

    @BeforeAll
    public static void beforeAll() {
        driver = openBrowser("https://www.barbora.lt");
    }

    @Test
    @Order(1)
    public void selectRegion() {

        //Arrange
        String titleMessage = "BARBORA internetinė parduotuvė";
        String headerMessage = "Pasirinkite apskritį, kurioje norite apsipirkti:";
        int noOfRegions = 10;

        //Act
        regionSelection.checkIfPageIsOpen();

        //Assert
        Assertions.assertEquals(titleMessage, regionSelection.getPageTitle());
        Assertions.assertEquals(headerMessage, regionSelection.getHeader());
        Assertions.assertEquals(noOfRegions, regionSelection.getRegions().size());

        for (WebElement region : regionSelection.getRegions()) {
            Assertions.assertTrue(region.isDisplayed());
        }
    }

    @Test
    @Order(2)
    public void selectService() {

        //Arrange
        String barbora = "BARBORA";
        String barboraExpress = "BARBORA EXPRESS";
        int noOfDescriptions = 2;

        //Act
        regionSelection.selectRegion(VILNIAUS);

        //Assert
        Assertions.assertEquals(noOfDescriptions, serviceSelection.getServices().size());
        Assertions.assertEquals(barbora, serviceSelection.getServices().get(0).getText());
        Assertions.assertEquals(barboraExpress, serviceSelection.getServices().get(1).getText());
        Assertions.assertEquals(noOfDescriptions, serviceSelection.getDescriptions().size());

        for (WebElement service : serviceSelection.getServices()) {
            Assertions.assertTrue(service.isDisplayed());
        }

        for (WebElement description : serviceSelection.getDescriptions()) {
            Assertions.assertFalse(description.getText().isEmpty());
        }
    }
}
