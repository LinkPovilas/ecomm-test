package barbora.basket;

import com.codeborne.selenide.Selenide;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import barbora.base.BarboraBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.CustomParsing;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AddingCheapestPizza extends BarboraBase {

    final String path = "saldytas-maistas/saldyti-kulinarijos-ir-konditerijos-gaminiai/saldytos-picos-ir-uzkandziai";
    final String product = "//div[@class=\"b-product--wrap clearfix b-product--js-hook   \"]";

    @BeforeAll
    public void navigateToProducts() {
        navigateToUrl(getWebDriver().getCurrentUrl() + path);
    }

    @Test
    public void getCheapestPizza() {
        // Select product sorting
        Selenide.$(By.xpath("//select[@class=\"b-orderby form-control\"]")).selectOptionByValue("priceAsc");
        // Get all web elements by specified xpath
        List<WebElement> elements = getWebDriver().findElements(By.xpath(product));
        // Class for convenience
        CustomParsing custom = new CustomParsing();
        // List for storing Json objects of web elements
        List<JsonObject> products = new ArrayList<>();
        // Iteration over elements list to get attribute value and parse as JSON object
        for (WebElement element : elements) {
            products.add(custom.parseAttributeValueAsJson(element, "data-b-for-cart"));
        }
        // List for storing Json objects of specific products
        List<JsonObject> pizzas = new ArrayList<>();
        // Iteration over products list to get Json objects based on criteria
        for (JsonObject product : products) {
            if(product.get("title").toString().contains("pica")) {
                pizzas.add(product);
            }
        }
        //Setting first element as initial value to compare against
        double small = custom.getMemberValueAsDouble(pizzas, 0, "price");
        //Comparison of items from the list to get the smallest number
        for (int i = 1; i < pizzas.size(); i++) {
            if (custom.getMemberValueAsDouble(pizzas, i, "price") < small) {
                small = custom.getMemberValueAsDouble(pizzas, i, "price");
                System.out.println("Cheapest pizza: " + pizzas.get(i).get("title"));
                System.out.println("Lowest price: " + small);
            }
        }

    }
}
