package persistence;

// Used JsonSerializationDemo as reference

import model.Manhwa;
import model.ManhwaCatalogue;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads catalogue from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads catalogue from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ManhwaCatalogue read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseManhwaCatalogue(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses catalogue from JSON object and returns it
    private ManhwaCatalogue parseManhwaCatalogue(JSONObject jsonObject) {
        ManhwaCatalogue mc = new ManhwaCatalogue();
        addManhwas(mc, jsonObject);
        return mc;
    }

    // MODIFIES: mc
    // EFFECTS: parses manhwas from JSON object and adds them to catalogue
    private void addManhwas(ManhwaCatalogue mc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("List");
        for (Object json : jsonArray) {
            JSONObject nextManhwa = (JSONObject) json;
            addManhwa(mc, nextManhwa);
        }
    }

    // MODIFIES: mc
    // EFFECTS: parses manhwa from JSON object and adds it to catalogue
    private void addManhwa(ManhwaCatalogue mc, JSONObject jsonObject) {
        String title = jsonObject.getString("Title");
        String description = jsonObject.getString("Description");
        int rating = jsonObject.getInt("Rating");
        Manhwa manhwa = new Manhwa(title, description, rating);
        mc.addManhwa(manhwa);
    }
}
