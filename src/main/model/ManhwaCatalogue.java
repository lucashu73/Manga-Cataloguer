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

    // EFFECTS: returns the number of manhwa in the catalogue
    public int length() {
        return catalogue.size();
    }

}
