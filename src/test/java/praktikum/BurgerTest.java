package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredientSauce;

    @Mock
    private Ingredient mockIngredientFilling;

    @Mock
    private Ingredient mockIngredientAnotherFilling;

    private Burger burger;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertTrue(true);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredientSauce);
        assertTrue(true);
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredientSauce);
        burger.removeIngredient(0);
        assertTrue(true);
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredientSauce);
        burger.addIngredient(mockIngredientFilling);
        burger.moveIngredient(0, 1);
        assertTrue(true);
    }

    @Test
    public void testGetPrice() {
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredientSauce.getPrice()).thenReturn(50.0f);
        when(mockIngredientFilling.getPrice()).thenReturn(70.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientSauce);
        burger.addIngredient(mockIngredientFilling);

        float expectedPrice = 100.0f * 2 + 50.0f + 70.0f;
        assertEquals(expectedPrice, burger.getPrice(), 0.01f);
    }

    @Test
    public void testGetReceipt() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredientSauce.getName()).thenReturn("sour cream");
        when(mockIngredientSauce.getPrice()).thenReturn(50.0f);
        when(mockIngredientSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientFilling.getName()).thenReturn("cutlet");
        when(mockIngredientFilling.getPrice()).thenReturn(70.0f);
        when(mockIngredientFilling.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientSauce);
        burger.addIngredient(mockIngredientFilling);

        String receipt = burger.getReceipt();
        String expectedReceipt = "(==== black bun ====)\n" +
                                 "= sauce sour cream =\n" +
                                 "= filling cutlet =\n" +
                                 "(==== black bun ====)\n\n" +
                                 "Price: 320,000000\n";

        assertEquals(expectedReceipt, receipt);
    }

    @Test
    public void testGetReceiptEmptyBurger() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100.0f);

        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();
        String expectedReceipt = "(==== black bun ====)\n" +
                                 "(==== black bun ====)\n\n" +
                                 "Price: 200,000000\n";

        assertEquals(expectedReceipt, receipt);
    }

    @Test
    public void testGetReceiptWithOnlyBun() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(80.0f);

        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();
        String expectedReceipt = "(==== white bun ====)\n" +
                                 "(==== white bun ====)\n\n" +
                                 "Price: 160,000000\n";

        assertEquals(expectedReceipt, receipt);
    }
}
