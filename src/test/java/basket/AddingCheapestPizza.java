package basket;

import org.junit.jupiter.api.Test;
import web.Driver;

import static com.codeborne.selenide.Selenide.*;

public class AddingCheapestPizza extends Driver {

    @Test
    public void navigateToProducts() {
        openAndAddCookie("https://pagrindinis.barbora.lt");
        sleep(10000);
        close();
        sleep(10000);
    }
}
