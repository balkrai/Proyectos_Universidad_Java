/*
 *  Panel del juego que contiene una matriz
 */
package com.mycompany.proyecto_final;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Borja Somovilla del saz Jose Miguel vallet Comes
 */
public class PanelCuadrados extends JPanel {

    private int tam_palabra, dificultad; // tamaño de la palabra e intentos 
    private Cuadrado[][] cuadrados; //matriz tantas filas como intentos y tantas columnas como tamaño de la palabra

    /**
     * constructor de la clase Panelcuadrado
     *
     * @param dificultad numero de intentos
     * @param tam_palabra tamaño de la palabra
     */
    public PanelCuadrados(int dificultad, int tam_palabra) {
        this.tam_palabra = tam_palabra;
        this.dificultad = dificultad;
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        cuadrados = new Cuadrado[dificultad][5];
        setLayout(new GridLayout(dificultad, 5, 3, 3));
        for (int i = 0; i < dificultad; i++) {
            for (int j = 0; j < tam_palabra; j++) {
                Cuadrado a = new Cuadrado();
                cuadrados[i][j] = a;
                this.add(a);
            }
        }

    }

    /**
     * funcion que permite modificar el tamaño de las letras
     *
     * @param n nuevo tamaño de las letras
     */
    public void CambiarTamLetras(int n) {
        for (int i = 0; i < dificultad; i++) {
            for (int j = 0; j < tam_palabra; j++) {
                cuadrados[i][j].getArea().setFont(new Font("Serif", Font.BOLD, n));
            }
        }
    }

    /**
     * funcion que permite habilitar las filas o bloquearlas
     *
     * @param fila fila que se quiere habilitar o desabilitar
     * @param b estado al que se le quiere poner
     */
    public void CambiarEstado(int fila, boolean b) {
        for (int i = 0; i < tam_palabra; i++) {
            cuadrados[fila][i].getArea().setEditable(b);
        }
    }

    /**
     * funcion que permite poner los keylistener a los textarea
     *
     * @param listener listener que se quiere poner
     */
    public void PonKeyListener(KeyListener listener) {
        for (int i = 0; i < dificultad; i++) {
            for (int j = 0; j < tam_palabra; j++) {
                cuadrados[i][j].getArea().addKeyListener(listener);

            }
        }
        this.addKeyListener(listener);
    }

    /**
     * funcion que recoge las letras dada una fila si alguna casilla esta vacia
     * se le añade un espacio para poder comparar
     *
     * @param fila fila de la que se quiere recoger las letras
     * @return string formado a partir de las letras
     */
    public String RecogerLetras(int fila) {
        String palabra = "";
        for (int i = 0; i < tam_palabra; i++) {
            if (cuadrados[fila][i].getArea().getText().isBlank()) {
                palabra += " ";
            } else if (cuadrados[fila][i].getArea().getText().length() > 0) {
                palabra += cuadrados[fila][i].getArea().getText().substring(0, 1);
                cuadrados[fila][i].getArea().setText("" + palabra.charAt(palabra.length() - 1));
            } else {
                palabra += cuadrados[fila][i].getArea().getText();
            }
        }
        palabra = palabra.replace("\t", "");
        palabra = palabra.replace("\n", "");

        return palabra;
    }

    /**
     * funcion que permite cambiar el color a una fila especifica teniendo en
     * cuenta si el modo daltonico esta activado
     *
     * @param lista lista de colores que se quiere aplicar
     * @param fila fila a la que se le quiere aplicar
     * @param daltonico boolean que indica si el modo daltonico esta activado
     */
    public void EnviaColores(ArrayList<Color> lista, int fila, boolean daltonico) {
        for (int i = 0; i < tam_palabra; i++) {
            cuadrados[fila][i].getArea().setBackground(lista.get(i));

            if (daltonico) {
                if (lista.get(i).getRGB() == Color.decode("#459525").getRGB()) {
                    cuadrados[fila][i].GetLabel().setText("Acertado");
                } else if (lista.get(i).getRGB() == Color.decode("#c8a111").getRGB()) {
                    cuadrados[fila][i].GetLabel().setText("Otra posicion");
                } else {
                    cuadrados[fila][i].GetLabel().setText("Fallo");
                    cuadrados[fila][i].GetLabel().setForeground(Color.white);

                }

            }
            cuadrados[fila][i].GetLabel().setOpaque(true);
            cuadrados[fila][i].GetLabel().setBackground(lista.get(i));

        }

    }

    /**
     * funcion que mueve las letras acertadas de una fila a otra
     *
     * @param fila fila a la que se quieren mover las letras
     * @param daltonico booleano que indica si el modo daltonico esta activo
     */
    public void MueveAcertados(int fila, boolean daltonico) {
        for (int i = 0; i < tam_palabra; i++) {
            if (cuadrados[fila - 1][i].getArea().getBackground().getRGB() == Color.decode("#459525").getRGB()) {
                cuadrados[fila][i].getArea().setBackground(Color.decode("#459525"));
                cuadrados[fila][i].getArea().setText(cuadrados[fila - 1][i].getArea().getText());
                cuadrados[fila][i].getArea().setEditable(false);

                if (daltonico) {
                    cuadrados[fila][i].GetLabel().setText("Acertado");
                }
                cuadrados[fila][i].GetLabel().setOpaque(true);
                cuadrados[fila][i].GetLabel().setBackground(Color.decode("#459525"));

            }
        }
    }

    /**
     * funcion que permite moverse entre las casillas de una fila de forma
     * circular teniendo en cuenta que pueden haber casillas acertadas, es
     * decir, si hay alguna acertada el focus en vez de saltar a esa saltara a
     * la siguiente que no este acertada
     *
     * @param fila fila actual
     * @param baja boolean que permite mover el focus cuando se esta cambiando
     * de fila a la primera casilla disponible
     */
    public void CambiaFocus(int fila, boolean baja) {
        int cont = 0;
        boolean cambiado = false;
        if (!baja) {
            for (int i = 0; i < tam_palabra; i++) {
                if (cuadrados[fila][i].getArea().hasFocus()) {
                    if (i == tam_palabra - 1) {
                        if (cuadrados[fila][0].getArea().getBackground().getRGB() != Color.decode("#459525").getRGB()) {
                            cuadrados[fila][0].getArea().grabFocus();
                        } else {
                            while (cont < tam_palabra && !cambiado) {
                                if (cuadrados[fila][cont].getArea().getBackground().getRGB() != Color.decode("#459525").getRGB()
                                        && !cuadrados[fila][cont].getArea().hasFocus()) {
                                    cuadrados[fila][cont].getArea().grabFocus();
                                    cambiado = true;
                                }
                                cont++;
                            }
                        }
                    } else {
                        if (cuadrados[fila][i + 1].getArea().getBackground().getRGB() != Color.decode("#459525").getRGB()) {
                            cuadrados[fila][i + 1].getArea().grabFocus();
                        } else {
                            while (cont < tam_palabra && !cambiado) {
                                if (cuadrados[fila][cont].getArea().getBackground().getRGB() != Color.decode("#459525").getRGB()
                                        && !cuadrados[fila][cont].getArea().hasFocus()) {
                                    cuadrados[fila][cont].getArea().grabFocus();
                                    cambiado = true;
                                }
                                cont++;
                            }
                        }
                    }
                }
            }
        } else {
            while (cont < tam_palabra && !cambiado) {
                if (cuadrados[fila][cont].getArea().getBackground().getRGB() != Color.decode("#459525").getRGB()) {
                    cuadrados[fila][cont].getArea().grabFocus();
                    cambiado = true;

                }
                cont++;
            }
        }
    }
}
