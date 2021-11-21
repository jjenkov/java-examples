package com.jenkov.java.concurrency.falsesharing;


public class Counter4 {

    @jdk.internal.vm.annotation.Contended("group1")
    public volatile long count1 = 0;

    @jdk.internal.vm.annotation.Contended("group1")
    public volatile long count2 = 0;

    @jdk.internal.vm.annotation.Contended("group2")
    public volatile long count3 = 0;

}
