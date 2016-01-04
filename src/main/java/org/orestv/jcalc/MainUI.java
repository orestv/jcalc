package org.orestv.jcalc;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MainUI extends JFrame {
    private JPanel mainPanel;

    public MainUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        MigLayout layout = new MigLayout();
        mainPanel = new JPanel(layout);

        this.add(mainPanel);
    }
}
