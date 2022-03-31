package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

// Represents a catalogue (list) of manhwa
public class ManhwaCatalogue {

    private LinkedList<Manhwa> catalogue;

    // EFFECTS: constructs empty manhwa catalogue
    public ManhwaCatalogue() {
        catalogue = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: add manhwa to end of queue
    public void addManhwa(Manhwa manhwa) {
        catalogue.addLast(manhwa);
        EventLog.getInstance().logEvent(new Event("Added manhwa: " + manhwa.getTitle()));
    }

    // EFFECTS: if there is a manhwa that has the given title,
    //             - returns the entry number of the manhwa in the catalogue
    //          otherwise return -1
    public int getEntryNumber(String title) {
        int counter = 1;
        int entry = -1;

        for (Manhwa x : this.catalogue) {
            if (Objects.equals(title, x.getTitle())) {
                counter = counter + 1;
                entry = entry + counter;
            } else {
                counter = counter + 1;
            }
        }
        return entry;
    }

    // EFFECTS: gets manhwa with given title
    public Manhwa getManhwa(String title) {
        Manhwa manhwa = null;

        for (Manhwa x : this.catalogue) {
            if (Objects.equals(title, x.getTitle())) {
                manhwa = x;
            }
        }
        return manhwa;
    }

    // EFFECTS: gets list of manhwa in order
    public String getCatalogue() {
        String list = "";

        for (Manhwa x : this.catalogue) {
            list = list + x.getTitle() + "\n";
        }
        EventLog.getInstance().logEvent(new Event("Retrieved list"));
        return list;
    }

    // EFFECTS: gets title, description, and rating of manhwa with given title
    public String getDetails(String title) {
        String details = "";

        for (Manhwa x : this.catalogue) {
            if (Objects.equals(title, x.getTitle())) {
                details = "Title: " + x.getTitle() + "\n"
                        + "Description: " + x.getDescription() + "\n"
                        + "Rating: " + x.getRating();
            }
        }
        if (!details.equals("")) {
            EventLog.getInstance().logEvent(new Event("Retrieved details of the manhwa " + title));
        }
        return details;
    }

    // EFFECTS: returns the number of manhwa in the catalogue
    public int length() {
        return catalogue.size();
    }

    // EFFECTS: returns an unmodifiable list of manhwa in this catalogue
    public List<Manhwa> getListOfManhwa() {
        return Collections.unmodifiableList(catalogue);
    }

    // Used JsonSerializationDemo as reference
    // EFFECTS: converts catalogue to JSON
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("List", manhwasToJson());
        return json;
    }

    // Used JsonSerializationDemo as reference
    // EFFECTS: returns manhwas in this catalogue as a JSON array
    private JSONArray manhwasToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Manhwa m : catalogue) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }

}
