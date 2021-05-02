package barbora.basket;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import web.Product;
import web.browser.Browser;
import web.objects.pages.HomePage;
import web.objects.pages.RegionSelection;
import web.objects.pages.ServiceSelection;

import static com.codeborne.selenide.Selenide.$;
import static web.objects.references.Region.*;
import static web.objects.references.Service.*;

public class CheapestPizzaTest extends Browser {
    private static WebDriver driver;

    final RegionSelection regionSelection = new RegionSelection(driver);
    final ServiceSelection serviceSelection = regionSelection.selectRegion(VILNIAUS);
    final HomePage homePage = serviceSelection.selectService(BARBORA);
    final Product product = new Product();

    @BeforeAll
    public static void beforeAll() {
        driver = openBrowser("https://www.barbora.lt");
    }

    @Test
    public void addCheapestPizza() {

        //Arrange
        final String href = "/saldytas-maistas/saldyti-kulinarijos-ir-konditerijos-gaminiai/saldytos-picos-ir-uzkandziai";
        final String asc = "priceAsc";
        final String productPath = "//div[@class=\"b-product--wrap clearfix b-product--js-hook   \"]";
        final String data = "data-b-for-cart";
        final String productName = "pica";
        JsonObject obj;

        //Act
        homePage.closeCookieConsent();
        homePage.goToProductPage(href);
        homePage.selectSortingByValue(asc);
        /** --- Work In Progress --- */
        //ToDO Need to finish test case
        obj = product.getCheapest(productPath, data, productName);

        // Select product
        click("//div[@data-b-item-id=" + obj.get("id").toString() + "]");

        // Add to cart
        click("//button[@class=\"c-btn c-btn--brand-primary c-btn--block c-btn--center c-btn--modifier\"]/span");

        //Assert
        Assertions.assertTrue($(By.xpath("//div[@class=\"modal-content\"]")).isDisplayed());
    }
}
