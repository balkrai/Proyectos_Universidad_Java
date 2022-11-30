package com.mycompany.conversor;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ClearPanel extends JPanel {

    JButton c;

    public ClearPanel() {
        setLayout(new GridLayout(1, 1));
        c = new JButton("CLEAR");
        c.setActionCommand("clear");
        c.setForeground(Color.BLUE);
        c.setFont(new Font("Arial", Font.PLAIN, 30));
        c.setMargin(new Insets(10, 10, 10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        add(c);
    }

    public void setActionListener(ActionListener a) {
        c.addActionListener(a);
    }

}
