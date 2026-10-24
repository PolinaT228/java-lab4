package ua.edu.sumdu.j2se.pr4;

import java.util.Objects;

/**
 * Клас виробника одягу (для демонстрації агрегації).
 */
public class Manufacturer {
    private String brandName;
    private String country;

    /**
     * Конструктор виробника з параметрами.
     * @param brandName назва бренду
     * @param country країна-виробник
     */
    public Manufacturer(String brandName, String country) {
        setBrandName(brandName);
        setCountry(country);
    }

    /**Конструктор копіювання для виробника. @інший виробник для копіювання*/
    public Manufacturer(Manufacturer other) {
        if (other != null) {
            this.brandName = other.brandName;
            this.country = other.country;
        }
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        if (brandName == null || brandName.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва бренду не може бути порожньою");
        }
        this.brandName = brandName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Країна не може бути порожньою");
        }
        this.country = country;
    }

    @Override
    public String toString() {
        return "Manufacturer{brandName='" + brandName + "', country='" + country + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return Objects.equals(brandName, that.brandName) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandName, country);
    }
}