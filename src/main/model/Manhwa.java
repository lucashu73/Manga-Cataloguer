package model;

// Represents a manhwa/manga/anime having a title, a description, and 10-point scale rating
public class Manhwa {
    private String title;
    private String description;
    private int rating;

    // REQUIRES: title and desc has a non-zero length
    // EFFECTS: title of manhwa is set to initialTitle
    //          title of description is set to initialDescription
    //          if initialRating <=10 and initialRating >= 0 then rating is set to initialRating,
    //          otherwise ratings is -1 (unrated)
    public Manhwa(String initialTitle, String initialDescription, int initialRating) {
        this.title = initialTitle;
        this.description = initialDescription;
        if (initialRating <= 10 && initialRating >= 0) {
            rating = initialRating;
        } else {
            rating = -1;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    // REQUIRES: newRating <= 10 and newRating > 0
    // MODIFIES: this
    // EFFECTS: if rating is valid, set to new rating and return true,
    //          otherwise rating stays the same and return false
    public boolean rate(int newRating) {
        if (newRating <= 10 && newRating > 0) {
            rating = rating + -1 * rating + newRating;
            return true;
        } else {
            return false;
        }
    }

}
