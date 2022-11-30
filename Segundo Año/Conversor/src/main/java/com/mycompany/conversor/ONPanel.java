/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conversor;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author Jose
 */
public class ONPanel extends JPanel {

    OperationPanel op;
    NumerosPanel np;

    public ONPanel() {
        setLayout(new GridLayout(1, 2));
        np = new NumerosPanel();
        add(np);
        op = new OperationPanel();
        add(op);
    }

    public void setActionListener(ActionListener actionListener) {
        np.setActionListener(actionListener);
        op.setActionListener(actionListener);
    }

    public void setActionRadioButton(ActionListener a) {
        op.setActionRadioButton(a);
    }

    public OperationPanel getOp() {
        return op;
    }

    public void PonRbListener(ActionListener a)
    {
        op.PonRbListener(a);
        
    }
}
