/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alternativo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

public class Modelo {

    private String Palabra;
    private int Fila, Columna, tamanyo, dificultad, intentos_actuales;
    private Color Verde = Color.decode("#459525");
    private Color Amarillo = Color.decode("#c8a111");
    private Color Gris = Color.DARK_GRAY;
    private ArrayList<String> Acertadas;
    private ArrayList<String> NoEstan;
    Map<String,Integer> Jugadores;
    private boolean Acabada;
    private int Tam_Letras;
    

    public Modelo() {
        Jugadores =  new TreeMap();
       
        IniciaPartida();
    }
    
    public void IniciaPartida()
    {
        FormularioInicial f = new FormularioInicial(this);
        f.setVisible(true);
        Fila = 0;
        Acertadas = new ArrayList<>();
        Columna = 0;
        NoEstan = new ArrayList<String>();
        intentos_actuales = 0;
      
        Acabada = false;
    }
    
    public void SetTamLetra(int t)
    {
        Tam_Letras = t;
    }
    
    public int GetTamLetras()
    {
        return Tam_Letras;
    }
    
    public boolean PartidaAcabada()
    {
        return Acabada;
    }
    
    public void PonAcabada(boolean a)
    {
        Acabada = a;
    }
    
    public Map<String,Integer> DevuelveJugadores()
    {
        Map<String,Integer> res = new LinkedHashMap<>();
        
      for (int i = 0; i <= 30; i++) {
        for (Map.Entry<String, Integer> entry : Jugadores.entrySet()) {
          if (entry.getValue() == i) {
            res.put(entry.getKey(), entry.getValue());
          }
      }
    }
      return res;
    }
    
    public void PonJugador(String nombre,int Puntuacion)
    {
        Jugadores.put(nombre,Puntuacion);
    }

    public void AumentaIntentos() {
        intentos_actuales++;
    }

    public int GetIntentos() {
        return intentos_actuales;
    }

    public String getPalabra() {
        return Palabra;
    }

    public void setPalabra(String palabra) {
        Palabra = palabra;
    }

    public int getFila() {
        return Fila;
    }

    public void setFila(int fila) {
        Fila += fila;
    }

    public int getColumna() {
        return Columna;
    }

    public void setColumna(int valor) {
        if (Columna >= 0 && valor > 0) {
            Columna += valor;
        } else if (valor < 0 && Columna != 0) {
            Columna--;
        }
        System.out.println(Columna);
    }

    public int getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(int tamanyo) {
        this.tamanyo = tamanyo;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public ArrayList<Color> Comprueba(String p1) {

        ArrayList<Color> listaColores = new ArrayList<>();
        Acertadas = new ArrayList<>();
        p1.toUpperCase();

        for (int i = 0; i < p1.length(); i++) {

            if (p1.charAt(i) == Palabra.charAt(i)) {

                listaColores.add(Color.decode("#459525"));
                Acertadas.add("" + p1.charAt(i));

            } else if (Palabra.indexOf(p1.charAt(i)) == -1) {

                listaColores.add(Color.decode("#474b4d"));
                NoEstan.add("" + p1.charAt(i));

            } else {

                listaColores.add(Color.decode("#c8a111"));

            }

        }
        return listaColores;

    }

    public void ReseteaColumna() {
        Columna = 0;
    }

    public void PonCol(int c) {
        Columna = c;
    }

    public ArrayList<String> getAcertadas() {
        return Acertadas;
    }

    public ArrayList<String> getNoEstan() {
        return NoEstan;
    }

    public int DevuelvePosLibre(Cuadrado[] v_cuadrados, int PosActual) {
        int pos = -1;
        boolean salir = false;
        if (Acertadas.size() < Palabra.length() - 1) {
            for (int i = PosActual; i < v_cuadrados.length && !salir; i++) {

                if (v_cuadrados[i].GetColor().getRGB() != Verde.getRGB() && i != PosActual) {
                    salir = true;
                    pos = i;
                }
            }

        } else {
            for (int i = 0; i < v_cuadrados.length && !salir; i++) {

                if (v_cuadrados[i].GetColor().getRGB() != Verde.getRGB()) {
                    salir = true;
                    pos = i;
                }
            }
        }

        return pos;
    }

}
