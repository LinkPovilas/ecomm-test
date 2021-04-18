package barbora.basket;

import barbora.base.BarboraBase;
import com.google.gson.JsonObject;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import web.Product;

import static com.codeborne.selenide.Selenide.*;
import static util.Config.BARBORA_MAIN;

@DisplayName("Adding cheapest pizza into cart")
@Description("Test is used to validate log in prompt after adding cheapest product")
@Tags({@Tag("AddToCart"), @Tag("Cheapest")})
public class AddingCheapestPizza extends BarboraBase {

    final String path = "saldytas-maistas/saldyti-kulinarijos-ir-konditerijos-gaminiai/saldytos-picos-ir-uzkandziai";

    final String productPath = "//div[@class=\"b-product--wrap clearfix b-product--js-hook   \"]";

    final String data = "data-b-for-cart";

    final String productName = "pica";

    JsonObject obj;

    @BeforeAll
    public void navigateToProducts() {
        navigateToUrl(BARBORA_MAIN + path);
    }

    @Test
    @Order(1)
    @DisplayName("Get cheapest pizza")
    @Description("Test is used for sorting by price (ascending) and getting specific cheapest product")
    public void getCheapestPizza() {
        // Select product sorting
        $(By.xpath("//select[@class=\"b-orderby form-control\"]")).selectOptionByValue("priceAsc");

        // Get cheapest product data
        Product product = new Product();
        obj = product.getCheapest(productPath, data, productName);
    }

    @Test
    @Order(2)
    @DisplayName("Add product into cart")
    @Description("Test is used for selecting the item and adding to cart")
    public void addToCart() {
        // Select product
        click("//div[@data-b-item-id=" + obj.get("id").toString() + "]");

        // Add to cart
        click("//button[@class=\"c-btn c-btn--brand-primary c-btn--block c-btn--center c-btn--modifier\"]/span");
    }

    @Test
    @Order(3)
    @DisplayName("Check if client is asked to log in")
    @Description("Test is used to validate that client is asked to log in")
    public void checkForLogInPrompt() {
        // Check for client log in prompt
        Assertions.assertTrue($(By.xpath("//div[@class=\"modal-content\"]")).isDisplayed());
    }
}
