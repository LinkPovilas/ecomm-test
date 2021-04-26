package web;

import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.CustomParsing;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Product {

    final CustomParsing custom = new CustomParsing();

    public JsonObject getCheapest(String xpath, String dataAttribute, String product) {

        List<WebElement> elements = getWebDriver().findElements(By.xpath(xpath));

        List<JsonObject> products = storeWebElements(elements, dataAttribute);

        List<JsonObject> pizzas = getSpecificProductList(products, "title", product);

        int prodIdx = getIdxBySmallestMemberNo(pizzas, "price");

        return pizzas.get(prodIdx);
    }

    public List<JsonObject> storeWebElements(List<WebElement> webElements, String dataAttribute) {

        // List for storing Json objects of web elements
        List<JsonObject> products = new ArrayList<>();

        // Iteration over elements list to get attribute value and parse as JSON object
        for (WebElement element : webElements) {
            products.add(custom.parseAttributeValueAsJson(element, dataAttribute));
        }
        return products;
    }

    public List<JsonObject> getSpecificProductList(List<JsonObject> products, String memberName, String contains) {

        // List for storing Json objects of specific products
        List<JsonObject> jsonList = new ArrayList<>();

        // Iteration over products list to get Json objects based on criteria
        for (JsonObject product : products) {
            if (product.get(memberName).toString().contains(contains)) {
                jsonList.add(product);
            }
        }
        return jsonList;
    }

    public int getIdxBySmallestMemberNo(List<JsonObject> jsonObjectList, String memberName) {

        int productIdx = 0;

        //Setting first element as initial value to compare against
        double small = custom.getMemberValueAsDouble(jsonObjectList, productIdx, memberName);

        //Comparison of items from the list to get the smallest number
        for (int i = 1; i < jsonObjectList.size(); i++) {
            if (custom.getMemberValueAsDouble(jsonObjectList, i, memberName) < small) {
                small = custom.getMemberValueAsDouble(jsonObjectList, i, memberName);
                productIdx = i;
            }
        }
        return productIdx;
    }
}
