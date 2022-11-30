/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uv.eu.photoeditor.view;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Jose
 */
public class Norte extends JPanel {

    private JLabel l;
    private JSlider s;

    public Norte() {
        l = new JLabel("Grosor del rectangulo:");
        s = new JSlider(JSlider.HORIZONTAL, 1, 1000, 50);
        s.setMajorTickSpacing(100);
        s.setMinorTickSpacing(100);
        s.setPaintTicks(true);
        s.setPaintLabels(true);

        setLayout(new GridLayout(2, 1));
        add(l);
        add(s);
    }

    public void setJSliderListener(ChangeListener c) {
        s.addChangeListener(c);
    }
}
