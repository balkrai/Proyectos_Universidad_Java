package Ejercicio5;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ParteArriba extends JPanel {

	private JSlider barra;
	private JLabel lbl1, lbl2;
	private JComboBox box;
	String[] lista = { "Rojo", "Verde", "Azul" };

	public ParteArriba() {
		this.setLayout(new GridLayout(2, 2));

		this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		barra = new JSlider();
		lbl1 = new JLabel("Color Pincel");
		lbl2 = new JLabel("Radio");
		box = new JComboBox<>(lista);

		barra.setValue(100);

		add(lbl1);
		add(box);
		add(lbl2);
		add(barra);

	}

	public void PonEventoSlider(ChangeListener e) {
		barra.addChangeListener(e);
	}

	public void PonComboEvento(ActionListener e) {
		box.addActionListener(e);
	}
}
