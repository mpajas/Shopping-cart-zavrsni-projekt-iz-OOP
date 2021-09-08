package com.company;

public abstract class Product {
    String name;
    Double price;
    String origin;
    String expiry;

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getOrigin() {
        return origin;
    }

    public String getExpiry() {
        return expiry;
    }

    @Override
    public String toString() {
        StringBuffer display = new StringBuffer();
        display.append("---- " + getName() + " ----\n");
        display.append("Price: " + getPrice() + "\n");
        display.append("Origin: " + getOrigin() + "\n");
        display.append("Expiring: " + getExpiry());
        return display.toString();
    }

}
