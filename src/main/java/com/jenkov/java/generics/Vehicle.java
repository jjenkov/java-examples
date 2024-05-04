package com.jenkov.java.generics;

public class Vehicle {

    protected int weight;
    protected int length;
    protected int width;


    public Vehicle() {}

    public Vehicle(int weight, int length, int width) {
        this.weight = weight;
        this.length = length;
        this.width = width;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
