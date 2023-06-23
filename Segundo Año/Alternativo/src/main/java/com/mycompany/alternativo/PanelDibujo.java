/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alternativo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PanelDibujo extends JPanel {

    private Cuadrado[][] cuadrados;
    int dificultad, tam_palabra;
    Modelo mod;

    public PanelDibujo(int dificultad, int tam_palabra, Modelo m) {
        mod = m;
        cuadrados = new Cuadrado[dificultad][tam_palabra];
        this.dificultad = dificultad;
        this.tam_palabra = tam_palabra;
                this.setOpaque(true);

    }

    public void paintComponent(Graphics c) {
        super.paintComponent(c);
        int posX = 60;
        int posY = 30;

        for (int i = 0; i < dificultad; i++) {
            for (int j = 0; j < tam_palabra; j++) {

                Cuadrado cuad = new Cuadrado(posX, posY, c, "", Color.gray, mod.GetTamLetras());

                cuadrados[i][j] = cuad;

                posX += 70;

            }
            posX = 60;
            posY += 70;
        }
    }

    public void ColocaLetra(String letra, int fila, int columna) {

        int ejeX = cuadrados[fila][columna].getPos_X();
        int ejeY = cuadrados[fila][columna].getPos_Y();
        System.out.println("Posicion del Modelo" + mod.getColumna());
        if (cuadrados[fila][columna].GetColor().getRGB() != Color.decode("#459525").getRGB()) {
            cuadrados[fila][columna] = new Cuadrado(ejeX, ejeY, getGraphics(), letra, Color.white, mod.GetTamLetras());
            mod.setColumna(1);
        } else {
            int aux = columna;
            columna = mod.DevuelvePosLibre(cuadrados[fila], columna);
            ejeX = cuadrados[fila][columna].getPos_X();
            ejeY = cuadrados[fila][columna].getPos_Y();
            cuadrados[fila][columna] = new Cuadrado(ejeX, ejeY, getGraphics(), letra, Color.white, mod.GetTamLetras());
            mod.setColumna((columna - aux) + 1);

        }
    }

    public void BorraCasilla(int fila, int columna) {
        int ejeX = cuadrados[fila][columna].getPos_X();
        int ejeY = cuadrados[fila][columna].getPos_Y();

        if (cuadrados[fila][columna].GetColor().getRGB() != Color.decode("#459525").getRGB() && columna != -1) {
            cuadrados[fila][columna] = new Cuadrado(ejeX, ejeY, getGraphics(), "", Color.gray, mod.GetTamLetras());
        }

    }

    public String ObtenerLetras(int fila) {
        String palabra = "";
        for (int i = 0; i < cuadrados[fila].length; i++) {
            if (cuadrados[fila][i].GetLetra() == null || cuadrados[fila][i].GetLetra().equals("")) {
                palabra += " ";
            } else {
                palabra += cuadrados[fila][i].GetLetra();
            }
        }
        return palabra;
    }

    public void EnviaColores(ArrayList<Color> v, int f) {
        int ejeX = 0;
        int ejeY = 0;
        String letra = "";
        for (int i = 0; i < cuadrados[f].length; i++) {
            ejeX = cuadrados[f][i].getPos_X();
            ejeY = cuadrados[f][i].getPos_Y();
            letra = cuadrados[f][i].GetLetra();
            cuadrados[f][i] = new Cuadrado(ejeX, ejeY, getGraphics(), letra, v.get(i), mod.GetTamLetras());
        }

    }

    public void MoverAcertados(int f1, int f2) {
        int ejeX = 0;
        int ejeY = 0;
        String letra = "";

        for (int i = 0; i < cuadrados[f2].length; i++) {
            ejeX = cuadrados[f2][i].getPos_X();
            ejeY = cuadrados[f2][i].getPos_Y();

            if (cuadrados[f1][i].GetColor().getRGB() == Color.decode("#459525").getRGB()) {
                cuadrados[f2][i] = new Cuadrado(ejeX, ejeY, getGraphics(), cuadrados[f1][i].GetLetra(),
                        Color.decode("#459525"), mod.GetTamLetras());
            }
        }
    }

}
