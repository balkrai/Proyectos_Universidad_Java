package Ejercicio4;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class Controlador {

	private Ventana vent;
	private Modelo m;
	JCheckBox aux;

	public Controlador(Ventana v, Modelo m) {
		vent = v;
		this.m = m;
		v.PonChecListener(new CheckControlador());

	}

	class CheckControlador implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox box = (JCheckBox) e.getSource();

			Color c = DevuelveColor(box.getText());

			if (e.getStateChange() == ItemEvent.SELECTED) {

				if (!m.Comprueba()) {
					m.PonColor(c);

				} else {
					box.setSelected(false);
				}
			}

			else if (e.getStateChange() == ItemEvent.DESELECTED) {

				m.QuitaColor(c);

			}

			if (m.getActivos() == 0)
				vent.EnviaColores(Color.white, Color.white);
			else
				vent.EnviaColores(m.getC1(), m.getC2());

		}

		public Color DevuelveColor(String c) {
			Color color = null;

			switch (c) {
			case "Rojo":
				color = Color.red;
				break;
			case "Verde":
				color = Color.green;
				break;
			case "Azul":
				color = Color.blue;
				break;
			}

			return color;
		}

	}
}
