package com.jenkov.java.concurrency.threadcongestion;

import com.jenkov.java.concurrency.threadpipeline.Step1Processor;
import com.jenkov.java.concurrency.threadpipeline.Step2Processor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPipelineMain {

    public static void main(String[] args) {

        BlockingQueue queue1 = new ArrayBlockingQueue(10);
        BlockingQueue queue2 = new ArrayBlockingQueue(10);

        BlockingQueue finalOutputQueue = new ArrayBlockingQueue(10);

        Step1Processor processor1 = new Step1Processor();
        Step2Processor processor2 = new Step2Processor();

        processor1.setInputQueue (queue1);
        processor1.setOutputQueue(queue2);
        processor2.setInputQueue (queue2);
        processor2.setOutputQueue(finalOutputQueue);

        Thread thread1 = new Thread(processor1);
        Thread thread2 = new Thread(processor2);

        thread1.start();
        thread2.start();

        for(int i=0; i<10; i++) {
            try {
                queue1.put("[" + i + "] abcdefgh" );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i=0; i<10; i++) {
            try {
                Object result = finalOutputQueue.take();
                System.out.println("Result: " + result);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done");
        processor1.requestShutdown();
        processor2.requestShutdown();


    }
}
