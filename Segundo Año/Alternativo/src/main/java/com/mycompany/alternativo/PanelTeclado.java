/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alternativo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelTeclado extends JPanel {

    String letras = "QWERTYUIOPASDFGHJ!KLZXCVBNM+";
    ArrayList<JButton> botones = new ArrayList<>();

    public PanelTeclado() {
        this.setLayout(new GridLayout(3, 9));
        for (int i = 0; i < letras.length(); i++) {
            JButton boton = new JButton();
            boton.setPreferredSize(new Dimension(90, 50));

            if (letras.charAt(i) == '!') {
                boton.setText("Enter");
                boton.setActionCommand("Enter");

            } else if (letras.charAt(i) == '+') {
                boton.setText("Borrar");
                boton.setActionCommand("Borrar");

            } else {
                boton.setText("" + letras.charAt(i));
                boton.setActionCommand("" + letras.charAt(i));
            }
            add(boton);
            botones.add(boton);

        }

    }

    public void ColocaEventoBoton(ActionListener listener) {
        for (int i = 0; i < botones.size(); i++) {
            botones.get(i).addActionListener(listener);
        }
    }

    public void CambiaColorLetras(ArrayList<String> v_acertadas, ArrayList<String> v_NoEstan) {
        for (int i = 0; i < botones.size(); i++) {
            if (v_acertadas.contains(botones.get(i).getText())) {
                botones.get(i).setBackground(Color.decode("#459525"));
            } else if (v_NoEstan.contains(botones.get(i).getText())) {
                botones.get(i).setBackground(Color.DARK_GRAY);
            }
        }

    }

}
