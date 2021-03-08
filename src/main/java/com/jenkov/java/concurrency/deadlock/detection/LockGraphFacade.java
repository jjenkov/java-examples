package com.jenkov.java.concurrency.deadlock.detection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

public class LockGraphFacade {

    private DeadlockDetection deadlockDetection = new DeadlockDetection();

    private Map<Thread, ThreadNode> threadNodes = new HashMap<>();
    private Map<Lock, LockNode>     lockNodes   = new HashMap<>();

    public synchronized boolean tryLock(Lock lock) {
        Thread     currentThread = Thread.currentThread();
        return tryLock(lock, currentThread);
    }

    public synchronized boolean tryLock(Lock lock, Thread currentThread) {
        System.out.println(currentThread.getName() + " tryLock()...");
        LockNode lockNode = getOrCreateLockNode(lock);

        ThreadNode threadNode    = getOrCreateThreadNode(currentThread);

        if(lockNode.lockedBy == threadNode) {
            return true;
        }
        if(lockNode.lockedBy == null) {
            lockNode.lockedBy = threadNode;
            lock.lock();
            return true;
        }

        threadNode.waitingFor = lockNode;

        if(this.deadlockDetection.isInvolvedInDeadlock(lockNode)){
            threadNode.waitingFor = null;
            return false;
        }

        // Lock is locked by another thread, but is not part of a deadlock,
        // so it is safe to wait to lock the Lock at a later time.
        lock.lock();
        threadNode.waitingFor = null;
        lockNode.lockedBy = threadNode;
        return true;
    }

    public synchronized void unlock(Lock lock) {
        lock.unlock();
        LockNode lockNode = getOrCreateLockNode(lock);
        lockNode.lockedBy = null;
    }

    private LockNode getOrCreateLockNode(Lock lock) {
        LockNode lockNode = this.lockNodes.get(lock);
        if(lockNode == null) {
            lockNode = new LockNode();
            this.lockNodes.put(lock, lockNode);
        }
        return lockNode;
    }

    private ThreadNode getOrCreateThreadNode(Thread thread) {
        ThreadNode threadNode = this.threadNodes.get(thread);
        if(threadNode == null) {
            threadNode = new ThreadNode();
            threadNode.thread = thread;
            this.threadNodes.put(thread, threadNode);
        }
        return threadNode;
    }

}
