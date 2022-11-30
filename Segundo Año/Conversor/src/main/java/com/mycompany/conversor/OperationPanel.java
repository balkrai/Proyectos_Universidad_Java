package com.mycompany.conversor;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class OperationPanel extends JPanel {

    ButtonGroup gb;
    JRadioButton euro_dolar;
    JRadioButton dolar_euro;
    JButton convert;

    public OperationPanel() {
        //setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setLayout(new GridLayout(3, 1));
        gb = new ButtonGroup();
        dolar_euro = new JRadioButton("Dolar-Euro");
        dolar_euro.setSelected(true);
        dolar_euro.setBackground(Color.white);
        dolar_euro.setActionCommand("dolar_euro");
        dolar_euro.setFont(new Font("Arial", Font.PLAIN, 15));
        this.add(dolar_euro);
        gb.add(dolar_euro);
        euro_dolar = new JRadioButton("Euro-Dolar");
        euro_dolar.setBackground(Color.white);
        euro_dolar.setFont(new Font("Arial", Font.PLAIN, 15));
        euro_dolar.setActionCommand("euro_dolar");
        gb.add(euro_dolar);
        
        this.add(euro_dolar);
        convert = new JButton("Convert");
        convert.setActionCommand("convert");
        euro_dolar.setFont(new Font("Arial", Font.PLAIN, 15));
        convert.setForeground(Color.BLUE);
        this.add(convert);
    }

    public void setActionListener(ActionListener a) {
        convert.addActionListener(a);
    }

    public void setActionRadioButton(ActionListener a) {
        convert.addActionListener(a);
        dolar_euro.addActionListener(a);
        euro_dolar.addActionListener(a);
    }

    public JRadioButton getEuro_dolar() {
        return euro_dolar;
    }

    public JRadioButton getDolar_euro() {
        return dolar_euro;
    }
    
    public void PonRbListener(ActionListener a)
    {
        dolar_euro.addActionListener(a);
        euro_dolar.addActionListener(a);
        
    }

}
