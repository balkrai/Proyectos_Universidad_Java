/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alternativo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cuadrado extends JPanel {

    Graphics c;
    private int pos_X, pos_Y;
    private JLabel lbl;
    String letra = null;
    Color color = Color.gray;
    private int Tam_letras;

    public Cuadrado(int i, int j, Graphics cu, String l, Color col, int tam) {
        pos_X = i;
        pos_Y = j;
        c = cu;
        letra = l;
        color = col;
        Tam_letras = tam;
        pinta();

    }

    public void pinta() {
        super.paintComponent(c);
        Stroke stroke1 = new BasicStroke(8f);
        Graphics2D g2d = (Graphics2D) c;
        if (letra == null) {
            c.drawRect(pos_X, pos_Y, 50, 50);
        } else if (color == null) {
            c.drawRect(pos_X, pos_Y, 60, 60);
            c.setFont(new Font("Verdana", Font.PLAIN, Tam_letras)); //30
            c.drawString(letra, pos_X + 20, pos_Y + 40);
        } else {

            g2d.setColor(Color.BLACK);
            g2d.setStroke(stroke1);
            g2d.drawRect(pos_X, pos_Y, 50, 50);
            c.setColor(color);
            c.fillRect(pos_X, pos_Y, 50, 50);
            c.setFont(new Font("Verdana", Font.PLAIN, Tam_letras));
            c.setColor(Color.BLACK);
            c.drawString(letra, pos_X + DevuelvePostX(), pos_Y + DevuelvePostY());
        }
        repaint();

    }

    public int DevuelvePostX() {
        if (Tam_letras <= 30) {
            return 15;
        } else {
            return 10;
        }

    }

    public int DevuelvePostY() {
        if (Tam_letras <= 30) {
            return 35;
        } else {
            return 45;
        }

    }

   

    public String GetLetra() {
        return letra;
    }

    public int getPos_X() {
        return pos_X;
    }

    public void setPos_X(int pos_X) {
        this.pos_X = pos_X;
    }

    public int getPos_Y() {
        return pos_Y;
    }

    public void setPos_Y(int pos_Y) {
        this.pos_Y = pos_Y;
    }

    public Color GetColor() {
        return color;

    }

}
