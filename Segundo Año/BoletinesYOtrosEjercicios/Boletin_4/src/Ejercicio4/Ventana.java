package Ejercicio4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JFrame;

public class Ventana extends JFrame {

	private PanelDibujo pd;
	private Container cont;
	private ParteArriba arriba;

	public Ventana() {
		this.setLayout(new BorderLayout());
		pd = new PanelDibujo();
		cont = getContentPane();
		arriba = new ParteArriba();

		cont.add(pd);

		add(arriba, "North");

	}

	public void PonChecListener(ItemListener a) {
		arriba.PonChecListener(a);
	}

	public void EnviaColores(Color c1, Color c2) {
		pd.EnviaColores(c1, c2);
	}

}
