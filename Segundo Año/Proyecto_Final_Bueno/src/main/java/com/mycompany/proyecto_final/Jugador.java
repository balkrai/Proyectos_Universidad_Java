/**
 * Clase que representa un jugador donde se guardan el nombre y la puntuacion
 */
package com.mycompany.proyecto_final;

public class Jugador {

    private String nombre;  // nombre del jugador
    private Integer puntuacion; // puntuacion

    /**
     * Constructor de la clase Jugador
     *
     * @param n nombre del jugador
     * @param p puntuacion obtenida
     */
    public Jugador(String n, Integer p) {
        nombre = n;
        puntuacion = p;

    }

    /**
     * funcion getter de nombre
     *
     * @return nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * funcion setter de nombre
     *
     * @param nombre nombre del jugador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Funcion que permite obtener la puntuacion
     *
     * @return devuelve la puntuacion del usuario
     */
    public Integer getPuntuacion() {
        return puntuacion;
    }

    /**
     * Funcion para poner la puntuacion
     *
     * @param puntuacion nueva puntuacion
     */
    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Funcion que representa un jugador por pantalla
     *
     * @return el string con el nombre y la puntuacion del jugador
     */
    @Override
    public String toString() {
        return "Nombre: " + nombre + " " + "Puntuacion: " + puntuacion;
    }
}
