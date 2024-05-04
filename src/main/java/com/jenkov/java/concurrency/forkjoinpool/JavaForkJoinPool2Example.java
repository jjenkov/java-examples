package com.jenkov.java.concurrency.forkjoinpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class JavaForkJoinPool2Example {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool1 = ForkJoinPool.commonPool();
        ForkJoinPool forkJoinPool2 = new ForkJoinPool(4);

        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(123);
        //forkJoinPool1.invoke(myRecursiveAction);

        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(123);
        //long result = forkJoinPool1.invoke(myRecursiveTask);

        ForkJoinTask<Long> forkJoinTask = forkJoinPool1.submit(myRecursiveTask);
        try {
            Long result = forkJoinTask.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        forkJoinPool1.getParallelism();
        forkJoinPool1.setParallelism(10);
        forkJoinPool1.getPoolSize();
        forkJoinPool1.getQueuedSubmissionCount();
        forkJoinPool1.getRunningThreadCount();
        forkJoinPool1.isShutdown();
        forkJoinPool1.isTerminated();
        forkJoinPool1.isTerminating();

        forkJoinPool1.shutdown();
        forkJoinPool1.shutdownNow();


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
