package com.jenkov.java.switchexp;

public class SwitchExamples {

    public static void main(String[] args) {

        int val = Integer.parseInt("123");

        switch(val) {
            case 123 : {
                System.out.println("123");
                break;
            }

            default: {
                System.out.println("Default");
            }
        }

        String result = switch(val) {
            case 123 -> "123-1234";

            default -> "Default-Default";
        };

        System.out.println(result);

    }
}
