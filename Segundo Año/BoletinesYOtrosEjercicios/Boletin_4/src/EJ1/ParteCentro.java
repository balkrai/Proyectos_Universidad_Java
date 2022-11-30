package EJ1;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class ParteCentro extends JPanel {

	private JTextArea tx1;
	Modelo m;

	public ParteCentro(Modelo m) {
		this.m = m;
		this.setLayout(new FlowLayout());
		tx1 = new JTextArea(6, 30);

		tx1.setBorder(new LineBorder(Color.gray, 1));

		add(tx1);

	}

	public void Concatena() {
		tx1.append(m.getDato1() + "-" + m.getDato2());
	}
}
