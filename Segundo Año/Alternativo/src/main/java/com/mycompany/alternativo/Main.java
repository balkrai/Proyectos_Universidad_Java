/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alternativo;

/**
 *
 * @author borja
 */
public class Main {
    public static void main(String[] args) {
        
		Modelo m = new Modelo();
		Ventana v = new Ventana(m);
		Controlador c = new Controlador(v, m);
                
	}
}
