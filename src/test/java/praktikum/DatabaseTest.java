package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class DatabaseTest {
    
    @Test
    public void testDatabaseConstructor() {
        Database database = new Database();
        
        // Проверяем, что база данных инициализирована
        assertNotNull(database);
    }
    
    @Test
    public void testAvailableBuns() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();
        
        assertNotNull(buns);
        assertEquals(3, buns.size());
        
        // Проверяем названия булок
        assertEquals("black bun", buns.get(0).getName());
        assertEquals("white bun", buns.get(1).getName());
        assertEquals("red bun", buns.get(2).getName());
        
        // Проверяем цены
        assertEquals(100.0f, buns.get(0).getPrice(), 0.001);
        assertEquals(200.0f, buns.get(1).getPrice(), 0.001);
        assertEquals(300.0f, buns.get(2).getPrice(), 0.001);
    }
    
    @Test
    public void testAvailableIngredients() {
        Database database = new Database();
        List<Ingredient> ingredients = database.availableIngredients();
        
        assertNotNull(ingredients);
        assertEquals(6, ingredients.size());
        
        // Проверяем первые 3 соуса
        assertEquals(IngredientType.SAUCE, ingredients.get(0).getType());
        assertEquals("hot sauce", ingredients.get(0).getName());
        assertEquals(100.0f, ingredients.get(0).getPrice(), 0.001);
        
        assertEquals(IngredientType.SAUCE, ingredients.get(1).getType());
        assertEquals("sour cream", ingredients.get(1).getName());
        assertEquals(200.0f, ingredients.get(1).getPrice(), 0.001);
        
        assertEquals(IngredientType.SAUCE, ingredients.get(2).getType());
        assertEquals("chili sauce", ingredients.get(2).getName());
        assertEquals(300.0f, ingredients.get(2).getPrice(), 0.001);
        
        // Проверяем 3 начинки
        assertEquals(IngredientType.FILLING, ingredients.get(3).getType());
        assertEquals("cutlet", ingredients.get(3).getName());
        assertEquals(100.0f, ingredients.get(3).getPrice(), 0.001);
        
        assertEquals(IngredientType.FILLING, ingredients.get(4).getType());
        assertEquals("dinosaur", ingredients.get(4).getName());
        assertEquals(200.0f, ingredients.get(4).getPrice(), 0.001);
        
        assertEquals(IngredientType.FILLING, ingredients.get(5).getType());
        assertEquals("sausage", ingredients.get(5).getName());
        assertEquals(300.0f, ingredients.get(5).getPrice(), 0.001);
    }
}
