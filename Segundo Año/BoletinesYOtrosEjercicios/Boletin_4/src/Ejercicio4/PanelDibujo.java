package Ejercicio4;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PanelDibujo extends JPanel {

	private Color c1, c2;

	public PanelDibujo() {
		c1 = Color.white;
		c2 = Color.white;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graficos2d = (Graphics2D) g;

		GradientPaint Gradiente = new GradientPaint(100, 130, c1, 100, 120, c2);
		graficos2d.setPaint(Gradiente);
		graficos2d.fillRect(100, 100, 100, 50);

	}

	public void EnviaColores(Color c1, Color c2) {
		this.c1 = c1;
		this.c2 = c2;
		this.repaint();

	}

}
