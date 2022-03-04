package persistence;

// Used JsonSerializationDemo as reference

import model.Manhwa;
import model.ManhwaCatalogue;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ManhwaCatalogue mc = new ManhwaCatalogue();
            JsonWriter writer = new JsonWriter("./data/my\0invalid:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyManhwaCatalogue() {
        try {
            ManhwaCatalogue mc = new ManhwaCatalogue();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyManhwaCatalogue.json");
            writer.open();
            writer.write(mc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyManhwaCatalogue.json");
            mc = reader.read();
            assertEquals(0, mc.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterSampleManhwaCatalogue() {
        try {
            ManhwaCatalogue mc = new ManhwaCatalogue();
            mc.addManhwa(new Manhwa("Overgeared", "Virtual reality game", 9));
            mc.addManhwa(new Manhwa("Sweet home", "Monsters and zombies", 10));
            JsonWriter writer = new JsonWriter("./data/testWriterSampleManhwaCatalogue.json");
            writer.open();
            writer.write(mc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterSampleManhwaCatalogue.json");
            mc = reader.read();
            List<Manhwa> list = mc.getListOfManhwa();
            assertEquals(2, list.size());
            checkManhwa("Virtual reality game", 9, "Overgeared", list.get(0));
            checkManhwa("Monsters and zombies", 10, "Sweet home", list.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
