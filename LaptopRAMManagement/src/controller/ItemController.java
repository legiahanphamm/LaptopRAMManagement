/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Item;
import model.ItemModel;
import utilities.Utilities;
import view.View;

/**
 * This is the control class.
 * @author phamm
 */
public class ItemController {

    private ItemModel model;
    private View ui;

    public ItemController(ItemModel model, View ui) {
        this.model = model;
        this.ui = ui;
    }

    public void run() {
        System.out.println("YOUR ACTIVE RAMITEM LIST");
        model.loadFromFile();
        showList();
        int choice = 0;
        ArrayList<Item> re = new ArrayList<>();

        do {
            ui.showMenu();
            choice = Utilities.inputAnInteger("Please choose an option (from 1 to 6): ", 0);

            switch (choice) {
                //Add an item
                case 1: {
                    do {
                        System.out.println("You have chosen Option 1: Add an Item.");
                        Item i = model.inputItem();
                        if (model.addItem(i, ++model.size)) {
                            System.out.println("Added successfully.");
                            System.out.println("AFTER ADDING, ACTIVE ITEM LIST IS");
                            showList();
                        } else {
                            System.out.println("Added unsuccessfully.");
                        }
                    } while (ui.askToContinue());
                    break;
                }

                //Search Item
                case 2: {
                    do {
                        if (model.getItems().isEmpty()) {
                            System.out.println("Empty set. Please add at least one Item into list!");
                        } else {
                            System.out.println("You have chosen Option 2: Search Item.");
                            ui.showSubMenu();
                            int op = Utilities.inputAnInteger("Please choose an option to search: ", 0);

                            String value = "";

                            switch (op) {
                                //Search by Type
                                case 1: 
                                    System.out.println("You have chosen 2.1. Search by type.");

                                    value = ui.inputType();

                                    re = model.searchByType(value);
                                    if (re.isEmpty())
                                        System.out.println("Not found " + value + " type!");
                                    else {
                                        ui.showFilter("TYPE");
                                        for (Item item : re) {
                                            ui.showInfoByType(item);
                                        }
                                    }
                                    break;
                                

                                //Search by Bus
                                case 2: {
                                    System.out.println("You have chosen 2.2. Search by bus.");

                                    value = ui.inputBus();

                                    re = model.searchByBus(value);
                                    if (re.isEmpty())
                                        System.out.println("Not found " + value + " bus!");
                                    else {
                                        ui.showFilter("BUS");
                                        for (Item item : re) {
                                            ui.showInfoByBus(item);
                                        }
                                    }
                                    break;
                                }

                                //Search by Brand
                                case 3: {
                                    System.out.println("You have chosen 2.3. Search by brand.");

                                    value = ui.inputBrand();

                                    re = model.searchByBrand(value);
                                    if (re.isEmpty())
                                        System.out.println("Not found " + value + " brand!");
                                    else {
                                        ui.showFilter("BRAND");
                                        for (Item item : re) {
                                            ui.showInfoByBrand(item);
                                        }
                                    }
                                    break;
                                }

                                //Cancel
                                case 4: {
                                    System.out.println("Go back to the main menu..");
                                }

                                default:
                                    System.out.println("ERROR!");
                            }
                        }
                    } while (ui.askToContinue());
                    break;
                }

                //Update Item
                case 3: {
                    do {
                        if (model.getItems().isEmpty()) {
                            System.out.println("Empty set. Please add at least one Item into list!");
                        } else {
                            System.out.println("You have chosen Option 3: Update Item.");
                            String code = Utilities.inputItemCode("Please input Item's code (RAMtype_x): ");
                            Item u = model.updateItem(code);
                            if (u!= null) {
                                showItem(u);
                                System.out.println("Updated successfully.");
                            } else {
                                System.out.println("Updated unsuccessfully.");
                            }
                        }
                    } while (ui.askToContinue());
                    break;
                }

                //Delete Item
                case 4: {
                    do {
                        if (model.getItems().isEmpty()) {
                            System.out.println("Empty set. Cannot delete anymore!");
                        } else {
                            System.out.println("You have chosen Option 4: Delele Item.");
                            String code = Utilities.inputItemCode("Please input Item's code (RAMtype_x): ");

                            if (model.deleteItem(code)) {
                                System.out.println("Deleted successfully.");
                                System.out.println("AFTER REMOVING, ACTIVE ITEM LIST IS");
                                if (model.getItems().isEmpty())
                                    System.out.println("You have just deleted the last Item!");
                                else {
                                    showList();
                                }
                            } else {
                                System.out.println("Not found code!");
                            }
                        }
                    } while (ui.askToContinue());
                    break;
                }

                //Show all List
                case 5: {
                    showList();
                    break;
                }

                //Exit
                case 6: {
                    model.saveToFile();
                    System.out.println("Your list is saved.");
                    System.out.println("Thank you. See you again!");
                    break;
                }

                default:
                    System.out.println("ERROR!");
            }
        } while (choice != 6);
    }
    
    public void test() {
        model.addItem(new Item("RAMDDR5_1", "DDR5", "3000MHz", "Intel", 1, "9/2023"), 1);
        model.addItem(new Item("RAMLPDDR5_2", "LPDDR5", "3200MHz", "Intel", 1, "7/2022"), 2);
        model.addItem(new Item("RAMDDR3_3", "DDR3", "2800MHz", "Lenovo", 3, "3/2024"), 3);
        model.addItem(new Item("RAMDDR5_4", "DDR5", "4000MHz", "HP", 2, "12/2023"), 4);
        model.addItem(new Item("RAMLPDDR4_5", "LPDDR4", "3200MHz", "Asus", 1, "5/2024"), 5);
    }
    
    /**
     * This method is used to show the List of all active RAMItems.
     */
    public void showList() {
        ui.showAllAttributes();
        for (Item item : model.getItems()) {
            if(item.isActive())
                ui.showInfo(item);
        }
        System.out.println("");
    }
    
    /**
     * This method is used to show Item's information.
     * @param i Item that users want to show information.
     */
    public void showItem(Item i) {
        ui.showAllAttributes();
        ui.showInfo(i);
        System.out.println("");
    }
}
