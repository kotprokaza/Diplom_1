package praktikum;

import org.junit.Before;
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
    
    private Burger burger;
    
    @Mock
    private Bun mockBun;
    
    @Mock
    private Ingredient mockIngredient1;
    
    @Mock
    private Ingredient mockIngredient2;
    
    // Параметры теста
    private final float bunPrice;
    private final float ingredient1Price;
    private final float ingredient2Price;
    private final float expectedPrice;
    
    public BurgerParameterizedTest(float bunPrice, float ingredient1Price, 
                                   float ingredient2Price, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredient1Price = ingredient1Price;
        this.ingredient2Price = ingredient2Price;
        this.expectedPrice = expectedPrice;
    }
    
    @Parameterized.Parameters(name = "Булка: {0}, Ингредиент1: {1}, Ингредиент2: {2}, Ожидаемая цена: {3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            // bunPrice, ingredient1Price, ingredient2Price, expectedPrice
            {100.0f, 50.0f, 75.0f, 325.0f},     // (100*2) + 50 + 75 = 325
            {0.0f, 50.0f, 75.0f, 125.0f},       // (0*2) + 50 + 75 = 125
            {200.0f, 0.0f, 0.0f, 400.0f},       // (200*2) + 0 + 0 = 400
            {150.5f, 75.25f, 100.75f, 477.0f},  // (150.5*2) + 75.25 + 100.75 = 477.0
            {10.0f, 5.0f, 5.0f, 30.0f}          // (10*2) + 5 + 5 = 30
        });
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
        
        // Настройка моков с параметрами
        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockIngredient1.getPrice()).thenReturn(ingredient1Price);
        when(mockIngredient2.getPrice()).thenReturn(ingredient2Price);
        
        // Собираем бургер
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
    }
    
    @Test
    public void testGetPriceWithDifferentCombinations() {
        float actualPrice = burger.getPrice();
        assertEquals("Цена рассчитана неверно для параметров: " +
                    bunPrice + ", " + ingredient1Price + ", " + ingredient2Price,
                    expectedPrice, actualPrice, 0.001);
    }
}
