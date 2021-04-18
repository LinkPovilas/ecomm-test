package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CustomParsing {

    public JsonObject parseAttributeValueAsJson(WebElement element, String attribute) {
        return JsonParser.parseString(element.getAttribute(attribute)).getAsJsonObject();
    }

    // ToDo fix IndexOutOfBoundsException if item is not found
    public double getMemberValueAsDouble(List<JsonObject> jsonObjects, int index, String memberName) {

        try {
            return Double.parseDouble(jsonObjects.get(index).get(memberName).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.00;
    }
}
