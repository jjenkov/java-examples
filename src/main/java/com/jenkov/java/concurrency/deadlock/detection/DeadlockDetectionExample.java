package com.jenkov.java.concurrency.deadlock.detection;

public class DeadlockDetectionExample {

    public static void main(String[] args) {

        LockNode   lockNode1   = new LockNode();
        ThreadNode threadNode1 = new ThreadNode();
        lockNode1.lockedBy = threadNode1;

        LockNode   lockNode2   = new LockNode();
        ThreadNode threadNode2 = new ThreadNode();
        lockNode2.lockedBy = threadNode2;

        LockNode   lockNode3   = new LockNode();
        ThreadNode threadNode3 = new ThreadNode();
        lockNode3.lockedBy = threadNode3;

        threadNode1.waitingFor = lockNode2;
        //threadNode2.waitingFor = lockNode3;
        threadNode3.waitingFor = lockNode1;


        DeadlockDetection deadlockDetection = new DeadlockDetection();

        boolean involvedInDeadlock1 = deadlockDetection.isInvolvedInDeadlock(lockNode1);
        boolean involvedInDeadlock2 = deadlockDetection.isInvolvedInDeadlock(lockNode2);
        boolean involvedInDeadlock3 = deadlockDetection.isInvolvedInDeadlock(lockNode3);

        System.out.println(involvedInDeadlock1);
        System.out.println(involvedInDeadlock2);
        System.out.println(involvedInDeadlock3);


    }
}
