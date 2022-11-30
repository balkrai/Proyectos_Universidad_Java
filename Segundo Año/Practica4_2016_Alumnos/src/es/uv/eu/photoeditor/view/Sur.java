/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uv.eu.photoeditor.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Jose
 */
public class Sur extends JPanel {

    private JLabel lg, lc1, lc2;
    private JTextArea g, c1, c2;

    public Sur() {
        lg = new JLabel("Grosor del rectangulo: ");
        lc1 = new JLabel("Color 1: ");
        lc2 = new JLabel("Color 2: ");
        g = new JTextArea("50");
        g.setEditable(false);
        c1 = new JTextArea(1, 4);
        c1.setEditable(false);

        c2 = new JTextArea(1, 4);
        c2.setEditable(false);
        g.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        c1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        c2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        add(lg);
        add(g);
        add(Box.createRigidArea(new Dimension(10, 20)));

        add(lc1);
        add(c1);
        add(Box.createRigidArea(new Dimension(10, 20)));

        add(lc2);
        add(c2);
    }

    public void cambiarGrosor(int x) {
        g.setText(String.valueOf(x));
    }

    public void cambiarColor1(Color c) {
        c1.setBackground(c);
    }

    public void cambiarColor2(Color c) {
        c2.setBackground(c);
    }

    public int getGrosor() {
        return Integer.parseInt(g.getText());
    }

    public Color getColor1() {
        return c1.getBackground();
    }

    public Color getColor2() {
        return c2.getBackground();
    }
    
}
