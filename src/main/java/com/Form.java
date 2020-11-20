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
    private JPanel dataPanel;
    private JPanel contentPanel;
    private JPanel buttonPanel;

    private final DefaultListModel<String> dml;
    private JList<String> resultArea;
    private JTextField pathField;
    private JTextField patternField;
    private JLabel info;
    private JButton searchMatches;



    public Form() {
        dml = new DefaultListModel<>();
        resultArea.setModel(dml);
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));

        searchMatches.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMatches.setEnabled(false);
                try {
                    String path = pathField.getText();
                    String pattern = patternField.getText();
                    if (new File(path).exists() && pattern.length() > 0) {
                        List<String> matches = Processor.getMatches(path, pattern);
                        dml.clear();
                        dml.addAll(matches);
                        info.setText("Matches found: " + dml.size());

                    } else {
                        info.setText("Проверьте правильность введённых данных.");
                    }
                } catch (OutOfMemoryError ex) {
                    info.setText("Превышен лимит по памяти. Попробуйте сократить область поиска.");
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
