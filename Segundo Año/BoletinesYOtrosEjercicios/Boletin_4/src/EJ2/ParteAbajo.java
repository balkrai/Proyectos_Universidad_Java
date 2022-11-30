package EJ2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ParteAbajo extends JPanel {
	ArrayList<JButton> botones = new ArrayList<JButton>();
	private int num_botones = 6;

	public ParteAbajo() {
		this.setLayout(new FlowLayout());
		GeneraBotones();
		for (int i = 0; i < botones.size(); i++) {
			this.add(botones.get(i));
			this.add(Box.createRigidArea(new Dimension(3, 0)));
		}
	}

	public void GeneraBotones() {
		for (int i = 1; i <= num_botones; i++) {
			JButton b = new JButton(String.valueOf(i));
			botones.add(b);
		}
	}
	
	public void PonActionListener(ActionListener a)
	{
		int num = 0;
		for (int i = 0; i < botones.size(); i++) {
			num = i+1;
			botones.get(i).setActionCommand("Boton "+num);
			botones.get(i).addActionListener(a);
		}
	}
}
