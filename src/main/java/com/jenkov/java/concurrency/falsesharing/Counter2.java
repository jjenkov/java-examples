package com.jenkov.java.concurrency.falsesharing;

public class Counter2 {

    public volatile long count1 = 0;
    public volatile byte[] bytes1 = new byte[128];
    public volatile byte[] bytes2 = new byte[128];
    public volatile long count2 = 0;

}
