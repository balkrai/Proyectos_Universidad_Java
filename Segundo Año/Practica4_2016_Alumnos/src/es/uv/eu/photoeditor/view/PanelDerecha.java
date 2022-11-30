/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uv.eu.photoeditor.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Jose
 */
public class PanelDerecha extends JPanel{
private BufferedImage foto = null;
    public PanelDerecha() {
        setBorder(BorderFactory.createLineBorder(Color.darkGray));
    }
    
    @Override
    public void paintComponent(Graphics c)
    {
        super.paintComponent(c);
        c.drawImage(foto, 10, 10, this);
    }
    
     public void PonFoto(BufferedImage foto)       
    {
        this.foto = foto;
        this.repaint();
    }
     
    public void setMouseListener(MouseListener m)
    {
        addMouseListener(m);
    }
    
    public void pintar()
    {
        repaint();
    }
    
}
