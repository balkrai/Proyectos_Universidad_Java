/*
 * Clase que representa el modelo de la aplicacion, es decir, donde se haran las
 * operaciones pertinentes como  comprobar las letras etc.
 */
package com.mycompany.proyecto_final;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Miguel Vallet Comes, Borja Somovilla del Saz
 */
public class Modelo {

    private int tam_palabra, dificultad; //tamaño de palabras y dificultad
    private boolean daltonico; // indica si el modo daltonico esta activado
    private String palabra;  // palabra que se quiere averiguar
    private int contador;  // representa el intento por el que se va
    private ArrayList<Jugador> top = new ArrayList<>(); // Lista de los jugadores del rankingborja

    public Modelo() {
        JOptionPane.showMessageDialog(null, "Esta aplicacion permite jugar al wordle. Este popular juego trata de averiguar una palabra secreta poniendo las letras en las casillas que corresponden,\n si la letra esta en la posicion correcta el cuadrado estara en verde, \nsi la letra esta pero en la posicion incorrecta el cuadrado pasa a naranja,\n si la letra no esta el cuadrado se pondrá gris");
        EmpezarPartida();
        daltonico = false;
    }

    /**
     * funcion que ejecuta todas las intrucciones para comenzar partida
     */
    public void EmpezarPartida() {
        contador = 0;
        boolean validacion = true;//true es que esta mal false es que bien
        do {
            try {
                tam_palabra = Integer.parseInt(JOptionPane.showInputDialog("Jugador 1 introduzca el tamaño de palabra.(3-5)"));
            } catch (NumberFormatException e) {
                tam_palabra = Integer.parseInt(JOptionPane.showInputDialog("Error, tiene que ser un numero\nJugador 1 introduzca el tamaño de palabra."));
            }
            palabra = JOptionPane.showInputDialog("Jugador 1 introduzca la palabra.");

            dificultad = Integer.parseInt(JOptionPane.showInputDialog("Introduzca la dificultad.(Max: 8)"));
            validacion = tam_palabra > 5 || tam_palabra < 3 || dificultad > 8 || dificultad < 1 || palabra.length() != tam_palabra;
            for (char s : palabra.toCharArray()) {
                if (Character.isDigit(s)) {
                    validacion = true;
                }

            }
        } while (validacion);
    }

    /**
     *
     * @param p1 palabra que ha insertado el usuario
     * @param p2 palabra correcta
     * @return vector de colores con los colores que se tienen que poner en las
     * casillas de la fila i
     */
    public ArrayList<Color> Comprueba(String p1, String p2) {
        ArrayList<Color> listaColores = new ArrayList<>();

        for (int i = 0; i < p1.length(); i++) {
            if (Character.toUpperCase(p1.charAt(i)) == Character.toUpperCase(p2.charAt(i))) {
                listaColores.add(Color.decode("#459525"));
            } else if (DiferentePosicion(i, Character.toUpperCase(p1.charAt(i)), p2)) {
                listaColores.add(Color.decode("#c8a111"));
            } else {
                listaColores.add(Color.decode("#474b4d"));
            }

        }

        return listaColores;
    }

    /**
     * funcion que nos dice si una letra esta pero en otra posicion
     *
     * @param pos posicion de la letra
     * @param letra letra que se quiere analizar
     * @param p2 string de la palabra 2
     * @return devuelve true si la letra esta pero en una posicion distinta
     */
    public boolean DiferentePosicion(int pos, char letra, String p2) {
        boolean esta = false;

        for (int i = 0; i < p2.length(); i++) {
            if (Character.toUpperCase(letra) == Character.toUpperCase(p2.charAt(i)) && i != pos) {
                esta = true;
            }
        }
        return esta;
    }

    /**
     *
     * @param p palabra intriducida por el usuario
     * @return devuelve true si la palabra coincide con la intriducida por el
     * usuario
     */
    public boolean PalabraAcertada(String p) {
        return palabra.equals(p);
    }

    /**
     * Devuelve el tamanyo de la palabra
     *
     * @return tamanyo de la palabra
     */
    public int getTam_palabra() {
        return tam_palabra;
    }

    /**
     * Devuelve la palabra que hay acertar
     *
     * @return palabra que hay que acertar
     */
    public String getPalabra() {
        return palabra;
    }

    /**
     * funcion que devuelve la dificultar
     *
     * @return dificultar
     */
    public int getDificultad() {
        return dificultad;
    }

    /**
     * funcion que aumenta en 1 el contador de intentos
     */
    void AumentarContador() {
        contador++;
    }

    /**
     * funcion que devuelve el intento por el que se va
     *
     * @return intento
     */
    public int getContador() {
        return contador;
    }

    /**
     * permite añadir a la lista del ranking un jugador
     *
     * @param j jugador a ser añadido
     */
    public void AnyadirTop(Jugador j) {
        top.add(j);
    }

    /**
     * devuelve la lista del ranking del jugadores
     *
     * @return lista del ranking
     */
    public ArrayList<Jugador> getTop() {
        return top;
    }

    /**
     * devuelve el estado del modo daltonico
     *
     * @return true si esta activado y false sino
     */
    public boolean isDaltonico() {
        return daltonico;
    }

    /**
     * cambia la variable booleana al contrario
     */
    public void CambiarDaltonico() {
        this.daltonico = !daltonico;
    }

}
