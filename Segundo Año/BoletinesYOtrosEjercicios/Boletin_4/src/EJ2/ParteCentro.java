package EJ2;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ParteCentro extends JPanel {
	private ParteIzquierda izquierda;
	private ParteDerecha derecha;
	private JLabel lbl;
	
	Modelo mod;

	public ParteCentro(Modelo m) {
		this.setLayout(new GridLayout(1, 3));
		
		mod = m;
		
		lbl = new JLabel();
		lbl.setBorder(new EmptyBorder(0, 40, 10, 0));
		
		
		izquierda = new ParteIzquierda();
		derecha = new ParteDerecha();
		add(izquierda);
		add(lbl);
		add(derecha);
	}
	
	public void PonActionListener(ActionListener a)
	{
		izquierda.PonActionListener(a);
		derecha.PonActionListener(a);
	}
	
	public void PontTexto()
	{
		lbl.setText(mod.getDato());
	}
}
