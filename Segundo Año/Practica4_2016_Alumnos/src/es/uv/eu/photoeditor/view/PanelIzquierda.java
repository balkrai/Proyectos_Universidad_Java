/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uv.eu.photoeditor.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

/**
 *
 * @author borja
 */
public class PanelIzquierda extends JPanel{
 private PanelColor Color1;
 private PanelColor Color2;
 
 public PanelIzquierda()
 {
     Color1 = new PanelColor(1);
     Color1.setBorder(new CompoundBorder(
    BorderFactory.createLineBorder(Color.GRAY), 
    BorderFactory.createEmptyBorder(30,10,10,10)));
     Color2 = new PanelColor(2);
     Color2.setBorder(new CompoundBorder(
    BorderFactory.createLineBorder(Color.GRAY), 
    BorderFactory.createEmptyBorder(30,10,10,10)));
     
     this.setLayout(new GridLayout(2,1));
     add(Color1);
    
     add(Color2);
 }
    
 
  public void setActionEvent(ActionListener a)
    {
        Color1.setActionEvent(a);
        Color2.setActionEvent(a);

    }
    
    
}
