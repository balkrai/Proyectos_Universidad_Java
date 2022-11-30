package Ejercicio4;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.CompoundBorder;

public class ParteArriba extends JPanel {

	private JCheckBox r1, r2, r3;

	public ParteArriba() {
		this.setLayout(new GridLayout(1, 3));

		this.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Colores"),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		r1 = new JCheckBox("Azul");
		r2 = new JCheckBox("Rojo");
		r3 = new JCheckBox("Verde");

		r1.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

		add(r1);
		add(r2);
		add(r3);

	}

	public void PonChecListener(ItemListener a) {
		r1.addItemListener(a);
		r2.addItemListener(a);
		r3.addItemListener(a);
	}

}
