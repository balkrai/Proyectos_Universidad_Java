/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uv.eu.photoeditor.view;

import es.uv.eu.photoeditor.model.PhotoEditorModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Jose
 */
public class PhotoEditorView extends JFrame {

    private Menu m;
    private Norte n;
    private PanelIzquierda pi;
    private Sur s;
    private PanelDerecha d;
    private PhotoEditorModel modelo;

    public PhotoEditorView(PhotoEditorModel modelo) {
        setLayout(new BorderLayout());
        n = new Norte();
        m = new Menu();
        d = new PanelDerecha();
        this.modelo = modelo;
        pi = new PanelIzquierda();
        s = new Sur();
        add(n, BorderLayout.NORTH);
        add(pi, "West");
        add(s, "South");
        setJMenuBar(m);

        add(n, "North");

        add(d,"Center");
        s.cambiarColor1(Color.BLACK);
        s.cambiarColor2(Color.WHITE);
    }
    public void setActionEvent(ActionListener a)
    {
        m.setActionListener(a);
        pi.setActionEvent(a);

        
    }
    public void PonFoto(BufferedImage foto)       
    {
        d.PonFoto(foto);
    }

    public void setSliderEvent(ChangeListener a)
    {
        n.setJSliderListener(a);
    }
    public void setMouseListener(MouseListener m)
    {
        d.setMouseListener(m);
    }
    public void cambiarGrosor(int x){
        s.cambiarGrosor(x);
    }
    
    public void cambiarColor1(Color c){
        s.cambiarColor1(c);
    }
    public void cambiarColor2(Color c){
        s.cambiarColor2(c);
    }

    public int getGrosor() {
        return s.getGrosor();
    }

    public Color getColor1() {
        return s.getColor1();
    }

    public Color getColor2() {
        return s.getColor2();
    }
    public void pintar()
    {
        d.pintar();
    }
    
}
