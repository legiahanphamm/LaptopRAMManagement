/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TreeSet;
import utilities.Utilities;

/**
 *
 * @author phamm
 */
public class ItemModel {
    
    private TreeSet<Item> items;
    private String pathfile;
    public int size;
    
    /**
     * Default constructor.
     */
    public ItemModel() {
        this.items = new TreeSet<>((Item t1, Item t2) -> {
            int type = t1.getType().compareTo(t2.getType());
            if (type != 0)
                return type;
            
            int bus = t1.getBus().compareTo(t2.getBus());
            if (bus != 0)
                return bus;
            
            return t1.getBrand().compareTo(t2.getBrand());
        });
    }

    /**
     * ItemModel constructor
     * @param pathfile The file that store Item's list
     */
    public ItemModel(String pathfile) {
        this.items = new TreeSet<>((Item t1, Item t2) -> {
            int type = t1.getType().compareTo(t2.getType());
            if (type != 0)
                return type;
            
            int bus = t1.getBus().compareTo(t2.getBus());
            if (bus != 0)
                return bus;
            
            return t1.getBrand().compareTo(t2.getBrand());
        });
        this.pathfile = pathfile;
    }

    public TreeSet<Item> getItems() {
        return items;
    }
    
    /**
     * This method is used to input Item's information from the keyboard.
     * @return RAM Item that users have just input.
     */
    public Item inputItem() {
        Item i = new Item();
        
        i.setType(Utilities.inputAString("Type: "));
        i.setBus(Utilities.inputAnInteger("Bus speed of RAM: ", 0) + "MHz");
        i.setBrand(Utilities.inputAString("Brand: "));
        i.setQuantity(Utilities.inputAnInteger("Quantity: ", 0));
        i.setProduction_month_year(Utilities.inputMonthYear("Production Month: ", 0, 13)
                        + "/" + Utilities.inputMonthYear("Production Year: ", 0, 2025));
        
        return i;
    }
    
    /**
     * This method is used to add Item into list.
     * @param i RAMItem that you want to add into list.
     * @param index Item's index to generate code.
     * @return true if added successfully, otherwise false.
     */
    public boolean addItem(Item i, int index) {
        while (i.getType().isEmpty() || i.getBus().equalsIgnoreCase("0MHz") || i.getBrand().isEmpty() || i.getQuantity() == 0) {
            System.out.println("You have just input at least one blank information. Please input again!");
            i = inputItem();
        }
        i.setCode(Utilities.generateId(index, i.getType()));
        return (items.add(i));
    }
    
    /**
     * This method is used to search Item by Type/Bus/Brand.
     * @param inputValue Value to search that users input from the keyboard.
     * @param compareValue Item's value that used to compare with inputValue.
     * @return true if Item is found, otherwise false.
     */
    public boolean searchItem(String inputValue, String compareValue) {
        if (inputValue.equalsIgnoreCase(compareValue))
            return true;
        return false;
    }
    
    /**
     * This method is used to search Item by Type.
     * @param type Item's Type that users input from the keyboard to search.
     * @return The list of Item that each Item's type matches input value.
     */
    public ArrayList<Item> searchByType(String type) {
        ArrayList<Item> re = new ArrayList<>();
        for (Item item : items) {
            if(searchItem(type, item.getType()))
                re.add(item);
        }
        
        return re;
    }
    
     /**
     * This method is used to search Item by Bus.
     * @param bus Item's Bus that users input from the keyboard to search.
     * @return The list of Item that each Item's type matches input value.
     */
    public ArrayList<Item> searchByBus(String bus) {
        ArrayList<Item> re = new ArrayList<>();
        for (Item item : items) {
            if(searchItem(bus, item.getBus()))
                re.add(item);
        }
        return re;
    }
    
    /**
     * This method is used to search Item by Bus.
     * @param brand Item's Brand that users input from the keyboard to search.
     * @return The list of Item that each Item's type matches input value.
     */
    public ArrayList<Item> searchByBrand(String brand) {
        ArrayList<Item> re = new ArrayList<>();
        for (Item item : items) {
            if(searchItem(brand, item.getBrand()))
                re.add(item);
        }
        return re;
    }
    
    /**
     * This method is used to search Item by Code.
     * @param code Item's Code that users input from the keyboard to search.
     * @return Item that code is found, otherwise null.
     */
    public Item searchByCode(String code) {
        for (Item item : items) {
            if(searchItem(code, item.getCode()))
                return item;
        }
        return null;
    }
    
    /**
     * This method is used to update Item's information by Code.
     * @param code Valid Item's code that users input from the keyboard to update Item's information.
     * @return Current Item with new information.
     */
    public Item updateItem(String code) {
        Item u = searchByCode(code);
        
        if(u != null && u.isActive()) {
            System.out.println("Found Item " + code);
            System.out.println("Please update Item's information. Press Enter key on any field that you don't want to change.");
            
            Item x = inputItem();

            if (!x.getType().isEmpty()) {
                if (!u.getType().equalsIgnoreCase(x.getType())) {
                    u.setType(x.getType());
                    u.setCode(Utilities.generateId(Utilities.getIndexCode(code), u.getType()));
                }
            }
            u.setBus(x.getBus().equalsIgnoreCase("0MHz") ? u.getBus() : x.getBus());
            u.setBrand(x.getBrand().isEmpty() ? u.getBrand() : x.getBrand());
            u.setQuantity(x.getQuantity()== 0 ? u.getQuantity(): x.getQuantity());
            return u;
        } else {
            System.out.println("Not found Item " + code);
            return null;
        }
    }
    
    /**
     * This method is used to delete Item by Code.
     * @param code Item's Code that users input from the keyboard to search.
     * @return true if code is found and deleted successfully, otherwise false.
     */
    public boolean deleteItem(String code) {
        for (Item item : items) {
            if (searchItem(code, item.getCode())) {
                item.setActive();
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method is used to store the list of RAM items into a binary file (RAMModules.dat) to preserve data between sessions.
     * @return true if saved successfully, otherwise false.
     */
    public boolean saveToFile() {
        boolean status = false;
        try {
            //Stream output
            FileOutputStream fos = new FileOutputStream(this.pathfile);

            //Create an Object Output Stream
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //Write Object's data
            for (Item i : items) {
                oos.writeObject(i);
            }
            
            //Close
            oos.close();
            fos.close();
            status = true;
        } catch (FileNotFoundException fnf) {
            System.out.println("Not found file!");
        } catch (IOException ioe) {
            System.out.println("Error while writing Object's data!");
        }
        return status;
    }
    
    /**
     * This method is used to load data from the RAMModules.dat file at the start of the program to ensure continuity.
     * @return true if loaded successfully, otherwise false.
     */
    public boolean loadFromFile() {
        boolean status = false, loopMore = true;
        try {
            //Stream input
            FileInputStream fis = new FileInputStream(this.pathfile);

            //Create an Object Input Stream
            ObjectInputStream ois = new ObjectInputStream(fis);

            //Read data from file
            try {
                while (loopMore) {
                    Item i = (Item) ois.readObject();
                    items.add(i);  // Add each product read from the file
                    this.size++;
                }
            } catch (EOFException eof) {
            }     
            ois.close();
            fis.close();
            status = true;
        } catch (FileNotFoundException fnf) {
            System.out.println("Not found file!");
        } catch (IOException ioe) {
            System.out.println("Error while reading data!");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Not Found Class!");
        }
        return status;
    }
}