import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PriceListTest {

    @Test
    void getProduct() {

        PriceList catalog = new PriceList();
        PriceList finalCatalog = catalog;
        assertThrows(NullPointerException.class, () -> catalog.getProduct(22222));

        PriceList catalog1 = new PriceList();
        catalog1.setProduct(200,99, "Хлеб", 23240);
        assertEquals("Хлеб {rubles=200, cents=99}", catalog1.getProduct(23240).toString());

    }

    @Test
    void setProduct() {

        PriceList catalog = new PriceList();
        catalog.setProduct(100,99, "Печенье", 32345);
        assertEquals("Печенье {rubles=100, cents=99}",catalog.getProduct(32345).toString());
        assertEquals(true,catalog.setProduct(200,99, "Хлеб", 23240));
        assertEquals(false,catalog.setProduct(200,99, "Хлеб", 23240));

    }

    @Test
    void changeThePrice() {

        PriceList catalog = new PriceList();
        catalog.setProduct(100,99, "Яблоки", 32345);
        assertEquals(false,catalog.changeThePrice(200,99, 23240));
        assertEquals(true,catalog.changeThePrice(400,66, 32345));

        catalog.setProduct(150,99, "Груши", 123333);
        catalog.changeThePrice(500,45, 123333);
        assertEquals(500,catalog.getProduct(123333).getRubles());
        assertEquals(45,catalog.getProduct(123333).getCents());

    }

    @Test
    void changeTheName() {

        PriceList catalog = new PriceList();
        catalog.setProduct(100,99, "Яблоки", 32345);
        assertThrows(IllegalArgumentException.class, () -> catalog.changeTheName("2db34teg", 32345));

        PriceList catalog1 = new PriceList();
        catalog1.setProduct(100, 50, "Брусника", 9090);
        assertEquals(false, catalog1.changeTheName("Вишня", 123123));
        assertEquals(true, catalog1.changeTheName("Черника", 9090));

    }

    @Test
    void removeTheProduct() {

        PriceList catalog = new PriceList();
        catalog.setProduct(50, 69, "Молоко", 32345);
        catalog.removeTheProduct(32345);
        assertThrows(NullPointerException.class, () -> catalog.getProduct(32345));

        PriceList catalog1 = new PriceList();
        assertEquals(false, catalog1.removeTheProduct(11111));

    }

    @Test
    void totalPrice() {

        PriceList catalog = new PriceList();
        Map test = new HashMap();
        test.put(32345, 3);
        test.put(45678, 2);
        test.put(90875, 4);
        catalog.setProduct(50, 69, "Молоко", 32345);
        catalog.setProduct(60, 99, "Яйца", 45678);
        catalog.setProduct(29, 89, "Хлеб", 90875);
        assertEquals("Итоговая сумма: 393.61", catalog.totalPrice(test));

        PriceList catalog1 = new PriceList();
        Map test1 = new HashMap();
        test1.put(32345, 3);
        catalog1.setProduct(60, 99, "Яйца", 45678);
        assertThrows(NullPointerException.class, () -> catalog1.totalPrice(test1));

    }
}