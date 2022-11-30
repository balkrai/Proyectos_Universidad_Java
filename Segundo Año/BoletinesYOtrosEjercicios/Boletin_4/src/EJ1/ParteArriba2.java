package EJ1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ParteArriba2 extends JPanel {

	private JTextField txt1, txt2;
	private JButton btn;

	Modelo mod;

	public ParteArriba2(Modelo m) {

		mod = m;
		this.setLayout(new FlowLayout());

		txt1 = new JTextField("", 10);
		txt2 = new JTextField("", 10);
		btn = new JButton("Añadir");

		add(txt1);
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(txt2);
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(btn);

	}

	public void PonListener(ActionListener a) {
		btn.addActionListener(a);

	}

	public void PonDatos() {
		mod.setDato1(txt1.getText());
		mod.setDato2(txt2.getText());
	}
}
