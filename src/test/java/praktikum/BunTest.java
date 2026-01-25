package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class BunTest {
    
    @Test
    public void testBunConstructorAndGetters() {
        Bun bun = new Bun("Test Bun", 100.5f);
        
        assertEquals("Test Bun", bun.getName());
        assertEquals(100.5f, bun.getPrice(), 0.001);
    }
    
    @Test
    public void testBunWithZeroPrice() {
        Bun bun = new Bun("Free Bun", 0.0f);
        
        assertEquals("Free Bun", bun.getName());
        assertEquals(0.0f, bun.getPrice(), 0.001);
    }
    
    @Test
    public void testBunWithSpecialCharacters() {
        Bun bun = new Bun("Special_Bun-123", 99.99f);
        
        assertEquals("Special_Bun-123", bun.getName());
        assertEquals(99.99f, bun.getPrice(), 0.001);
    }
}
