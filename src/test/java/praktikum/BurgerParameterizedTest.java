package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {
    
    private final String bunName;
    private final float bunPrice;
    private final String ingredientName;
    private final float ingredientPrice;
    
    @Mock
    private Bun mockBun;
    
    @Mock
    private Ingredient mockIngredient;
    
    public BurgerParameterizedTest(String bunName, float bunPrice, 
                                   String ingredientName, float ingredientPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
        MockitoAnnotations.openMocks(this);
    }
    
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {"black bun", 100.0f, "sour cream", 50.0f},
            {"white bun", 80.0f, "cutlet", 70.0f},
            {"red bun", 120.0f, "dinosaur", 90.0f}
        });
    }
    
    @Test
    public void testGetPriceWithDifferentBunsAndIngredients() {
        Burger burger = new Burger();
        
        when(mockBun.getName()).thenReturn(bunName);
        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockIngredient.getName()).thenReturn(ingredientName);
        when(mockIngredient.getPrice()).thenReturn(ingredientPrice);
        when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        
        float expectedPrice = bunPrice * 2 + ingredientPrice;
        assertEquals(expectedPrice, burger.getPrice(), 0.01f);
    }
}
