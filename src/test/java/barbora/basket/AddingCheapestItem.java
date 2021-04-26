package barbora.basket;

import barbora.base.BarboraBase;
import com.google.gson.JsonObject;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import web.Product;

import static com.codeborne.selenide.Selenide.*;
import static util.Config.BARBORA_MAIN;

@DisplayName("Adding cheapest item into cart")
@Description("Test is used to validate log in prompt after adding cheapest product")
@Tags({@Tag("AddToCart"), @Tag("Cheapest")})
public class AddingCheapestItem extends BarboraBase {

    final String picaPath = "saldytas-maistas/saldyti-kulinarijos-ir-konditerijos-gaminiai/saldytos-picos-ir-uzkandziai";

    final String duonaPath = "duonos-gaminiai-ir-konditerija/duona/tamsi-duona";

    final String wokPath = "saldytas-maistas/saldytos-darzoves-grybai-ir-uogos/saldytos-darzoves-ir-ju-misiniai";

    final String productPath = "//div[@class=\"b-product--wrap clearfix b-product--js-hook   \"]";

    final String data = "data-b-for-cart";

    JsonObject obj;

    @DisplayName("Parameterized execution")
    @ParameterizedTest
    @CsvSource({picaPath + ",pica", duonaPath + ",duona", wokPath + ",WOK"})
    public void executeTest(String path, String productName) {
        System.out.println("csv data " + productName);
        navigateToProducts(path);
        getCheapestItem(productName);
        addToCart();
        checkForLogInPrompt();
    }

    public void navigateToProducts(String path) {
        navigateToUrl(BARBORA_MAIN + path);
    }

    @DisplayName("Get cheapest item")
    @Description("Test is used for sorting by price (ascending) and getting specific cheapest product")
    public void getCheapestItem(String productName) {
        // Select product sorting
        $(By.xpath("//select[@class=\"b-orderby form-control\"]")).selectOptionByValue("priceAsc");

        // Get cheapest product data
        Product product = new Product();
        obj = product.getCheapest(productPath, data, productName);
    }

    @DisplayName("Add product into cart")
    @Description("Test is used for selecting the item and adding to cart")
    public void addToCart() {
        // Select product
        click("//div[@data-b-item-id=" + obj.get("id").toString() + "]");
        System.out.println(obj.get("title"));

        // Add to cart
        click("//button[@class=\"c-btn c-btn--brand-primary c-btn--block c-btn--center c-btn--modifier\"]/span");
    }

    @DisplayName("Check if client is asked to log in")
    @Description("Test is used to validate that client is asked to log in")
    public void checkForLogInPrompt() {
        // Check for client log in prompt
        Assertions.assertTrue($(By.xpath("//div[@class=\"modal-content\"]")).isDisplayed());
    }
}
