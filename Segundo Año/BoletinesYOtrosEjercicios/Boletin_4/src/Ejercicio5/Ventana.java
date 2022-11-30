package Ejercicio5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Ventana extends JFrame {

	private ParteArriba par;
	private ParteDibujo abajo;
	private Container c;
	private Modelo m;

	public Ventana(Modelo mod) {
		this.setLayout(new BorderLayout());
		this.setSize(400, 300);
		
		m = mod;

		par = new ParteArriba();
		c = getContentPane();
		abajo = new ParteDibujo();
		
		c.add(abajo);

		add(par, "North");
		
	}
	
	public void PonEventoSlider(ChangeListener e)
	{
		par.PonEventoSlider(e);
	}
	
	public void PonComboEvento(ActionListener e)
	{
		par.PonComboEvento(e);
	}
	
	public void PonRadio(int num)
	{
		abajo.PonRadio(num);
	}
	
	public void PonColor(Color c)
	{
		abajo.PonColor(c);
	}

}
