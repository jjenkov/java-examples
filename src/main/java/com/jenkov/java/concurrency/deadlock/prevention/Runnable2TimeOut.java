package com.jenkov.java.concurrency.deadlock.prevention;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Runnable2TimeOut implements Runnable{

    private Lock lock1 = null;
    private Lock lock2 = null;

    public Runnable2TimeOut(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        while(true) {
            int failureCount = 0;
            while(! tryLockBothLocks()) {
                failureCount++;
                System.err.println(threadName + " failed to lock both Locks. Waiting a bit before retrying [" + failureCount + " tries]");
                sleep(100L * ((long) Math.random()));
            }
            if(failureCount > 0) {
                System.out.println(threadName + " succeeded in locking both locks - after " + failureCount + " failures.");
            }

            //do the work - now that both locks have been acquired (locked by this thread)

            //unlock
            lock2.unlock();
            lock1.unlock();
        }
    }

    private void sleep(long timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean tryLockBothLocks() {
        String threadName = Thread.currentThread().getName();

        try {
            boolean lock2Succeeded = lock2.tryLock(1000, TimeUnit.MILLISECONDS);
            if(!lock2Succeeded) {
                return false;
            }
        } catch (InterruptedException e) {
            System.out.println(threadName + " interrupted trying to lock Lock 2");
            return false;
        }
        try {
            boolean lock1Succeeded = lock1.tryLock(1000, TimeUnit.MILLISECONDS);
            if(!lock1Succeeded) {
                lock2.unlock();
                return false;
            }
        } catch (InterruptedException e) {
            System.out.println(threadName + " interrupted trying to lock Lock 1");
            lock1.unlock();
            return false;
        }

        return true;
    }
}
