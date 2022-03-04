package persistence;

// Used JsonSerializationDemo as reference

import model.Manhwa;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkManhwa(String description, int rating, String title, Manhwa manhwa) {
        assertEquals(description, manhwa.getDescription());
        assertEquals(rating, manhwa.getRating());
        assertEquals(title, manhwa.getTitle());
    }
}
