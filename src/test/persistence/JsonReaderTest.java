package persistence;

// Used JsonSerializationDemo as reference

import model.Manhwa;
import model.ManhwaCatalogue;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistentFile.json");
        try {
            ManhwaCatalogue mc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyManhwaCatalogue() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyManhwaCatalogue.json");
        try {
            ManhwaCatalogue mc = reader.read();
            assertEquals(0, mc.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderSampleManhwaCatalogue() {
        JsonReader reader = new JsonReader("./data/testReaderSampleManhwaCatalogue.json");
        try {
            ManhwaCatalogue mc = reader.read();
            List<Manhwa> list = mc.getListOfManhwa();
            assertEquals(3, list.size());
            checkManhwa("Virtual reality game", 9, "Overgeared", list.get(0));
            checkManhwa("Monsters and zombies", 10, "Sweet home", list.get(1));
            checkManhwa("Isekai", 8, "The beginning after the end", list.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
