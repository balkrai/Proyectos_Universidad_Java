/*
 * Clase que representa una casilla donde se pondrá las letras
 */
package com.mycompany.proyecto_final;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Borja Somovilla del Saz, Jose Miguel Vallet Comes
 */
public class Cuadrado extends JPanel {

    private String letra;  //letra de la casilla
    private Color color;   // color de la casilla
    private JTextPane area; // cuadro de texto  donde se pondra la letra 
    private JLabel lbl;   // label por si se activa el modo daltonico

    public Cuadrado() {
        area = new JTextPane();
        area.setFont(new Font("Serif", Font.BOLD, 20));

        StyledDocument doc = area.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        lbl = new JLabel(" ",SwingConstants.CENTER);

        color = Color.white;

        setLayout(new BorderLayout());
        this.setBackground(color);
        area.setEditable(false);
        add(area, BorderLayout.CENTER);
        add(lbl,BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createLineBorder(Color.gray));
    }

    /**
     * 
     * @return Devuelve el text area
     */
    public JTextPane getArea() {
        return area;
    }
    /**
     * 
     * @return devuelve el label
     */
    public JLabel GetLabel()
    {
        return lbl;
    }
    
    
}
