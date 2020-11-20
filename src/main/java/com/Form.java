package com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class Form {
    private JPanel rootPanel;
    private JPanel contentPanel;
    private JPanel buttonPanel;
    private JPanel dataPanel;

    private JTextField patternField;
    private JTextField pathField;
    private JTextArea resultArea;
    private JButton searchMatches;


    public Form() {
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        searchMatches.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMatches.setEnabled(false);
                try {
                    String path = pathField.getText();
                    String pattern = patternField.getText();
                    if (new File(path).exists() && pattern.length() > 0) {
                        resultArea.setText("");
                        List<String> matches = Processor.getMatches(path, pattern);
                        matches.forEach(x -> resultArea.append(x + "\n"));
                    }
                } finally {
                    searchMatches.setEnabled(true);
                }
            }
        });
        pathField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JFileChooser fileOpen = new JFileChooser();
                    fileOpen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    fileOpen.showDialog(null, "select");
                    File file = fileOpen.getSelectedFile();
                    if (file != null) {
                        pathField.setText(file.getPath());
                    }
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

}
