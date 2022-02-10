package ui;

import model.ManhwaCatalogue;
import model.Manhwa;

import java.util.Scanner;

// Used TellerApp as a reference to design ui

// Manhwa/manga/anime cataloguer desktop application
public class Cataloguer {

    private ManhwaCatalogue myList;
    private Scanner input;

    // EFFECTS: runs cataloguer
    public Cataloguer() {
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
    }

    // EFFECTS: displays menu of commands the user can input
    private void displayMenu() {
        System.out.println("\nSelect from the commands below:");
        System.out.println("\ta -> Add manhwa");
        System.out.println("\tv -> View list of your manhwa");
        System.out.println("\td -> Select a manhwa to view its details");
        System.out.println("\tr -> Select a manhwa to rate it");
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

        System.out.print("Give this manhwa a rating (1-10): ");
        int rating = input.nextInt();

        myList.addManhwa(new Manhwa(title, description, rating));
        System.out.print("Manhwa added!");
    }

    // EFFECTS: view list of manhwa
    private void doViewList() {
        System.out.print(myList.getCatalogue());
    }

    // EFFECTS: view details of manhwa
    private void doDetailsManhwa() {
        System.out.print("Enter title of manhwa you would like to see details of: ");
        String title = input.next();
        System.out.print(myList.getDetails(title));
    }

    // MODIFIES: this
    // EFFECTS: rate manhwa
    private void doRateManhwa() {
        System.out.print("Enter title of manhwa you would like to rate: ");
        String title = input.next();
        System.out.print("Enter rating: ");
        int rating = input.nextInt();

        if (myList.getManhwa(title) == null) {
            System.out.print("No such manhwa exists!");
        } else {
            if (myList.getManhwa(title).rate(rating)) {

                System.out.print("Rated!");
            } else {
                System.out.print("Invalid rating!");
            }
        }
    }

}
