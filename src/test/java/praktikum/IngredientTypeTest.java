package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTypeTest {
    
    @Test
    public void testIngredientTypeValues() {
        // Проверяем, что enum содержит правильные значения
        IngredientType[] values = IngredientType.values();
        
        assertEquals(2, values.length);
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
    
    @Test
    public void testIngredientTypeToString() {
        // Проверяем toString() для каждого значения
        assertEquals("SAUCE", IngredientType.SAUCE.toString());
        assertEquals("FILLING", IngredientType.FILLING.toString());
    }
}
