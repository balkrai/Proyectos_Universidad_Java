package com.mycompany.conversor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class EuroConversorView extends JFrame {

    private DisplayPanel display;
    private EuroConversorMenu barra;
    private ONPanel onp;
    private ClearPanel cl;

    public EuroConversorView() {
        setLayout(new BorderLayout());
        this.setTitle("EuroConversor");
        this.setSize(600, 400);
        this.setVisible(true);

        display = new DisplayPanel();
        barra = new EuroConversorMenu();
        onp = new ONPanel();
        cl = new ClearPanel();

        this.setJMenuBar(barra);

        add(display, BorderLayout.NORTH);
        add(onp, "Center");
        add(cl, "South");
    }

    public void setActionListener(ActionListener actionListener) {
        barra.getSalir().addActionListener(actionListener);
        barra.getChangeRate().addActionListener(actionListener);
        onp.setActionListener(actionListener);
        cl.setActionListener(actionListener);
    }

    public void setActionRadioButton(ActionListener a) {
        onp.setActionRadioButton(a);
    }

    public void setRate(String s) {
        display.setRt(s);
    }

    public void setTexto(String t) {
        display.setTexto(t);
    }

    public ONPanel getOnp() {
        return onp;
    }
    
    public void PonRbListener(ActionListener a)
    {
        onp.PonRbListener(a);
        
    }

}
