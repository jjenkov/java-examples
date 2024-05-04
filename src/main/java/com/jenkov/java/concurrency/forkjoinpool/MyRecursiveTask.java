package com.jenkov.java.concurrency.forkjoinpool;

import java.util.concurrent.RecursiveTask;

public class MyRecursiveTask extends RecursiveTask<Long> {

    private long workLoad = 0;

    public MyRecursiveTask(long workLoad) {
        this.workLoad = workLoad;
    }

    protected Long compute() {

        //if work is above threshold, break tasks up into smaller tasks
        if(this.workLoad > 16) {
            System.out.println("Splitting workLoad : " + this.workLoad);

            long workload1 = this.workLoad / 2;
            long workload2 = this.workLoad - workload1;

            MyRecursiveTask subtask1 = new MyRecursiveTask(workload1);
            MyRecursiveTask subtask2 = new MyRecursiveTask(workload2);

            subtask1.fork();
            subtask2.fork();

            long result = 0;
            result += subtask1.join();
            result += subtask2.join();
            return result;

        } else {
            System.out.println("Doing workLoad myself: " + this.workLoad);
            return workLoad * 3;
        }
    }
}
