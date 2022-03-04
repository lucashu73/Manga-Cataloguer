package ui;

import model.ManhwaCatalogue;
import model.Manhwa;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Used TellerApp as a reference to design ui

// Manhwa/manga/anime cataloguer desktop application
public class Cataloguer {

    private static final String JSON_STORE = "./data/catalogue.json";
    private ManhwaCatalogue myList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs cataloguer
    public Cataloguer() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runCataloguer();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCataloguer() {
        boolean appRunning = true;
        String command = null;

        myList = new ManhwaCatalogue();
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        runStartMenu();

        while (appRunning) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                appRunning = false;
            } else {
                processCommand(command);
            }
        }

        runExitMenu();
    }

    // MODIFIES: this
    // EFFECTS: processes user's start input
    private void runStartMenu() {
        boolean appStarting = true;
        String startCommand = null;

        while (appStarting) {
            displayStartMenu();
            startCommand = input.next();
            startCommand = startCommand.toLowerCase();

            if (startCommand.equals("y")) {
                loadCatalogue();
                System.out.println("List successfully loaded!");
                appStarting = false;
            } else if (startCommand.equals("n")) {
                System.out.println("Create your own manhwa list!");
                appStarting = false;
            } else {
                System.out.println("Your selection was not valid...");
            }
        }
    }

    // EFFECTS: displays start menu where the user has the option to load their list before starting
    private void displayStartMenu() {
        System.out.println("\nWould you like to load your list from file?");
        System.out.println("\ty -> Yes");
        System.out.println("\tn -> No");
    }

    // MODIFIES: this
    // EFFECTS: processes user's exit input
    private void runExitMenu() {
        boolean appExiting = true;
        String exitCommand = null;

        while (appExiting) {
            displayExitMenu();
            exitCommand = input.next();
            exitCommand = exitCommand.toLowerCase();

            if (exitCommand.equals("y")) {
                saveCatalogue();
                System.out.println("List successfully saved. Goodbye!");
                appExiting = false;
            } else if (exitCommand.equals("n")) {
                System.out.println("Goodbye!");
                appExiting = false;
            } else {
                System.out.println("Your selection was not valid...");
            }
        }
    }

    // EFFECTS: displays exit menu where the user has the option to save their list before exiting
    private void displayExitMenu() {
        System.out.println("\nWould you like to save your list to file before exiting?");
        System.out.println("\ty -> Yes");
        System.out.println("\tn -> No");
    }

    // EFFECTS: displays menu of commands the user can input
    private void displayMenu() {
        System.out.println("\nSelect from the commands below:");
        System.out.println("\ta -> Add manhwa");
        System.out.println("\tv -> View list of your manhwa");
        System.out.println("\td -> Select a manhwa to view its details");
        System.out.println("\tr -> Select a manhwa to rate it");
        System.out.println("\ts -> Save list of your manhwa to file");
        System.out.println("\tl -> Load list of your manhwa from file");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user's command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddManhwa();
        } else if (command.equals("v")) {
            doViewList();
        } else if (command.equals("d")) {
            doDetailsManhwa();
        } else if (command.equals("r")) {
            doRateManhwa();
        } else if (command.equals("s")) {
            saveCatalogue();
        } else if (command.equals("l")) {
            loadCatalogue();
        } else {
            System.out.println("Your selection was not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: add manhwa to list
    // found method nextLine() to use instead of nextDouble()
    private void doAddManhwa() {
        System.out.print("Enter title of manhwa: ");
        String title = input.next();

        System.out.print("Enter description of manhwa: ");
        String description = input.next();

        int rating;

        while (true) {
            try {
                System.out.print("Give this manhwa a rating (1-10): ");
                rating = input.nextInt();
                if (rating < 1 || rating > 10) {
                    System.out.println("Invalid rating!");
                    input.nextLine();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid rating!");
                input.nextLine();
            }
        }

        myList.addManhwa(new Manhwa(title, description, rating));
        System.out.println("Manhwa added!");
    }

    // EFFECTS: view list of manhwa
    private void doViewList() {
        System.out.print(myList.getCatalogue());
    }

    // EFFECTS: view details of manhwa
    private void doDetailsManhwa() {
        System.out.print("Enter title of manhwa you would like to see details of: ");
        String title = input.next();
        if (myList.getDetails(title) == "") {
            System.out.println("No such manhwa exists!");
        } else {
            System.out.println(myList.getDetails(title));
        }
    }

    // MODIFIES: this
    // EFFECTS: rate manhwa
    private void doRateManhwa() {
        System.out.print("Enter title of manhwa you would like to rate: ");
        String title = input.next();
        if (myList.getManhwa(title) == null) {
            System.out.println("No such manhwa exists!");
        } else {
            try {
                System.out.print("Enter rating (1-10): ");
                int rating = input.nextInt();

                if (myList.getManhwa(title).rate(rating)) {
                    System.out.println("Rated!");
                } else {
                    System.out.println("Invalid rating!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid rating!");
                input.nextLine();
            }
        }
    }

    // Used JsonSerializationDemo as reference
    // EFFECTS: saves the catalogue to file
    private void saveCatalogue() {
        try {
            jsonWriter.open();
            jsonWriter.write(myList);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Used JsonSerializationDemo as reference
    // MODIFIES: this
    // EFFECTS: loads the catalogue to file
    private void loadCatalogue() {
        try {
            myList = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
