/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uv.eu.photoeditor.controller;

import es.uv.eu.photoeditor.model.PhotoEditorModel;
import es.uv.eu.photoeditor.view.PhotoEditorView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Jose
 */
public class PhotoEditorController {

    PhotoEditorView v;
    PhotoEditorModel m;

    public PhotoEditorController(PhotoEditorView v, PhotoEditorModel m) {
        this.v = v;
        this.m = m;
        this.v.setActionEvent(new BotonesListener());
        this.v.setSliderEvent(new SliderListener());
        this.v.setMouseListener(new RatonListener());

    }

    class SliderListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider sl = (JSlider) e.getSource();
            int r = sl.getValue();
            v.cambiarGrosor(r);
        }
    }

    class RatonListener implements MouseListener {
        private int x,y;
        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println("clickao");
            x = e.getX();
            y = e.getY();
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //System.out.println("Soltao");
            if(e.getButton()==MouseEvent.BUTTON1){
                m.pintaRectangulo(x, y, e.getX(), e.getY(), v.getGrosor(),
                    v.getColor2(), v.getColor1());
            }
            else if(e.getButton()==MouseEvent.BUTTON3)
            {
                m.pintaRectangulo(x, y, e.getX(), e.getY(), v.getGrosor(),
                    v.getColor1(), v.getColor1());
            }
            
            v.pintar();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        
        
    }

    class BotonesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();

            switch (comando) {
                case "salir":
                    System.out.println("Controller : Boton ’ Salir ’. ");
                    System.exit(0);
                    break;
                case "cargar":
                    JFileChooser j1 = new JFileChooser(FileSystemView.getFileSystemView());
                    j1.showSaveDialog(null);
                    m.loadImagen(new File(j1.getSelectedFile().getAbsolutePath()));
                    v.PonFoto(m.getImagen());
                    break;
                case "guardar":
                    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView());
                    j.showSaveDialog(null);
                    m.saveImagen(new File(j.getSelectedFile().getAbsolutePath()));
                    break;
                case "boton1":
                    JButton b = (JButton) e.getSource();
                    v.cambiarColor1(b.getBackground());
                    System.out.println(b.getText());
                    break;
                case "boton2":
                    JButton b2 = (JButton) e.getSource();
                    v.cambiarColor2(b2.getBackground());
                    System.out.println(b2.getText());
                    break;
                case "ayuda":
                    System.out.println("fdfdfd");
                    JOptionPane.showMessageDialog(null, "Esta aplicacion permite editar fotos UwU");
                    break;
            }
        }
    }

}
