/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alternativo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

public class Ventana extends JFrame {
	private PanelDibujo dibujo;
	private PanelTeclado teclado;
	private Modelo modelo;
        private MenuTareas menu;
        ActionListener evento;

	public Ventana(Modelo m) {
		modelo = m;
		dibujo = new PanelDibujo(modelo.getDificultad(), modelo.getTamanyo(), modelo);
                menu = new MenuTareas();
		this.setLayout(new BorderLayout());
		JTextArea tx = new JTextArea();
		AbstractDocument doc = (AbstractDocument) tx.getDocument();
		teclado = new PanelTeclado();
		doc.setDocumentFilter(new DocumentFilter());
                this.getContentPane().add(dibujo);
                
		add(dibujo, "Center");
		add(teclado, "South");
               menu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                setJMenuBar(menu);

		this.setSize(800, 800);
		this.setVisible(true);
	}
        
    
        
        public void IniciaPartida() {
        modelo.IniciaPartida();
        this.getContentPane().remove(dibujo);
        this.getContentPane().remove(teclado);

        dibujo = new PanelDibujo(modelo.getDificultad(), modelo.getTamanyo(),modelo);
        teclado = new PanelTeclado();

        ColocaEventoBoton(evento);
        add(dibujo, "Center");
	add(teclado, "South");
        repaint();

        this.setSize(getWidth() + 1, getHeight() + 1);
        this.setSize(getWidth() - 1, getHeight() - 1);
    }

	public void ColocaEventoBoton(ActionListener listener) {
                evento = listener;
		teclado.ColocaEventoBoton(listener);
	}

	public void ColocaLetra(String letra, int fila, int columna) {
		dibujo.ColocaLetra(letra, fila, columna);

	}

	public void CambiaColorLetras(ArrayList<String> v_acertadas, ArrayList<String> v_NoEstan) {
		teclado.CambiaColorLetras(v_acertadas, v_NoEstan);

	}

	public void BorraCasilla(int fila, int columna) {
		dibujo.BorraCasilla(fila, columna);

	}

	public String ObtenerLetras(int fila) {
		return dibujo.ObtenerLetras(fila);
	}

	public void EnviaColores(ArrayList<Color> v, int f) {
		dibujo.EnviaColores(v, f);

	}

	public void MoverAcertados(int f1, int f2) {
		dibujo.MoverAcertados(f1, f2);
	}
        
        public void PonOyenteMenu(ActionListener a)
        {
            menu.PonOyente(a);
        }

}