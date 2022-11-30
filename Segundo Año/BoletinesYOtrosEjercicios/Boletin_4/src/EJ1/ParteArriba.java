package EJ1;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ParteArriba extends JPanel {

	private ParteArriba1 ar1;
	private ParteArriba2 ar2;

	Modelo mod;

	public ParteArriba(Modelo m) {
		mod = m;

		this.setLayout(new GridLayout(2, 1));
		ar1 = new ParteArriba1();
		ar2 = new ParteArriba2(mod);

		add(ar1);
		add(ar2);

	}

	public void PonListener(ActionListener a) {
		ar2.PonListener(a);
	}

	public void PonDatos() {
		ar2.PonDatos();
	}
}
