package com.learn.common.thread;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue <T>{
    ReentrantLock lock = new ReentrantLock();
    Condition fullWaitSet = lock.newCondition();
    Condition emptyWaitSet = lock.newCondition();
    Deque<T> deque = new ArrayDeque<>();
    private int capacity;
    public BlockingQueue (int capacity){
        this.capacity = capacity;
    }
    public T take(){
        lock.lock();
        try {
            while(deque.isEmpty()){
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = deque.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }
    public void put(T t){
        lock.lock();
        try {
            while(deque.size()==capacity){
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            deque.push(t);
            emptyWaitSet.signal();

        }finally {
            lock.unlock();
        }
    }
    public int size(){
        lock.lock();
        try{
            return deque.size();
        }finally {
            lock.unlock();
        }
    }
}
