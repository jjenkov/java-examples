package com.jenkov.java.generics;

import java.util.ArrayList;
import java.util.List;
public class JavaGenericsExample1 {

    public static void man(String[] args) {
        List<Car> cars = new ArrayList<>();
        //cars.add(new Truck(3000, 800, 200, 5000));

        cars.add(new Car(1000, 400, 180, 5));
        cars.add(new Car(1200, 450, 182, 5));
        cars.add(new Car(1500, 480, 150, 7));

        int passengerCapacitySum = sumPassengerCapacityGenerics(cars);
        System.out.println("total passenger capacity: " + passengerCapacitySum);
    }

    public static int sumPassengerCapacity(List cars) {
        int passengerCapacitySum = 0;
        for(int i=0; i<cars.size(); i++){
            Car car = (Car) cars.get(i);
            passengerCapacitySum += car.getPassengerCapacity();
        }
        return passengerCapacitySum;
    }

    public static int sumPassengerCapacityGenerics(List<Car> cars) {
        int passengerCapacitySum = 0;
        for(int i=0; i<cars.size(); i++){
            Car car = cars.get(i);
            passengerCapacitySum += car.getPassengerCapacity();
        }
        return passengerCapacitySum;
    }

    public static int sumPassengerCapacityForEach(List cars) {
        int passengerCapacitySum = 0;
        for(Object o : cars){
            Car car = (Car) o;
            passengerCapacitySum += car.getPassengerCapacity();
        }
        return passengerCapacitySum;
    }

    public static int sumPassengerCapacityGenericsForEach(List<Car> cars) {
        int passengerCapacitySum = 0;
        for(Car car : cars){
            passengerCapacitySum += car.getPassengerCapacity();
        }
        return passengerCapacitySum;
    }







}
