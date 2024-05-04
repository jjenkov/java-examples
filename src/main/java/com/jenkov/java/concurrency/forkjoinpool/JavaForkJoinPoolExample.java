package com.jenkov.java.concurrency.forkjoinpool;

import java.util.concurrent.ForkJoinPool;

public class JavaForkJoinPoolExample {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool1 = ForkJoinPool.commonPool();
        ForkJoinPool forkJoinPool2 = new ForkJoinPool(4);

        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(123);
        forkJoinPool1.invoke(myRecursiveAction);

        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(123);
        //long result = forkJoinPool1.invoke(myRecursiveTask);

        //System.out.println("Result: "+ result);
        sleep(1000); // Make sure all System.out.print() reaches console before app stops.
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
