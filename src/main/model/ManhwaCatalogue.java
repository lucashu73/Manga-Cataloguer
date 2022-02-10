package model;

import java.util.LinkedList;
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
        return details;
    }

    // EFFECTS: returns the number of manhwa in the catalogue
    public int length() {
        return catalogue.size();
    }

}
