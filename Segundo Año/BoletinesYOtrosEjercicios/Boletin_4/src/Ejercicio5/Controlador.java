package Ejercicio5;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controlador {

		Ventana vent;
		Modelo mod;
		
		public Controlador(Ventana v, Modelo m)
		{
			vent = v;
			mod = m;
			vent.PonEventoSlider(new SliderControlador()); 
			vent.PonComboEvento(new ComboListener());
		}
		
	class SliderControlador implements ChangeListener
	{

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider sl = (JSlider) e.getSource();
			mod.radio = sl.getValue();
			vent.PonRadio(mod.radio);
		}
		
	}
	
	class ComboListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> com = (JComboBox<String>) e.getSource();
			switch(com.getSelectedItem().toString())
			{
			case "Rojo":
				mod.setC(Color.red);
				break;
			case "Verde":
				mod.setC(Color.green);
				break;
			case "Azul":
				mod.setC(Color.blue);
				break;
				
			}
			
			vent.PonColor(mod.getC());
		}
		
	}
}
