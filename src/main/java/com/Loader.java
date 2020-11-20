package com;

import javax.swing.*;

public class Loader {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame();
            Form swing = new Form();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(650, 400);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(swing.getRootPanel());
            frame.setVisible(true);

        });

    }
}
