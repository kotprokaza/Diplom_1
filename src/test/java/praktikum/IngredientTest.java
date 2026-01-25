package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTest {
    
    @Test
    public void testIngredientConstructorAndGetters() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Hot Sauce", 50.0f);
        
        assertEquals(IngredientType.SAUCE, ingredient.getType());
        assertEquals("Hot Sauce", ingredient.getName());
        assertEquals(50.0f, ingredient.getPrice(), 0.001);
    }
    
    @Test
    public void testFillingIngredient() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Cutlet", 100.0f);
        
        assertEquals(IngredientType.FILLING, ingredient.getType());
        assertEquals("Cutlet", ingredient.getName());
        assertEquals(100.0f, ingredient.getPrice(), 0.001);
    }
    
    @Test
    public void testIngredientWithZeroPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Free Sauce", 0.0f);
        
        assertEquals("Free Sauce", ingredient.getName());
        assertEquals(0.0f, ingredient.getPrice(), 0.001);
    }
}
