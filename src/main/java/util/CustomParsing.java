package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CustomParsing {

    public JsonObject parseAttributeValueAsJson(WebElement element, String attribute) {
        return JsonParser.parseString(element.getAttribute(attribute)).getAsJsonObject();
    }

    public double getMemberValueAsDouble(List<JsonObject> jsonObjects, int index, String memberName) {
        return Double.parseDouble(jsonObjects.get(index).get(memberName).toString());
    }
}
