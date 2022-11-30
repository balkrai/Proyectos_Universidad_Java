package EJ2;

public class Main {

	public static void main(String[] args) {
		Modelo m = new Modelo();		
		Ventana v = new Ventana(m);	
		Controlador c = new Controlador(v, m);
		v.setVisible(true);
		v.setTitle("Fernando alonso");
		v.setSize(450, 300);
		
		
	}
}
