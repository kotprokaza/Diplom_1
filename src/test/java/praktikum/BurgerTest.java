package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    
    private Burger burger;
    
    @Mock
    private Bun mockBun;
    
    @Mock
    private Ingredient mockIngredient1;
    
    @Mock
    private Ingredient mockIngredient2;
    
    @Mock
    private Ingredient mockIngredient3;
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }
    
    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        
        // Проверяем, что булка установлена
        assertNotNull("Булка должна быть установлена", burger.bun);
        assertEquals(mockBun, burger.bun);
    }
    
    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient1);
        
        assertEquals("Должен быть добавлен один ингредиент", 1, burger.ingredients.size());
        assertTrue("Ингредиент должен быть в списке", burger.ingredients.contains(mockIngredient1));
    }
    
    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        
        assertEquals(2, burger.ingredients.size());
        
        burger.removeIngredient(0);
        
        assertEquals("Должен остаться один ингредиент", 1, burger.ingredients.size());
        assertFalse("Первый ингредиент должен быть удален", burger.ingredients.contains(mockIngredient1));
        assertTrue("Второй ингредиент должен остаться", burger.ingredients.contains(mockIngredient2));
    }
    
    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        
        // Проверяем исходный порядок
        assertEquals(mockIngredient1, burger.ingredients.get(0));
        assertEquals(mockIngredient2, burger.ingredients.get(1));
        assertEquals(mockIngredient3, burger.ingredients.get(2));
        
        // Перемещаем первый элемент на позицию 2
        burger.moveIngredient(0, 2);
        
        // Проверяем новый порядок
        assertEquals("Элемент на позиции 0 должен быть mockIngredient2", mockIngredient2, burger.ingredients.get(0));
        assertEquals("Элемент на позиции 1 должен быть mockIngredient3", mockIngredient3, burger.ingredients.get(1));
        assertEquals("Элемент на позиции 2 должен быть mockIngredient1", mockIngredient1, burger.ingredients.get(2));
    }
    
    @Test
    public void testGetPrice() {
        // Настройка моков
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getPrice()).thenReturn(50.0f);
        when(mockIngredient2.getPrice()).thenReturn(75.0f);
        
        // Собираем бургер
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        
        // Рассчитываем ожидаемую цену: (100 * 2) + 50 + 75 = 325
        float expectedPrice = (100.0f * 2) + 50.0f + 75.0f;
        float actualPrice = burger.getPrice();
        
        assertEquals("Цена бургера рассчитана неверно", expectedPrice, actualPrice, 0.001);
        
        // Проверяем, что методы были вызваны
        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getPrice();
        verify(mockIngredient2, times(1)).getPrice();
    }
    
    @Test
    public void testGetPriceWithNoIngredients() {
        when(mockBun.getPrice()).thenReturn(150.0f);
        
        burger.setBuns(mockBun);
        
        float expectedPrice = 150.0f * 2; // Только булки
        float actualPrice = burger.getPrice();
        
        assertEquals("Цена только с булками неверна", expectedPrice, actualPrice, 0.001);
    }
    
    @Test
    public void testGetReceipt() {
        // Настройка моков для булки
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100.0f);
        
        // Настройка моков для ингредиентов
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("hot sauce");
        when(mockIngredient1.getPrice()).thenReturn(50.0f);
        
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getName()).thenReturn("cutlet");
        when(mockIngredient2.getPrice()).thenReturn(75.0f);
        
        // Собираем бургер
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        
        // Получаем чек
        String receipt = burger.getReceipt();
        
        // Проверяем содержимое чека
        assertNotNull("Чек не должен быть null", receipt);
        assertTrue("Чек должен содержать название булки", receipt.contains("black bun"));
        assertTrue("Чек должен содержать название соуса", receipt.contains("hot sauce"));
        assertTrue("Чек должен содержать название начинки", receipt.contains("cutlet"));
        assertTrue("Чек должен содержать общую цену", receipt.contains("Price:"));
        
        // Проверяем формат
        String[] lines = receipt.split("\n");
        assertTrue("Первая строка должна содержать булку", lines[0].contains("(==== black bun ====)"));
        assertTrue("Должна быть строка с соусом", lines[1].contains("= sauce hot sauce ="));
        assertTrue("Должна быть строка с начинкой", lines[2].contains("= filling cutlet ="));
        assertTrue("Последняя строка должна содержать булку", lines[3].contains("(==== black bun ====)"));
    }
    
    @Test
    public void testGetReceiptEmptyBurger() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(200.0f);
        
        burger.setBuns(mockBun);
        
        String receipt = burger.getReceipt();
        
        assertNotNull("Чек не должен быть null", receipt);
        assertTrue("Чек должен содержать название булки", receipt.contains("white bun"));
        
        // Исправленная проверка - используем contains с правильным значением
        // Цена: 200.0 * 2 = 400.0
        assertTrue("Чек должен содержать общую цену 400.0", receipt.contains("Price: 400"));
        
        // Проверяем структуру чека
        String[] lines = receipt.split("\n");
        assertTrue("Чек должен содержать минимум 4 строки", lines.length >= 4);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientInvalidIndex() {
        burger.addIngredient(mockIngredient1);
        burger.removeIngredient(5); // Неверный индекс
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientInvalidIndex() {
        burger.addIngredient(mockIngredient1);
        burger.moveIngredient(0, 5); // Неверный индекс
    }
    
    @Test
    public void testGetReceiptFormat() {
        when(mockBun.getName()).thenReturn("test bun");
        when(mockBun.getPrice()).thenReturn(100.0f);
        
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("test sauce");
        when(mockIngredient1.getPrice()).thenReturn(50.0f);
        
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        
        String receipt = burger.getReceipt();
        
        // Проверяем конкретный формат
        assertTrue("Должна быть верхняя булка", receipt.startsWith("(==== test bun ===="));
        assertTrue("Должен быть ингредиент", receipt.contains("= sauce test sauce ="));
        assertTrue("Должна быть нижняя булка", receipt.contains("(==== test bun ===="));
        assertTrue("Должна быть цена 250.0", receipt.contains("Price: 250"));
    }
    
    @Test
    public void testGetReceiptWithMultipleIngredients() {
        when(mockBun.getName()).thenReturn("bun");
        when(mockBun.getPrice()).thenReturn(50.0f);
        
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("sauce1");
        when(mockIngredient1.getPrice()).thenReturn(10.0f);
        
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getName()).thenReturn("filling1");
        when(mockIngredient2.getPrice()).thenReturn(20.0f);
        
        when(mockIngredient3.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient3.getName()).thenReturn("sauce2");
        when(mockIngredient3.getPrice()).thenReturn(15.0f);
        
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        
        String receipt = burger.getReceipt();
        
        // Проверяем наличие всех элементов
        assertTrue(receipt.contains("sauce1"));
        assertTrue(receipt.contains("filling1"));
        assertTrue(receipt.contains("sauce2"));
        // Цена: (50*2) + 10 + 20 + 15 = 145
        assertTrue(receipt.contains("Price: 145"));
    }
}
