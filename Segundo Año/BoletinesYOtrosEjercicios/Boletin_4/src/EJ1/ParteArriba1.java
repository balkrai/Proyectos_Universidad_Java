package EJ1;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ParteArriba1 extends JPanel {

	private JLabel lbl1;
	private JLabel lbl2;

	public ParteArriba1() {

		this.setLayout(new GridLayout(1, 2));

		lbl1 = new JLabel("Operador 1");
		lbl2 = new JLabel("Operador 2");

		lbl1.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 0));
		lbl2.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

		add(lbl1);
		add(lbl2);

	}
}
