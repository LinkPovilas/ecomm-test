package barbora.main;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.CustomAssert;
import web.browser.Browser;
import web.object.element.LoginPrompt;
import web.object.page.MainPage;
import web.object.page.LandingPage;
import web.object.page.ProductPage;

import static web.object.reference.Region.*;
import static web.object.reference.Service.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Landing page validation")
public class CheapestPizzaTest {
    private static WebDriver driver;

    final LandingPage landingPage = new LandingPage(driver);
    final MainPage mainPage = new MainPage(driver);
    final ProductPage productPage = new ProductPage(driver);
    final LoginPrompt loginPrompt = new LoginPrompt(driver);
    final CustomAssert custom = new CustomAssert();
    static String itemName;

    @BeforeAll
    public static void setup() {
        driver = Browser.openBrowser("https://www.barbora.lt");
    }

    @Test
    @Order(1)
    @DisplayName("Navigation to the homepage")
    public void navigateToHomePage() {

        //Arrange
        String region = VILNIAUS.getValue();
        String service = BARBORA.getValue();
        String pageUrl = "https://pagrindinis.barbora.lt/";

        //Act
        landingPage.selectOption(region);
        landingPage.selectOption(service);

        //Assert
        custom.isPageOpen(driver, pageUrl);
    }

    @Test
    @Order(2)
    @DisplayName("Addition of the cheapest pizza to the shopping cart")
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
        custom.assertWebElementIsDisplayed(loginPrompt.loginForm);
    }

    @Test
    @Order(3)
    @DisplayName("Log in with valid credentials")
    public void enterCredentials() {

        //Arrange
        String email = "yourEmail";
        String password = "yourPass";

        //Act
        loginPrompt.loginWithUser(email, password);
        productPage.isDisplayed();

        //Assert
        custom.assertWebElementIsDisplayed(productPage.removeAll);
        custom.assertTextIsEqual(itemName, productPage.itemInCart.getText());
    }
}
