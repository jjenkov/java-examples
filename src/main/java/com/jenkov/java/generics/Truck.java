package com.jenkov.java.generics;

public class Truck extends Vehicle {

    protected int loadCapacity = 0;

    public Truck(int weight, int length, int width, int loadCapacity) {
        super(weight, length, width);
        this.loadCapacity = loadCapacity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}
