package EJ1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador {

	Ventana v;
	Modelo m;

	public Controlador(Ventana ve, Modelo m) {
		v = ve;
		this.m = m;

		v.PonListener(new EventoBoton());
	}

	public class EventoBoton implements ActionListener {
		public EventoBoton() {

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			v.PonDatos();
			v.Concatena();
		}
	}
}
