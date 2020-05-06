import java.util.Objects;

public class Product {

    private static String legalName = "[A-Za-zа-яА-Я]+";

    private int rubles;
    private int cents;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return rubles == product.rubles &&
                cents == product.cents &&
                name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rubles, cents, name);
    }

    @Override
    public String toString() {
        return name + " {" +
                "rubles=" + rubles +
                ", cents=" + cents +
                '}';
    }

    public Product(int rubles, int cents, String name) {
        if ((rubles >= 0) && (cents >= 0) && (name.matches(legalName))) {
            this.rubles = rubles;
            this.cents = cents;
            this.name = name;
        }
    }

    public static String getLegalName() {
        return legalName;
    }

    public int getRubles() {
        return rubles;
    }

    public void setRubles(int rubles) {
        this.rubles = rubles;
    }

    public double getCents() {
        return cents;
    }

    public void setCents(int cents) {
        this.cents = cents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}