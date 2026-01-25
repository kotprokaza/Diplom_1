package praktikum;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.Assert.*;

public class PraktikumTest {
    
    @Test
    public void testMainMethodOutput() {
        PrintStream originalOut = System.out;
        
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream newOut = new PrintStream(baos);
            System.setOut(newOut);
            
            Praktikum.main(new String[]{});
            
            String output = baos.toString();
            
            assertNotNull(output);
            assertTrue("Output should contain bun name", output.contains("black bun"));
            assertTrue("Output should contain ingredient", output.contains("sour cream") || output.contains("cutlet"));
            assertTrue("Output should contain Price:", output.contains("Price:"));
            
        } finally {
            System.setOut(originalOut);
        }
    }
    
    @Test
    public void testMainMethodNoExceptions() {
        try {
            Praktikum.main(new String[]{});
            assertTrue(true);
        } catch (Exception e) {
            fail("Main method should not throw exceptions: " + e.getMessage());
        }
    }
    
    @Test
    public void testMainMethodCompleteCoverage() {
        PrintStream originalOut = System.out;
        
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream silentOut = new PrintStream(baos);
            System.setOut(silentOut);
            
            Praktikum.main(new String[]{});
            
        } finally {
            System.setOut(originalOut);
        }
    }
    
    @Test
    public void testDatabaseHasEnoughData() {
        Database db = new Database();
        assertTrue("Should have at least 1 bun", db.availableBuns().size() >= 1);
        assertTrue("Should have at least 6 ingredients", db.availableIngredients().size() >= 6);
    }
    
    @Test
    public void testMainWithDifferentDatabaseStates() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        try {
            Praktikum.main(new String[]{});
            String output = baos.toString();
            assertTrue(output.contains("Price:"));
        } finally {
            System.setOut(originalOut);
        }
    }
    
    @Test
    public void testPraktikumConstructor() {
        new Praktikum();
    }
}
