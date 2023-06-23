/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alternativo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Controlador {

    private Modelo modelo;
    private Ventana vista;

    public Controlador(Ventana v, Modelo m) {
        vista = v;
        modelo = m;
        vista.ColocaEventoBoton(new EventoBoton());
        vista.PonOyenteMenu(new EventoBotonesMenu());

    }

    class EventoBoton implements ActionListener {

        private String palabra;
        private String Nombre;

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            System.out.println(boton.getText());
            if (!modelo.PartidaAcabada()) {
                if (!boton.getText().equals("Enter") && !boton.getText().equals("Borrar")
                        && modelo.getColumna() < modelo.getTamanyo()) {
                    vista.ColocaLetra(boton.getText(), modelo.getFila(), modelo.getColumna());

                } else if (boton.getText().equals("Borrar")) {

                    modelo.setColumna(-1);
                    vista.BorraCasilla(modelo.getFila(), modelo.getColumna());

                } else if (boton.getText().equals("Enter")) {
                    modelo.AumentaIntentos();
                    palabra = vista.ObtenerLetras(modelo.getFila());
                    ArrayList<Color> v_colores = modelo.Comprueba(palabra);
                    vista.EnviaColores(v_colores, modelo.getFila());
                    if (!palabra.equals(modelo.getPalabra()) && modelo.GetIntentos() < modelo.getDificultad()) {
                        modelo.setFila(1);
                        modelo.ReseteaColumna();
                        vista.MoverAcertados(modelo.getFila() - 1, modelo.getFila());
                        vista.CambiaColorLetras(modelo.getAcertadas(), modelo.getNoEstan());
                    } else {
                        modelo.PonAcabada(true);

                        if (modelo.GetIntentos() == modelo.getDificultad() && modelo.getAcertadas().size() < modelo.getPalabra().length()) {
                            File fich = new File("Derrota.png");
                            Nombre = JOptionPane.showInputDialog("Inserte Nombre del jugador");
                            modelo.PonJugador(Nombre, modelo.GetIntentos());

                            PanelFinPartida fin = new PanelFinPartida(fich);

                        } else {
                            File fich = new File("Victoria.jpg");
                            Nombre = JOptionPane.showInputDialog("Inserte Nombre del jugador");
                            modelo.PonJugador(Nombre, modelo.GetIntentos());
                            PanelFinPartida fin = new PanelFinPartida(fich);

                        }
                    }

                }

            }
        }

    }

    public class EventoBotonesMenu implements ActionListener {

        private String res_ranking = "";

        @Override
        public void actionPerformed(ActionEvent e) {
            String codigo = e.getActionCommand().toString();

            switch (codigo) {
                case "Ranking":
                    res_ranking = "Ranking: \n";
                    Map<String, Integer> res = modelo.DevuelveJugadores();
                    for (Map.Entry<String, Integer> entrada : res.entrySet()) {
                        res_ranking += entrada.getKey() + " " + entrada.getValue() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, res_ranking);
                    break;
                case "Comienza":
                    vista.IniciaPartida();
                    vista.repaint();
                    break;
                case "ReglasAutores":
                    
                    JOptionPane.showMessageDialog(null, "Aplicación realizada por <Autor 1> y <Autor 2> \n Este juego consiste en, dada una palabra por el jugador 1 el jugador 2 tiene que intentar averiguarla poniendo las letras en las casillas"
                            + "\n Las casillas se pondran de diversos colores dependiendo si se ha acertado "
                            + "\n Verde: La letra se ha acertado y esta en la posicion que toca \n Naranja: La letra esta en la palabra pero no en la posicion que toca \n Gris: La letra no esta en la palabra");

                    break;
                case "Salir":
                    System.exit(0);
                    break;
                
            }
        }

    }

}
