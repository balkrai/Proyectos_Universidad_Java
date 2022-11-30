package EJ2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ParteDerecha extends JPanel {
	private String[] cadena = { "Opcion A", "Opcion B", "Opcion C" };
	private JComboBox combo;
	private JLabel lbl;

	public ParteDerecha() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints(); // Por cada componente
		GridBagConstraints c2 = new GridBagConstraints();
		c.gridx = 1; // Columna X fila Y
		c.gridy = 0;
		c2.gridx = 2;
		c2.gridy = 0;
		lbl = new JLabel("Lista: ");
		combo = new JComboBox(cadena);
		
		combo.setActionCommand("L1");
		add(lbl, c);
		add(combo, c2);
		
		
	}
	
	public void PonActionListener(ActionListener a)
	{
		combo.addActionListener(a);
	}
}