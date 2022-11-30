package Ejercicio5;

public class Main {

	public static void main(String[] args) {

		Modelo m = new Modelo();

		Ventana v = new Ventana(m);

		Controlador cont = new Controlador(v, m);
		v.setVisible(true);
	}

}
