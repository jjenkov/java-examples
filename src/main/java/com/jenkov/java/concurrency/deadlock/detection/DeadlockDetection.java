package com.jenkov.java.concurrency.deadlock.detection;

import java.util.HashSet;
import java.util.Set;

public class DeadlockDetection {

    public boolean isInvolvedInDeadlock(LockNode lockNode) {
        Set<ThreadNode> involvedThreadNodes = new HashSet<>();

        while(true) {
            ThreadNode lockedByThread = lockNode.lockedBy;

            if(involvedThreadNodes.contains(lockedByThread)) {
                return true;
            }
            involvedThreadNodes.add(lockedByThread);

            if(lockedByThread.waitingFor == null) {
                return false;
            }
            lockNode = lockedByThread.waitingFor;
        }
    }


    public boolean isInvolvedInDeadlock2(LockNode lockNode) {
        Set<ThreadNode> involvedThreadNodes = new HashSet<>();

        boolean isDeadlockDetected = false;
        boolean endOfGraphDetected = false;
        while(!isDeadlockDetected && !endOfGraphDetected) {
            ThreadNode lockedByThread = lockNode.lockedBy;

            if(involvedThreadNodes.contains(lockedByThread)) {
                isDeadlockDetected = true;
                break;
            }
            involvedThreadNodes.add(lockedByThread);

            if(lockedByThread.waitingFor == null) {
                endOfGraphDetected = true;
                break;
            }
            lockNode = lockedByThread.waitingFor;
        }

        return isDeadlockDetected;
    }
}
