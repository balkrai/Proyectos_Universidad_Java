/*
 * Clase de la ventana principal
 */
package com.mycompany.proyecto_final;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;


/**
 *
 * @author borja Somovilla del Saz , Jose Miguel Vallet Comes
 */
public class Vista extends JFrame {

    private PanelCuadrados dibujo; //Panel del juego
    private Menu menu; //Menu superior
    private Modelo modelo;  // Modelo
    private KeyListener key_listener;  //listener de las teclas
    //private ActionListener action_listener;

    /**
     * constructor de la vista
     *
     * @param m modelo
     */
    public Vista(Modelo m) {
        modelo = m;
        menu = new Menu();
        dibujo = new PanelCuadrados(m.getDificultad(), m.getTam_palabra());
        add(dibujo);

        this.setSize(700, 500);
        this.setVisible(true);
        this.setJMenuBar(menu);
    }

    /**
     * funcion que pasa el tamaño de las letras al panel del juego
     *
     * @param n tamaño de la letra
     */
    public void CambiarTamLetras(int n) {
        dibujo.CambiarTamLetras(n);
    }

    /**
     * funcion que permite iniciar partida
     */
    public void EmpezarPartida() {
        modelo.EmpezarPartida();
        this.getContentPane().remove(dibujo);
        dibujo = new PanelCuadrados(modelo.getDificultad(), modelo.getTam_palabra());
        CambiarEstado(0, true);
        PonKeyListener(key_listener);
        add(dibujo);
        repaint();

        this.setSize(getWidth() + 1, getHeight() + 1);
        this.setSize(getWidth() - 1, getHeight() - 1);
    }

    /**
     * funcion que pasa el listener de los botones al menu
     *
     * @param a listener
     */
    public void setActionListener(ActionListener a) {
        //action_listener = a;
        menu.setActionListener(a);
    }

    /**
     * funcion que envia el numero de fila y el estado al que se quiere poner
     *
     * @param fila numero de fila
     * @param b estado al que se quiere poner
     */
    public void CambiarEstado(int fila, boolean b) {

        dibujo.CambiarEstado(fila, b);
    }

    /**
     * funcion que envia los listener de las teclas
     *
     * @param listener listener de las teclas
     */
    public void PonKeyListener(KeyListener listener) {
        key_listener = listener;
        dibujo.PonKeyListener(listener);
    }

    /**
     * funcion que permite recoger las letras de la clase dibujo (panel de
     * juego)
     *
     * @param fila fila de donde se quieren recoger las letras
     * @return string resultante
     */
    public String RecogerLetras(int fila) {

        return dibujo.RecogerLetras(fila);
    }

    /**
     * funcion que envia los colores al panel de juego
     *
     * @param lista lista de colores
     * @param fila fila a donde se envia
     */
    public void EnviaColores(ArrayList<Color> lista, int fila) {
        dibujo.EnviaColores(lista, fila, modelo.isDaltonico());
    }

    /**
     * funcion que envia la fila a donde se quieren mover los aciertos
     *
     * @param fila fila a donde se quieren mover
     */
    public void MueveAcertados(int fila) {
        dibujo.MueveAcertados(fila, modelo.isDaltonico());
    }

    /**
     * funcion que manda los datos a la funcion que permite cambiar el focus con
     * el tabulador
     *
     * @param fila fila actual
     * @param c booleano por si se han movido y hay que buscar en la nueva fila
     */
    public void CambiaFocus(int fila, boolean c) {
        dibujo.CambiaFocus(fila, c);
    }

}
