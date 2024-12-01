/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author phamm
 */
public class Item implements Serializable {
    private String code;
    private String type;
    private String bus;
    private String brand;
    private int quantity;
    private String production_month_year;
    private boolean active;

    public Item() {
        this.active = true;
    }

    public Item(String code, String type, String bus, String brand, int quantity, String production_month_year) {
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.production_month_year = production_month_year;
        this.active = true;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduction_month_year() {
        return (production_month_year);
    }

    public void setProduction_month_year(String production_month_year) {
        this.production_month_year = production_month_year;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive() {
        this.active = false;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %d, %s, %b", code, type, bus, brand, quantity, production_month_year, active);
    }
}
