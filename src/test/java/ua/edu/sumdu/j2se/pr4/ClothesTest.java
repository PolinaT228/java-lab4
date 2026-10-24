package ua.edu.sumdu.j2se.pr4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClothesTest {

    @Test
    void shouldThrowExceptionWhenInvalidValueInSetter() {
        Clothes obj = new Clothes("Куртка", "M", 2500.0, 5);

        assertThrows(IllegalArgumentException.class, () -> {
            obj.setPrice(-100.0);
        });
    }

    @Test
    void shouldThrowExceptionWhenInvalidConstructorData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Clothes("", -10, 0.0, -1);
        });
    }
}