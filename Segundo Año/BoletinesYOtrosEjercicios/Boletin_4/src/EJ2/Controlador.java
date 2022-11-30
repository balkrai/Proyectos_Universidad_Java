package EJ2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador {

	Ventana vent;
	Modelo mod;

	public Controlador(Ventana v, Modelo m) {
		vent = v;
		mod = m;

		vent.PonActionListener(new Accion());

	}

	public class Accion implements ActionListener {
		public Accion() {

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			mod.setDato(e.getActionCommand());
			vent.PontTexto();

		}
	}
}
