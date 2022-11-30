package com.mycompany.conversor;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author
 */
public class DisplayPanel extends JPanel {

    private JTextArea txta;
    private JLabel label;

    public DisplayPanel() {

        this.setLayout(new GridLayout(2, 2));
        this.setBackground(Color.decode("#b4aa69"));
        this.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        txta = new JTextArea("0");
        label = new JLabel("Exchange rate: 1.11253");

        txta.setBackground(Color.decode("#b4aa69"));

        Font font = txta.getFont();
        float size = font.getSize() + 20.0f;
        txta.setFont(font.deriveFont(size));

        txta.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txta.setEditable(false);
        add(Box.createRigidArea(new Dimension(10, 10)));
        add(txta);
        add(label);
        add(Box.createRigidArea(new Dimension(10, 10)));
    }

    public void setRt(String rt) {
        this.label.setText("Exchange rate: " + rt);
    }

    public void setTexto(String t) {
        txta.setText(t);
    }
}
