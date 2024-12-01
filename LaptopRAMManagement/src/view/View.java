/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Item;
import utilities.Utilities;
import static utilities.Utilities.sc;

/**
 * This 
 * @author phamm
 */
public class View {

    /**
     * This method is used to show Main Menu.
     */
    public void showMenu() {
        System.out.println("======== LAPTOP RAM MANAGEMENT ========\n"
                + "1. Add an Item.\n"
                + "2. Search Item.\n"
                + "3. Update Item.\n"
                + "4. Delete Item.\n"
                + "5. Show all Items.\n"
                + "6. Quit.\n"
                + "=========================================");
    }

    /**
     *
     */
    public void showSubMenu() {
        System.out.println("----- SEARCH ITEM -----\n"
                + "1. Search by type.\n"
                + "2. Search by bus.\n"
                + "3. Search by brand.\n"
                + "4. Quit.\n"
                + "-----------------------");
    }

    /**
     * This method is used to
     *
     * @return true if users want to continue, false to go back.
     */
    public boolean askToContinue() {
        System.out.println("\nWould you like to continue or go back to the main menu?\n"
                + "(Y) to continue,\n"
                + "or press any key to go back to the main menu.");
        return (sc.nextLine().trim().toUpperCase().matches("Y"));
    }

    /**
     * 
     * @return 
     */
    public String inputType() {
        String type = Utilities.inputAString("Type: ");
        while (type.isEmpty()) {
            System.out.println("Item's type is blank! Input again!");
            type = Utilities.inputAString("Type: ");
        }
        return type;
    }
    
    /**
     * 
     * @return 
     */
    public String inputBus() {
        String bus = Utilities.inputAnInteger("Bus : ", 0) + "MHz";
        while (bus.equalsIgnoreCase("0MHz")) {
            System.out.println("Item's bus cannot equal 0MHz! Input again!");
            bus = Utilities.inputAnInteger("Bus: ", 0) + "MHz";
        }
        return bus;
    }

    /**
     * 
     * @return 
     */
    public String inputBrand() {
        String brand = Utilities.inputAString("Brand: ");
        while (brand.isEmpty()) {
            System.out.println("Item's brand is blank! Input again!");
            brand = Utilities.inputAString("Type: ");
        }
        return brand;
    }

    /**
     * 
     * @param value 
     */
    public void showFilter(String value) {
        System.out.println("|------------------------------------------------------|");
        System.out.printf("|     CODE     |   %-5s   | QUANTITY | PRODUCTIONDATE |\n", value);
        System.out.println("|------------------------------------------------------|");
    }

    /**
     * 
     * @param i 
     */
    public void showInfoByType(Item i) {
        System.out.printf("| %-12s |  %-6s   |   %-4d   |     %-7s    |\n",
                i.getCode(), i.getType(), i.getQuantity(), i.getProduction_month_year());
        System.out.println("|------------------------------------------------------|");
    }

    /**
     * 
     * @param i 
     */
    public void showInfoByBus(Item i) {
        System.out.printf("| %-12s |  %-7s  |   %-4d   |     %-7s    |\n",
                i.getCode(), i.getBus(), i.getQuantity(), i.getProduction_month_year());
        System.out.println("|------------------------------------------------------|");
    }

    /**
     * 
     * @param i 
     */
    public void showInfoByBrand(Item i) {
        System.out.printf("| %-12s | %-9s |   %-4d   |     %-7s    |\n",
                i.getCode(), i.getBrand(), i.getQuantity(), i.getProduction_month_year());
        System.out.println("|------------------------------------------------------|");
    }

    /**
     * 
     */
    public void showAllAttributes() {
        System.out.println("|----------------------------------------------------------------------------------|");
        System.out.println("|     CODE     |  TYPE  |   BUS   |   BRAND   | QUANTITY | PRODUCTIONDATE | ACTIVE |");
        System.out.println("|----------------------------------------------------------------------------------|");
    }

    /**
     * 
     * @param i 
     */
    public void showInfo(Item i) {
        System.out.printf("| %-12s | %-6s | %-7s | %-9s |   %-4d   |     %-7s    |  %-5b |\n",
                i.getCode(), i.getType(), i.getBus(), i.getBrand(), i.getQuantity(), i.getProduction_month_year(), i.isActive());
        System.out.println("|----------------------------------------------------------------------------------|");
    }
}
