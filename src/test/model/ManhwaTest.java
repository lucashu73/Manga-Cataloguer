package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManhwaTest {
    private Manhwa testManhwa;

    @BeforeEach
    public void setup() {
        testManhwa = new Manhwa("Overgeared", "Dummy description.", 10);
    }

    @Test
    public void testConstructor() {
        assertEquals("Overgeared", testManhwa.getTitle());
        assertEquals("Dummy description.", testManhwa.getDescription());
        assertEquals(10, testManhwa.getRating());
    }

    @Test
    public void testConstructorInvalidRating() {
        testManhwa = new Manhwa("Sweet Home", "Dummy description.", -5);

        assertEquals("Sweet Home", testManhwa.getTitle());
        assertEquals("Dummy description.", testManhwa.getDescription());
        assertEquals(-1, testManhwa.getRating());

        testManhwa = new Manhwa("Pigpen", "Dummy description.", 11);

        assertEquals("Pigpen", testManhwa.getTitle());
        assertEquals("Dummy description.", testManhwa.getDescription());
        assertEquals(-1, testManhwa.getRating());
    }

    @Test
    public void testRate() {
        assertFalse(testManhwa.rate(-5));
        assertEquals(10, testManhwa.getRating());
        assertTrue(testManhwa.rate(2));
        assertEquals(2, testManhwa.getRating());
    }


}