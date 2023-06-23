/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alternativo;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author borja
 */
public class MenuTareas extends JMenuBar{
    
     private JMenu menu;
     private JMenuItem Ranking, EmpezarPartida, ReglasYAutores, salir;
    public MenuTareas()
    {
        menu = new JMenu("Tareas Adicionales");
        Ranking = new JMenuItem("Ranking");
        EmpezarPartida = new JMenuItem("Empezar Partida");
        ReglasYAutores = new JMenuItem("Reglas Y autores");
        salir = new JMenuItem("Salir");

        Ranking.setActionCommand("Ranking");
        EmpezarPartida.setActionCommand("Comienza");
        ReglasYAutores.setActionCommand("ReglasAutores");
        salir.setActionCommand("Salir");
        
        
        menu.add(Ranking);
        menu.add(ReglasYAutores);
        menu.add(EmpezarPartida);
        menu.add(salir);
        this.add(menu);
    }
    
    
    public void PonOyente(ActionListener a)
    {
        Ranking.addActionListener(a);
        EmpezarPartida.addActionListener(a);
        ReglasYAutores.addActionListener(a);
        salir.addActionListener(a);
        
    }
    
}
