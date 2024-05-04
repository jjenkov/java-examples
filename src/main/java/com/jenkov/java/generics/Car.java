package com.jenkov.java.generics;

public class Car extends Vehicle {

    protected int passengerCapacity = 0;

    public Car(int weight, int length, int width, int passengerCapacity) {
        super(weight, length, width);
        this.passengerCapacity = passengerCapacity;
    }

    public Car(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

}
