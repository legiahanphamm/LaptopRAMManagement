/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rammanagementsystem;

import controller.ItemController;
import model.ItemModel;
import view.View;

/**
 * This is the main class.
 * @author phamm
 */
public class ItemManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String pathfile = "src/RAMModules.dat";
        ItemModel model = new ItemModel(pathfile);
        View ui = new View();
        
        ItemController controller = new ItemController(model, ui);
        controller.test();
        controller.run();
    }
}
