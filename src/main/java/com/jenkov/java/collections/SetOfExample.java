package com.jenkov.java.collections;

import java.util.Set;

public class SetOfExample {

    public static void main(String[] args) {
        Set<String> strings = Set.of("123", "456", "789");

        System.out.println(strings);

        strings.add("123");

        System.out.println(strings);

    }

}
