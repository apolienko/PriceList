import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PriceList {

    private Map<Integer, Product> catalog = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceList priceList1 = (PriceList) o;
        return Objects.equals(catalog, priceList1.catalog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalog);
    }

    @Override
    public String toString() {
        return "priceList = " + '{' + catalog +
                '}';
    }

    public Product getProduct(int code) {

        if (catalog.get(code) == null) {
            throw new NullPointerException("Товар не найден");
        }
        else return catalog.get(code);
    }

    public boolean setProduct(int rubles, int cents, String name, int code) {
        Product product = new Product(rubles, cents, name);
        if (catalog.get(code) == null) {
            catalog.put(code, product);
            return true;
        }
        else return false;
    }

    public boolean changeThePrice(int rubles, int cents, int code) {
        if (catalog.get(code) == null) {
            return false;
        }
        if (rubles >= 0 && cents >= 0 && cents < 100) {
            catalog.get(code).setRubles(rubles);
            catalog.get(code).setCents(cents);
            return true;
        } else {
            return false;
        }
    }

    public boolean changeTheName(String name, int code) throws IllegalArgumentException {
        if (!name.matches(Product.getLegalName())) {
            throw new IllegalArgumentException();
        }
        if (catalog.get(code) == null) {
            return false;
        }
        catalog.get(code).setName(name);
        return true;
    }

    public boolean removeTheProduct(int code) {
        if (catalog.get(code) == null) {
            return false;
        }
        catalog.remove(code);
        return true;
    }

    public String totalPrice(Map<Integer, Integer> input) throws NullPointerException {

        int rubles = 0;
        int cents = 0;
        for (Map.Entry<Integer, Integer> pair : input.entrySet()) {
            if (catalog.get(pair.getKey()) == null ) {
                throw new NullPointerException("Один из товаров не найден");
            }
            int currentCode = pair.getKey();
            int currentNumber = pair.getValue();
            rubles += (catalog.get(currentCode).getRubles() * currentNumber);
            cents += (catalog.get(currentCode).getCents() * currentNumber);
        }
        while (cents > 100) {
            cents -= 100;
            rubles += 1;
        }
        return "Итоговая сумма: " + rubles + '.' + cents;
    }

}