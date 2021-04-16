package barbora.basket;

import org.junit.jupiter.api.Test;
import barbora.base.BarboraBase;

import static com.codeborne.selenide.Selenide.*;

public class AddingCheapestPizza extends BarboraBase {

    @Test
    public void navigateToProducts() {
        sleep(10000);
        close();
    }
}
