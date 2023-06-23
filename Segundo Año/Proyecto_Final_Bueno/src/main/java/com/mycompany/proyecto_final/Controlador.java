/**
 * Esta clase representa el controlador de la aplicacion donde se han creado todos
 * los listeners que se usan.
 */
package com.mycompany.proyecto_final;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author borja Somovilla del saz y Jose miguel Vallet Comes
 */
public class Controlador {
    
    private Vista vista;  // vista donde se pondran los listeners
    private Modelo modelo; // modelo donde se hacen  las operaciones

    /**
     *
     * @param v recibe la vista para poder poner los listeners
     * @param m recibe el modelo
     */
    public Controlador(Vista v, Modelo m) {
        vista = v;
        modelo = m;
        v.CambiarEstado(0, true);
        v.PonKeyListener(new TeclaListener());
        v.setActionListener(new BotonesListener());
    }

    /**
     * Clase para los listeners de los botones
     */
    public class BotonesListener implements ActionListener {

        /**
         *
         * @param e evento que ha ocurrido
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            System.out.println(command);
            switch (command) {
                case "Top":
                    ArrayList<Jugador> lista = modelo.getTop();
                    lista.sort((j1, j2) -> j1.getPuntuacion().compareTo(j2.getPuntuacion()));
                    String top = "";
                    for (int i = 0; i < lista.size() && i < 9; i++) {
                        top += lista.get(i) + "\n";
                    }
                    JOptionPane.showMessageDialog(null, top);
                    break;
                case "jg1":
                    vista.EmpezarPartida();
                    break;
                case "letra":
                    try {
                    int tam_letra = Integer.parseInt(JOptionPane.showInputDialog("Inserte el tamaño de letra deseado"));
                    vista.CambiarTamLetras(tam_letra);
                } catch (NumberFormatException x) {
                    System.err.println(x);
                    JOptionPane.showMessageDialog(null, "El input es un numero", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
                case "Creditos":
                    JOptionPane.showMessageDialog(null, "Hecho por Don Borja Somovilla y El Ilustre Jose Miguel Vallet");
                    break;
                case "Ayuda":
                    JOptionPane.showMessageDialog(null, "Esta aplicacion permite jugar al wordle. Este popular juego trata de averiguar una palabra secreta poniendo las letras en las casillas que corresponden,\n si la letra esta en la posicion correcta el cuadrado estara en verde, \nsi la letra esta pero en la posicion incorrecta el cuadrado pasa a naranja,\n si la letra no esta el cuadrado se pondrá gris");
                    break;
                case "daltonicos":
                    modelo.CambiarDaltonico();
                    break;
                case "Salir":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no soportada");
                    break;
            }
        }
    }

    /**
     * Clase para los listeners de las teclas
     */
    public class TeclaListener implements KeyListener {
        
        private String palabra;
        private ArrayList<Color> listaColor;
        String texto = "";
        
        @Override
        public void keyTyped(KeyEvent e) {
        }

        /**
         *
         * @param e evento de la tecla
         */
        @Override
        public void keyPressed(KeyEvent e) {
            JTextPane pane = (JTextPane) e.getSource();
            
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ENTER:
                    palabra = vista.RecogerLetras(modelo.getContador());
                    listaColor = modelo.Comprueba(palabra, modelo.getPalabra());
                    vista.EnviaColores(listaColor, modelo.getContador());
                    vista.CambiarEstado(modelo.getContador(), false);
                    modelo.AumentarContador();
                    if (modelo.PalabraAcertada(palabra)) {
                        for (int i = 0; i < modelo.getDificultad(); i++) {
                            vista.CambiarEstado(i, false);
                        }
                        try {
                            Jugador j = new Jugador("", 0);
                            j.setNombre(JOptionPane.showInputDialog("Inserte nombre del jugador"));
                            j.setPuntuacion(modelo.getContador());
                            modelo.AnyadirTop(j);
                            String ruta = new File("").getAbsolutePath();
                            Desktop.getDesktop().open(new File(ruta + "\\volveria.mp4"));
                        } catch (Exception j) {
                            JOptionPane.showMessageDialog(null, "Error con el archivo "
                                    + "de felicitacion");
                        }
                    } else if (modelo.getContador() == modelo.getDificultad()&& !modelo.PalabraAcertada(palabra)) {
                        for (int i = 0; i < modelo.getDificultad(); i++) {
                            vista.CambiarEstado(i, false);
                        }
                        try {
                            Jugador j = new Jugador("", 0);
                            j.setNombre(JOptionPane.showInputDialog("Inserte nombre del jugador"));
                            j.setPuntuacion(modelo.getContador());
                            modelo.AnyadirTop(j);
                            String ruta = new File("").getAbsolutePath();
                            Desktop.getDesktop().open(new File(ruta + "\\vegeta.mp4"));
                        } catch (Exception j) {
                            JOptionPane.showMessageDialog(null, "Error con el archivo "
                                    + "de felicitacion");
                        }
                    } else {
                        vista.CambiarEstado(modelo.getContador(), true);
                        vista.MueveAcertados(modelo.getContador());
                        vista.CambiaFocus(modelo.getContador(), true);
                    }
                    
                    break;
                case KeyEvent.VK_TAB:
                    pane.setText(pane.getText().replace("\t", ""));
                    if (pane.getText().length() > 1) {
                        pane.setText(pane.getText().substring(0, pane.getText().length() - 1));
                    }
                    vista.CambiaFocus(modelo.getContador(), false);
                    break;
                default:
                    texto = pane.getText();
                    if (texto.length() > 0) {
                        pane.setText(texto.substring(0, texto.length() - 1));
                    }
                    break;
            }
            
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
        }
        
    }
    
}
