package Ejercicio4;

public class Main {

	public static void main(String[] args) {

		Modelo m = new Modelo();
		Ventana v = new Ventana();
		v.setSize(400, 400);
		v.setTitle("Fernando Alonso");

		Controlador c = new Controlador(v, m);

		v.setVisible(true);
	}

}
