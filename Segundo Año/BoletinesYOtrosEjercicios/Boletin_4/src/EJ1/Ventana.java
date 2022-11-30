package EJ1;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Ventana extends JFrame {

	private ParteArriba arriba;
	private ParteCentro centro;
	Modelo mod;

	public Ventana(Modelo m) {
		mod = m;
		this.setLayout(new BorderLayout());
		arriba = new ParteArriba(mod);
		centro = new ParteCentro(mod);

		add(arriba, BorderLayout.NORTH);
		add(centro, BorderLayout.CENTER);

	}

	public void PonDatos() {
		arriba.PonDatos();
	}

	public void PonListener(ActionListener a) {
		arriba.PonListener(a);
	}

	public void Concatena() {
		centro.Concatena();
	}
}
