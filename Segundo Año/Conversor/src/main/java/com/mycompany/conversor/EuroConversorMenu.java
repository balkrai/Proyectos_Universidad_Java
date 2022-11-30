/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.conversor;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class EuroConversorMenu extends JMenuBar {

    private JMenu Calculadora;
    private JMenuItem salir, changeRate;

    public EuroConversorMenu() {
        Calculadora = new JMenu("Calculadora");
        salir = new JMenuItem("Salir");
        salir.setActionCommand("salir");
        changeRate = new JMenuItem("Change Rate");
        changeRate.setActionCommand("changeRate");

        Calculadora.add(changeRate);
        Calculadora.add(salir);

        add(Calculadora);
    }

    public JMenuItem getSalir() {
        return salir;
    }

    public JMenuItem getChangeRate() {
        return changeRate;
    }

}
