package com.jenkov.java.concurrency.falsesharing;


@jdk.internal.vm.annotation.Contended
public class Counter3 {

    public volatile long count1 = 0;
    //padding bytes

    public volatile long count2 = 0;
    //padding bytes

    public volatile long count3 = 0;
    //padding bytes

}
