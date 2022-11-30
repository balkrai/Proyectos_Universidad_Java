/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conversor;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Jose
 */
public class NumerosPanel extends JPanel {

    ArrayList<JButton> bts = new ArrayList<>();
    private JButton punto;
    private JButton c;

    public NumerosPanel() {
        setLayout(new GridLayout(3, 4, 3, 3));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        for (int i = 0; i < 10; i++) {
            JButton b = new JButton(String.valueOf(i));
            b.setActionCommand(String.valueOf(i));
            bts.add(b);
            this.add(b);
        }
        punto = new JButton(".");
        add(punto);
        c = new JButton("C");
        add(c);
    }

    public void setActionListener(ActionListener a) {
        for (JButton b : bts) {
            b.addActionListener(a);
        }
        c.addActionListener(a);
        punto.addActionListener(a);
    }
}
