/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uv.eu.photoeditor.view;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {

    private JMenu archivo,ayuda;
    private JMenuItem salir, cargar,guardar,sobre;

    public Menu() {
        archivo = new JMenu("Archivo");
        ayuda = new JMenu("Ayuda");
        salir = new JMenuItem("Salir");
        salir.setActionCommand("salir");
        cargar = new JMenuItem("Cargar Imagen");
        cargar.setActionCommand("cargar");
        guardar = new JMenuItem("Guardar Imagen");
        guardar.setActionCommand("guardar");
       sobre = new JMenuItem("Sobre esta aplicacion");
       sobre.setActionCommand("ayuda");
       
        archivo.add(cargar);
        archivo.add(guardar);
        archivo.add(salir);
        ayuda.add(sobre);
        add(archivo);
        add(ayuda);
    }

    public JMenu getArchivo() {
        return archivo;
    }

    public JMenu getAyuda() {
        return ayuda;
    }

    public void setActionListener(ActionListener a){
        salir.addActionListener(a);
        guardar.addActionListener(a);
        cargar.addActionListener(a);
        sobre.addActionListener(a);
    }

}
