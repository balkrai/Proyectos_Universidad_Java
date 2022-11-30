package com.mycompany.conversor;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author EU (2016)
 */
public class EuroConversorController {

    private EuroConversorModel model;
    private EuroConversorView view;
    private String mostrar = "0";

    public EuroConversorController(EuroConversorModel model, EuroConversorView view) {
        this.model = model;
        this.view = view;

        view.addWindowListener(new EuroConversorWindowListener());
        view.setActionListener(new EuroConversorControllerActionListener());
        view.PonRbListener(new RadioButtonListener());
        
    }

    class EuroConversorWindowListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println(" EuroConversorController : Cerrar ventana.");
            System.exit(0);
        }
    }

    class EuroConversorControllerActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = ae.getActionCommand();
            //System.out.println(command);
            String rate;
            switch (command) {
                case "salir":
                    System.out.println(" EuroConversorController : Boton ’ Salir ’. ");
                    System.exit(0);
                    break;
                case "changeRate":
                    System.out.println(" EuroConversorController : cambiar ratio");
                    rate = JOptionPane.showInputDialog("Nuevo ratio");
                    model.setExchangeRate(Integer.parseInt(rate));
                    view.setRate(rate);
                    break;
                case "0":
                    System.out.println(command);
                    model.addDigit(command);
                    view.setTexto(model.getNumero());
                    break;
                case "1":
                    System.out.println(command);
                    model.addDigit(command);
                    view.setTexto(model.getNumero());
                    break;
                case "2":
                    model.addDigit(command);
                    view.setTexto(model.getNumero());

                    break;
                case "3":
                    model.addDigit(command);
                    view.setTexto(model.getNumero());

                    break;
                case "4":
                    model.addDigit(command);
                    view.setTexto(model.getNumero());

                    break;
                case "5":
                    model.addDigit(command);
                    view.setTexto(model.getNumero());

                    break;
                case "6":
                    model.addDigit(command);
                    view.setTexto(model.getNumero());

                    break;
                case "7":
                    model.addDigit(command);
                    view.setTexto(model.getNumero());

                    break;
                case "8":
                    model.addDigit(command);
                    view.setTexto(model.getNumero());

                    break;
                case "9":
                    model.addDigit(command);
                    view.setTexto(model.getNumero());

                    break;
                case ".":
                    model.addDigit(command);
                    view.setTexto(model.getNumero());

                    break;
                case "C":
                    model.addDigit(command);
                    view.setTexto(model.getNumero());

                    break;
                case "convert":
                    
                    view.setTexto(mostrar);
                    
                    break;
                case "clear":
                    model.reset();
                    view.setTexto(model.getNumero());
                    break;
                default:
                    System.out.println(" PeliculaController : Comando ’" + command + "’ no reconocido.");
                    break;
            }
        }
    }
    class RadioButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton rb = (JRadioButton) e.getSource();
           
            
            if (rb.getActionCommand().equals("euro_dolar"))
                mostrar = model.convertdiv();
            else
            {
                mostrar = model.convertmult();
            }
        }
        
    }

}
