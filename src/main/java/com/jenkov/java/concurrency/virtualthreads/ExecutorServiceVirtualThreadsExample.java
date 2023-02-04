package com.jenkov.java.concurrency.virtualthreads;

import java.util.concurrent.*;

public class ExecutorServiceVirtualThreadsExample {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        executor.submit(() -> {
            System.out.println("This is a Runnable that is executed by a virtual thread");
        });

        Callable<String> callable = new Callable<>() {
            @Override
            public String call() throws Exception {
                System.out.println("Callable executed by virtual thread");
                return "Result from Callable";
            }
        };

        Future<String> futureResult = executor.submit(callable);

        try {
            System.out.println(futureResult.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
