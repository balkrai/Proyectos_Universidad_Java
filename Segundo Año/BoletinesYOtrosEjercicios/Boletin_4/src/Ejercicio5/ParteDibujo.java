package Ejercicio5;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ParteDibujo extends JPanel {

	private int radio;
	private Color c = Color.red;

	public ParteDibujo() {
		radio = 100;

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(c);
		g.fillOval(100, 50, radio, radio);
	}

	public void PonRadio(int num) {
		radio = num;
		this.repaint();
	}

	public void PonColor(Color c) {
		this.c = c;
		this.repaint();
	}

}
