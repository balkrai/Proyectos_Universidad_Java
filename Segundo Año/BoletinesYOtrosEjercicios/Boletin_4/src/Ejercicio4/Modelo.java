package Ejercicio4;

import java.awt.Color;

public class Modelo {

	private Color c1, c2;
	private int Activos = 0;

	public Modelo() {
		c1 = null;
		c2 = null;

	}

	public boolean Comprueba() {

		return Activos == 2;
	}

	public void PonColor(Color c) {
		if (Activos == 0) {
			c1 = c;
			c2 = c1;
			Activos++;

		} else if (Activos == 1) {
			c2 = c;
			Activos++;
		}

	}

	public void QuitaColor(Color c) {
		if (c == c1) {
			c1 = c2;

			Activos--;
		} else if (c2 == c) {
			c2 = c1;
			Activos--;
		}

	}

	public Color getC1() {

		return c1;
	}

	public Color getC2() {

		return c2;
	}

	public int getActivos() {
		return Activos;
	}

	public void setActivos(int activos) {
		Activos += activos;
	}

}
