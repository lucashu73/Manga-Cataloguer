package ui;

import model.Event;
import model.EventLog;
import model.Manhwa;
import model.ManhwaCatalogue;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Graphical user interface for manhwa cataloguer
// Used the following materials to help design GUI:
//  - https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
//  - https://docs.oracle.com/javase/tutorial/uiswing/events/intro.html
//  - https://web.mit.edu/6.005/www/sp14/psets/ps4/java-6-tutorial/components.html
//  - https://youtu.be/Kmgo00avvEw
public class GUI extends JFrame implements ActionListener {

    private JFrame frame;
    private JPanel leftPanel;
    private JPanel rightPanel1;
    private JPanel rightPanel2;
    private JPanel bottomPanel;
    private JPanel centerPanel;
    private JTextField addNameTextField;
    private JTextField addDescTextField;
    private JTextField addRatingTextField;
    private JTextField detailsNameTextField;
    private JTextField rateNameTextField;
    private JTextField rateRatingTextField;
    private JTextArea displayText;
    private ImageIcon image;
    private ImageIcon goodbye;

    private static final String JSON_STORE = "./data/catalogue.json";
    private ManhwaCatalogue myList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs GUI with all panels and components in frame
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public GUI() {

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = dim.width * 2 / 3;
        int screenHeight = dim.height * 2 / 3;

        frame = new JFrame();
        myList = new ManhwaCatalogue();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        leftPanel = new JPanel();
        leftPanel.setBackground(Color.decode("#ABEBC6"));
        leftPanel.setBounds(0, 0, screenWidth * 2 / 9, screenHeight * 3 / 4);

        JLabel addLabel = new JLabel("Add manhwa");
        addLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        addLabel.setHorizontalAlignment(JLabel.CENTER);
        addLabel.setVerticalAlignment(JLabel.CENTER);

        JLabel addNameLabel = new JLabel("Name:");
        JLabel addDescLabel = new JLabel("Description:");
        JLabel addRatingLabel = new JLabel("Rating:");

        addNameTextField = new JTextField();
        addDescTextField = new JTextField();
        addRatingTextField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.setActionCommand("add");
        addButton.addActionListener(this);
        addButton.setFocusable(false);

        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridLayout leftPanelLayout = new GridLayout(0, 1);
        leftPanel.setLayout(leftPanelLayout);
        leftPanel.add(addLabel);
        leftPanel.add(addNameLabel);
        leftPanel.add(addNameTextField);
        leftPanel.add(addDescLabel);
        leftPanel.add(addDescTextField);
        leftPanel.add(addRatingLabel);
        leftPanel.add(addRatingTextField);
        leftPanelLayout.setVgap(20);
        leftPanel.add(addButton);


        centerPanel = new JPanel();
        centerPanel.setBackground(Color.white);
        centerPanel.setBounds(screenWidth * 2 / 9, 0, screenWidth * 5 / 9, screenHeight * 3 / 4);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        displayText = new JTextArea("Welcome to My Manhwa Cataloguer!");
        centerPanel.add(displayText);


        rightPanel1 = new JPanel();
        rightPanel1.setBackground(Color.decode("#AED6F1"));
        rightPanel1.setBounds(screenWidth * 7 / 9, 0, screenWidth * 2 / 9, screenHeight * 3 / 8);

        JLabel detailsLabel = new JLabel("View details of manhwa");
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        detailsLabel.setHorizontalAlignment(JLabel.CENTER);
        detailsLabel.setVerticalAlignment(JLabel.CENTER);

        JLabel detailsNameLabel = new JLabel("Name:");
        detailsNameTextField = new JTextField();
        JButton detailsButton = new JButton("View details");
        detailsButton.setActionCommand("details");
        detailsButton.addActionListener(this);
        detailsButton.setFocusable(false);

        rightPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 25));
        GridLayout rightPanelLayout1 = new GridLayout(0, 1);
        rightPanel1.setLayout(rightPanelLayout1);
        rightPanel1.add(detailsLabel);
        rightPanel1.add(detailsNameLabel);
        rightPanel1.add(detailsNameTextField);
        rightPanelLayout1.setVgap(20);
        rightPanel1.add(detailsButton);


        rightPanel2 = new JPanel();
        rightPanel2.setBackground(Color.decode("#F9E79F"));
        rightPanel2.setBounds(screenWidth * 7 / 9, screenHeight * 3 / 8,
                screenWidth * 2 / 9, screenHeight * 3 / 8);

        JLabel rateLabel = new JLabel("Rate manhwa");
        rateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        rateLabel.setHorizontalAlignment(JLabel.CENTER);
        rateLabel.setVerticalAlignment(JLabel.CENTER);

        JLabel rateNameLabel = new JLabel("Name:");
        JLabel rateRatingLabel = new JLabel("Rating:");
        rateNameTextField = new JTextField();
        rateRatingTextField = new JTextField();
        JButton rateButton = new JButton("Rate");
        rateButton.setActionCommand("rate");
        rateButton.addActionListener(this);
        rateButton.setFocusable(false);

        rightPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 25));
        GridLayout rightPanelLayout2 = new GridLayout(0, 1);
        rightPanel2.setLayout(rightPanelLayout2);
        rightPanel2.add(rateLabel);
        rightPanel2.add(rateNameLabel);
        rightPanel2.add(rateNameTextField);
        rightPanel2.add(rateRatingLabel);
        rightPanel2.add(rateRatingTextField);
        rightPanelLayout2.setVgap(20);
        rightPanel2.add(rateButton);


        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.decode("#AEB6BF"));
        bottomPanel.setBounds(0, screenHeight * 3 / 4, screenWidth, screenHeight / 4);

        JButton viewButton = new JButton();
        viewButton.setText("View list");
        viewButton.setActionCommand("view");
        viewButton.addActionListener(this);
        viewButton.setFocusable(false);

        JButton saveButton = new JButton();
        saveButton.setText("Save list");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);
        saveButton.setFocusable(false);

        JButton loadButton = new JButton();
        loadButton.setText("Load list");
        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);
        loadButton.setFocusable(false);

        JButton quitButton = new JButton();
        quitButton.setText("Quit");
        quitButton.setActionCommand("quit");
        quitButton.addActionListener(this);
        quitButton.setFocusable(false);

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(viewButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(loadButton);
        bottomPanel.add(quitButton);


        frame.setTitle("My Manhwa Cataloguer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(screenWidth, screenHeight);
        frame.setResizable(false);
        image = new ImageIcon("src/main/ui/icon.png");
        goodbye = new ImageIcon("src/main/ui/anime-wave.gif");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(200, 225, 250));

        frame.add(leftPanel);
        frame.add(centerPanel);
        frame.add(rightPanel1);
        frame.add(rightPanel2);
        frame.add(bottomPanel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    // EFFECTS: takes in a certain event and performs appropriate action
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            String title = addNameTextField.getText();
            String description = addDescTextField.getText();
            int rating;

            String testInt = addRatingTextField.getText();

            try {
                Integer.parseInt(testInt);
                rating = Integer.parseInt(addRatingTextField.getText());
                if (rating < 1 || rating > 10) {
                    displayText.setText("Invalid rating!");
                    addRatingTextField.setText("");
                } else {
                    myList.addManhwa(new Manhwa(title, description, rating));
                    displayText.setText("Manhwa added!");
                    addNameTextField.setText("");
                    addDescTextField.setText("");
                    addRatingTextField.setText("");
                }
            } catch (InputMismatchException | NumberFormatException f) {
                displayText.setText("Invalid rating!");
                addRatingTextField.setText("");
            }
        }
        if (e.getActionCommand().equals("details")) {
            if (myList.getDetails(detailsNameTextField.getText()) == "") {
                displayText.setText("No such manhwa exists!");
                detailsNameTextField.setText("");
            } else {
                displayText.setText(myList.getDetails(detailsNameTextField.getText()));
                detailsNameTextField.setText("");
            }
        }
        if (e.getActionCommand().equals("rate")) {
            String title = rateNameTextField.getText();
            if (myList.getManhwa(title) == null) {
                displayText.setText("No such manhwa exists!");
                rateNameTextField.setText("");
                rateRatingTextField.setText("");
            } else {
                try {
                    int rating = Integer.parseInt(rateRatingTextField.getText());

                    if (myList.getManhwa(title).rate(rating)) {
                        displayText.setText("Rated!");
                        rateNameTextField.setText("");
                        rateRatingTextField.setText("");
                    } else {
                        displayText.setText("Invalid rating!");
                        rateRatingTextField.setText("");
                    }
                } catch (InputMismatchException | NumberFormatException f) {
                    displayText.setText("Invalid rating!");
                    rateRatingTextField.setText("");
                }
            }
        }
        if (e.getActionCommand().equals("view")) {
            displayText.setText(myList.getCatalogue());
        }
        if (e.getActionCommand().equals("load")) {
            try {
                myList = jsonReader.read();
                displayText.setText("Loaded from " + JSON_STORE);
            } catch (IOException f) {
                displayText.setText("Unable to read from file: " + JSON_STORE);
            }
        }
        if (e.getActionCommand().equals("save")) {
            try {
                jsonWriter.open();
                jsonWriter.write(myList);
                jsonWriter.close();
                displayText.setText("List saved to" + JSON_STORE + "!");
            } catch (FileNotFoundException f) {
                displayText.setText("Unable to write to file:" + JSON_STORE);
            }
        }
        if (e.getActionCommand().equals("quit")) {
            int x = JOptionPane.showConfirmDialog(frame, "Would you like to save your list before exiting?",
                    "Exit confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION, goodbye);
            if (x == JOptionPane.YES_OPTION) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(myList);
                    jsonWriter.close();
                    displayText.setText("List saved to" + JSON_STORE + "!");
                } catch (FileNotFoundException f) {
                    displayText.setText("Unable to write to file:" + JSON_STORE);
                }
            }
            for (Event event: EventLog.getInstance()) {
                System.out.println(event);
            }
            System.exit(0);
        }
    }
}
