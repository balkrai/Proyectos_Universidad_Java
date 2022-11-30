/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uv.eu.photoeditor.view;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author borja
 */
public class PanelColor extends JPanel{
    private Color [] listaColores = {Color.black,Color.cyan,Color.GRAY,Color.gray,Color.decode("#c0c0c0")
    ,Color.decode("#ff00ff"),Color.orange,Color.pink,Color.yellow,Color.white,Color.red,Color.blue,Color.green};  
  
    private String [] listaNombres = {"Negro","Cyan","Gris Oscuro","Gris","Gris claro","Magente","Naranja"
    ,"Rosa","Amarillo","Blanco","Rojo","Azul","Verde"}; 
    ArrayList <JButton> listaBotones = new ArrayList<>();
    public PanelColor(int num)
    {
        JLabel lbl = new JLabel("Color"+num);
        this.setLayout(new GridLayout(7,2,5,5));
        add(lbl);
        
        for(int i = 0; i < listaColores.length;i++)
        {
            JButton boton = new JButton(listaNombres[i]);
            boton.setBackground(listaColores[i]);
            boton.setActionCommand("boton"+num);
           
            if(listaColores[i] == Color.gray 
            || listaColores[i] == Color.black || listaColores[i] == Color.blue)
            {
                boton.setForeground(Color.white);
            }
            
            
            listaBotones.add(boton);
            add(boton);
            
        }
    }
    
     public void setActionEvent(ActionListener a)
    {
        for(int i = 0; i < listaBotones.size();i++)
        {
            listaBotones.get(i).addActionListener(a);
        }

    }
}
