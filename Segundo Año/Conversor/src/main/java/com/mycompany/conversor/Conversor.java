/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.conversor;


/**
 *
 * @author 
 */
public class Conversor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EuroConversorModel model = new EuroConversorModel();
        EuroConversorView view = new EuroConversorView();
        EuroConversorController controller = new EuroConversorController(model, view);

        view.setVisible(true);
    }
}
    