package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Graphical user interface for manhwa cataloguer
public class GUI extends JFrame implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private ImageIcon image;

    public GUI() {

        frame = new JFrame();
        panel = new JPanel();
        label = new JLabel("Label test");
        image = new ImageIcon("src/main/ui/icon.png");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        JButton button = new JButton("Test - Click Me!");
        button.addActionListener(this);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);
        panel.setBackground(new Color(200, 225, 250));

        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("My Manhwa Cataloguer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(dim.width / 2, dim.height / 2);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(200, 225, 250));


    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
