package barbora.basket;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import web.browser.Browser;
import web.objects.elements.LoginPrompt;
import web.objects.pages.MainPage;
import web.objects.pages.LandingPage;
import web.objects.pages.ProductPage;

import static org.junit.jupiter.api.Assertions.*;
import static web.objects.references.Region.*;
import static web.objects.references.Service.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CheapestPizzaTest {
    private static WebDriver driver;

    final LandingPage landingPage = new LandingPage(driver);
    final MainPage mainPage = new MainPage(driver);
    final ProductPage productPage = new ProductPage(driver);
    final LoginPrompt loginPrompt = new LoginPrompt(driver);
    static String itemName;

    @BeforeAll
    public static void setup() {
        driver = Browser.openBrowser("https://www.barbora.lt");
    }

    @Test
    @Order(1)
    public void navigateToHomePage() {

        //Arrange
        String region = VILNIAUS.getValue();
        String service = BARBORA.getValue();
        String pageUrl = "https://pagrindinis.barbora.lt/";

        //Act
        landingPage.selectOption(region);
        landingPage.selectOption(service);

        //Assert
        assertTrue(mainPage.isOpen(pageUrl));
    }

    @Test
    @Order(2)
    public void addCheapestPizza() {

        //Arrange
        final String href = "/saldytas-maistas/saldyti-kulinarijos-ir-konditerijos-gaminiai/saldytos-picos-ir-uzkandziai";
        final String asc = "priceAsc";
        final String product = "pica ";

        //Act
        mainPage.closeCookieConsent();
        mainPage.goToProductPage(href);
        mainPage.selectSortingByValue(asc);
        WebElement item = mainPage.findFirstItem(product);
        itemName = item.getText();
        productPage.openProductPage(item);
        productPage.addToCart();

        //Assert
        assertTrue(loginPrompt.isDisplayed());
    }

    @Test
    @Order(3)
    public void enterCredentials() {

        //Arrange
        String email = "email";
        String password = "pass";

        //Act
        loginPrompt.setEmailField(email);
        loginPrompt.setPasswordFieldField(password);
        loginPrompt.loginWithUser();

        //Assert
        assertTrue(productPage.isDisplayed());
        assertEquals(itemName, productPage.getItemInCart().getText());
    }
}
