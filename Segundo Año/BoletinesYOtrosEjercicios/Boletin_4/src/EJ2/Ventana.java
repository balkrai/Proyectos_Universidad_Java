package EJ2;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Ventana extends JFrame {
	private ParteCentro centro;
	private ParteAbajo abajo;
	Modelo mod;

	public Ventana(Modelo m) {
		mod = m;
		this.setLayout(new BorderLayout()); // Definimos el layout
		centro = new ParteCentro(mod);
		abajo = new ParteAbajo();
		
		this.add(centro, "Center");
		add(abajo, "South");
	}
	
	public void PonActionListener(ActionListener a)
	{
		centro.PonActionListener(a);
		abajo.PonActionListener(a);
	}
	
	public void PontTexto()
	{
		centro.PontTexto();
		
	}

}
