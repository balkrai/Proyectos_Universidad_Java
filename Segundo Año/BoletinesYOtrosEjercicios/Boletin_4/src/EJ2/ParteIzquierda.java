package EJ2;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ParteIzquierda extends JPanel {
	private ParteIz2 iz;

	public ParteIzquierda() {
		this.setLayout(new GridBagLayout());
		iz = new ParteIz2();
		add(iz);
	}
	
	public void PonActionListener(ActionListener a)
	{
		iz.PonActionListener(a);
	}
	
	
}
