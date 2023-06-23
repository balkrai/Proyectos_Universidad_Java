/**
 * Funcion para representar el menu de la aplicacion que sera accesible desde
 * la ventana de juego
 */
package com.mycompany.proyecto_final;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Jose Miguel Vallet Comes, Borja Somovilla del Saz
 */
public class Menu extends JMenuBar {

    private JMenu menu;  //objeto menu que sea el nivel 1
    private JMenuItem top, jugador1, letra, ayuda, creditos, daltonicos,salir;  //objetos del menu nivel 2

    public Menu() {
        menu = new JMenu("Menu");
        top = new JMenuItem("Top");
        top.setActionCommand("Top");
        letra = new JMenuItem("Cambiar tamaño letra");
        letra.setActionCommand("letra");
        jugador1 = new JMenuItem("Ventana Jugador 1");
        jugador1.setActionCommand("jg1");
        daltonicos = new JMenuItem("Modo Daltonicos");
        daltonicos.setActionCommand("daltonicos");
        ayuda = new JMenuItem("Ayuda");
        ayuda.setActionCommand("Ayuda");
        creditos = new JMenuItem("Creditos");
        salir = new JMenuItem("Salir");
        salir.setActionCommand("Salir");
        menu.add(creditos);
        menu.add(ayuda);
        menu.add(letra);
        menu.add(jugador1);
        menu.add(top);
        menu.add(daltonicos);
        menu.add(salir);
        this.add(menu);
    }

    /**
     * funcion que permite poner los listeners a cada item del menu
     *
     * @param a nuevo listener
     */
    public void setActionListener(ActionListener a) {
        top.addActionListener(a);
        jugador1.addActionListener(a);
        letra.addActionListener(a);
        ayuda.addActionListener(a);
        creditos.addActionListener(a);
        daltonicos.addActionListener(a);
        salir.addActionListener(a);
    }
}
