/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.proyecto_final;

/**
 *
 * @author borja
 */
public class Proyecto_Final {

    public static void main(String[] args) {
        Modelo m = new Modelo();
        Vista vent = new Vista(m);
        Controlador c = new Controlador(vent, m);
    }
}
