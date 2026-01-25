package praktikum;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;

public class PraktikumTest {
    
    @Test
    public void testMainMethodOutput() {
        // Сохраняем оригинальный System.out
        PrintStream originalOut = System.out;
        
        try {
            // Создаем ByteArrayOutputStream для перехвата вывода
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream newOut = new PrintStream(baos);
            System.setOut(newOut);
            
            // Вызываем main метод
            Praktikum.main(new String[]{});
            
            // Получаем вывод
            String output = baos.toString();
            
            // Проверяем, что вывод содержит ожидаемые элементы
            assertNotNull(output);
            assertTrue("Output should contain bun name", output.contains("black bun"));
            assertTrue("Output should contain ingredient", output.contains("sour cream") || output.contains("cutlet"));
            assertTrue("Output should contain Price:", output.contains("Price:"));
            
        } finally {
            // Восстанавливаем оригинальный System.out
            System.setOut(originalOut);
        }
    }
    
    @Test
    public void testMainMethodNoExceptions() {
        // Просто проверяем, что метод main выполняется без исключений
        try {
            Praktikum.main(new String[]{});
            // Если дошли сюда, значит исключений не было
            assertTrue(true);
        } catch (Exception e) {
            fail("Main method should not throw exceptions: " + e.getMessage());
        }
    }
}
